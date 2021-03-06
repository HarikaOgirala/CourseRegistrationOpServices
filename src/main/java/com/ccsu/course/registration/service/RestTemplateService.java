package com.ccsu.course.registration.service;
import com.ccsu.course.registration.model.CoursesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${courseOp.url}")
    private String courseOpUrl;

    public CoursesDetails getCourseDetails(String courseNumber) {
        ResponseEntity<CoursesDetails> response
                = restTemplate.getForEntity(courseOpUrl + "/api/courseDetails?courseNumber={courseNumber}", CoursesDetails.class, courseNumber);
        return response.getBody();
    }

    public List<CoursesDetails> getAllCourseDetails() {
        ResponseEntity<List<CoursesDetails>> response
                = restTemplate.exchange(courseOpUrl + "/api/courseDetails/all",  HttpMethod.GET, null, new ParameterizedTypeReference<List<CoursesDetails>>() {});
        return response.getBody();
    }
}
