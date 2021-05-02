package com.ccsu.course.registration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ccsu.course.registration.constants.CourseStatus;
import com.ccsu.course.registration.entity.Courses;
import com.ccsu.course.registration.entity.Login;
import com.ccsu.course.registration.entity.StudentCourses;
import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.model.*;
import com.ccsu.course.registration.repository.CoursesRepository;
import com.ccsu.course.registration.repository.LoginRepository;
import com.ccsu.course.registration.repository.StudentCoursesRepository;
import com.ccsu.course.registration.service.CourseRegistrationService;
import com.ccsu.course.registration.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CoursesController {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private StudentCoursesRepository studentCoursesRepository;
    @Autowired
    private CourseRegistrationService courseRegistrationService;
    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private LoginRepository loginRepository;

    private static final String STATUS_ALL = "ALL";

    private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);

    @GetMapping("/courses/status/{status}")
    public List<CourseResponse> getAllCourses(Authentication authentication, @PathVariable(value = "status") String status) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (STATUS_ALL.equalsIgnoreCase(status) || ObjectUtils.isEmpty(status)) {
            return coursesRepository.getAllCoursesByUserName(userDetails.getUsername());
        } else {
            return coursesRepository.getAllCoursesByUserNameAndStatus(userDetails.getUsername(), CourseStatus.valueOf(status));
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Courses> getCoursesById(@PathVariable(value = "id") Long coursesId)
            throws ResourceNotFoundException {
        Courses courses = coursesRepository.findById(coursesId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + coursesId));
        return ResponseEntity.ok().body(courses);
    }

    @PostMapping("/courses")
    public Courses createCourses(@Valid @RequestBody Courses courses) {
        return coursesRepository.save(courses);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Courses> updateCourses(@PathVariable(value = "id") Long coursesId,
                                                 @Valid @RequestBody Courses coursesDetails) throws ResourceNotFoundException {
        Courses courses = coursesRepository.findById(coursesId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + coursesId));

        courses.setCourseNumber(coursesDetails.getCourseNumber());
        courses.setCourseName(coursesDetails.getCourseName());
        courses.setSemester(coursesDetails.getSemester());
        courses.setType(coursesDetails.getType());
        courses.setPreRequisites(coursesDetails.getPreRequisites());
        courses.setPartOfDay(coursesDetails.getPartOfDay());
        courses.setTime(coursesDetails.getTime());
        courses.setDay(coursesDetails.getDay());
        courses.setFaculty(coursesDetails.getFaculty());


        final Courses updatedCourses = coursesRepository.save(courses);
        return ResponseEntity.ok(updatedCourses);
    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteCourses(Authentication authentication, @PathVariable(value = "id") Long coursesId)
            throws ResourceNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Login user = loginRepository.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Username not found :: " + userDetails.getUsername()));
        StudentCourses studentCourses = studentCoursesRepository.findByCourseIdAndCcsuId(coursesId, user.getCcsuId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for user:: " + userDetails.getUsername()));
        studentCoursesRepository.delete(studentCourses);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/courses/register")
    public Map<String, Boolean> registerCourse(Authentication authentication, @RequestParam(value = "id") String id) throws ResourceNotFoundException {
        courseRegistrationService.validateAndRegisterCourse(authentication, id);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("registered", true);
        return responseMap;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<List<String>> getCoursesList() {
        List<CoursesDetails> coursesDetails = restTemplateService.getAllCourseDetails();
        List<String> courseNumbers = coursesDetails.stream().map(course -> course.getCourseNumber()).collect(Collectors.toList());
        return ResponseEntity.ok(courseNumbers);
    }

    @GetMapping("/courses/sendmail")
    public ResponseEntity<Boolean> sendEmail(Authentication authentication, @RequestParam(value = "id") String id) throws ResourceNotFoundException {
        courseRegistrationService.sendEmail(authentication, id);
        logger.info("Email Sent");
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
