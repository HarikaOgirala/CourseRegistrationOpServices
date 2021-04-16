package com.ccsu.course.registration.service;

import com.ccsu.course.registration.constants.CourseStatus;
import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.model.*;
import com.ccsu.course.registration.repository.CoursesRepository;
import com.ccsu.course.registration.repository.LoginRepository;
import com.ccsu.course.registration.repository.StudentCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseRegistrationService {

    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private StudentCoursesRepository studentCoursesRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MailService mailService;

    @Transactional
    public void validateAndRegisterCourse(Authentication authentication, String id) throws ResourceNotFoundException {
        List<String> isValid;
        CoursesDetails courses =  restTemplateService.getCourseDetails(id);
        List<String> preRequisites = courses.getPrerequisite().stream().filter(course -> !"null".equals(course)).map(p -> p.trim()).collect(Collectors.toList());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<CourseResponse> userCourses = coursesRepository.getAllCoursesByUserName(userDetails.getUsername());
        List<String> completedCourses = userCourses.stream().filter(course -> CourseStatus.COMPLETED.equals(course.getStatus())).map(course ->course.getCourseNumber().trim()).collect(Collectors.toList());
        List<String> registeredCourses = userCourses.stream().filter(course -> CourseStatus.REGISTERED.equals(course.getStatus())).map(course ->course.getCourseNumber().trim()).collect(Collectors.toList());
        isValid = preRequisites.stream().filter(preRequisite -> !completedCourses.contains(preRequisite)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(isValid)) {
            throw new RuntimeException("PreRequisites "+ String.join(",",isValid.stream().collect(Collectors.joining(","))+ " are not completed"));
        }
        else if(ObjectUtils.isEmpty(courses.getCourseNumber())){
            throw new RuntimeException("Course not found : "+ id);
        }
        else if(registeredCourses.contains(courses.getCourseNumber())) {
            throw new RuntimeException("Already registered for this course : "+ courses.getCourseNumber());
        }
        else if(completedCourses.contains(courses.getCourseNumber())) {
            throw new RuntimeException("Already Completed this course : "+ courses.getCourseNumber());
        }
        else {
            Courses course = buildCourseEntity(courses);
            coursesRepository.save(course);
            Optional<Login> login = loginService.getUserDetails(userDetails.getUsername());
            if(login.isPresent()) {
                StudentCourses studentCourses = buildStudentCourses(course.getId(), login.get().getCcsuId(), CourseStatus.REGISTERED);
                studentCoursesRepository.save(studentCourses);
                mailService.sendEmail(course, login.get());
            }
            else {
               throw new RuntimeException("User not found for id "+userDetails.getUsername());
            }

        }
    }

    private StudentCourses buildStudentCourses(long courseId, long ccsuId, CourseStatus status) {
        StudentCourses studentCourses = new StudentCourses();
        studentCourses.setCcsuId(ccsuId);
        studentCourses.setCourseId(courseId);
        studentCourses.setStatus(status);
        return studentCourses;
    }

    private Courses buildCourseEntity(CoursesDetails courses) {
        Courses course = new Courses();
        course.setCourseNumber(courses.getCourseNumber());
        course.setCourseName(courses.getCourseName());
        course.setSemester(courses.getSemesters().stream().collect(Collectors.joining(",")));
        course.setType(course.getType());
        course.setPreRequisites(courses.getPrerequisite().stream().collect(Collectors.joining(",")));
        course.setPartOfDay(courses.getPartOfDay());
        course.setTime(course.getTime());
        course.setDay(courses.getDays().stream().collect(Collectors.joining(",")));
        course.setFaculty(courses.getFaculty().getName());
        return course;
    }
}
