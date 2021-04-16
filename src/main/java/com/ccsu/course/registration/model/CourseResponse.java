package com.ccsu.course.registration.model;

import com.ccsu.course.registration.constants.CourseStatus;
import org.springframework.stereotype.Component;

@Component
public class CourseResponse {

    private Long id;
    private String courseNumber;
    private String courseName;
    private String semester;
    private String type;
    private String preRequisites;
    private String partOfDay;
    private String time;
    private String day;
    private String faculty;
    private CourseStatus status;

    public CourseResponse() {}

    public CourseResponse(Long id, String courseNumber, String courseName, String semester, String type, String preRequisites, String partOfDay, String time, String day, String faculty, CourseStatus status) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.semester = semester;
        this.type = type;
        this.preRequisites = preRequisites;
        this.partOfDay = partOfDay;
        this.time = time;
        this.day = day;
        this.faculty = faculty;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(String preRequisites) {
        this.preRequisites = preRequisites;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
