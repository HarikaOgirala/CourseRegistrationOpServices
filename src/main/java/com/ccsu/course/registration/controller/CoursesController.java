package com.ccsu.course.registration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.model.Courses;
import com.ccsu.course.registration.model.CoursesDetails;
import com.ccsu.course.registration.repository.CoursesRepository;
import com.ccsu.course.registration.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/courses")
    public List<Courses> getAllCourses(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return coursesRepository.getAllCoursesByUserName(userDetails.getUsername());
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
    public Map<String, Boolean> deleteCourses(@PathVariable(value = "id") Long coursesId)
         throws ResourceNotFoundException {
        Courses courses = coursesRepository.findById(coursesId)
       .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + coursesId));

        coursesRepository.delete(courses);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/courses/register")
    public Map<String, Boolean> registerCourse(@RequestParam(value = "id") String id) {
        CoursesDetails courses =  restTemplateService.getCourseDetails(id);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("registered",true);
        return responseMap;
    }
}
