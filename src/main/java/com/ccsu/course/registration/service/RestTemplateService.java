package com.ccsu.course.registration.service;
import com.ccsu.course.registration.model.CoursesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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
}
