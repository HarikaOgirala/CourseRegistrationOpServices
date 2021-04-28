package com.ccsu.course.registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoursesDetails {

    private String courseName;
    private String courseNumber;
    private String type;
    private List<String> prerequisite;
    private List<String> timings;
    private List<String> days;
    private List<String> semesters;
    private String partOfDay;
    private String courseCRN;
    @JsonProperty("taughtBy")
    private Faculty taughtBy;
    private String courseDesc;

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<String> getTimings() {
        return timings;
    }

    public void setTimings(List<String> timings) {
        this.timings = timings;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<String> semesters) {
        this.semesters = semesters;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public String getCourseCRN() {
        return courseCRN;
    }

    public void setCourseCRN(String courseCRN) {
        this.courseCRN = courseCRN;
    }

    public Faculty getFaculty() {
        return taughtBy;
    }

    public void setFaculty(Faculty taughtBy) {
        this.taughtBy = taughtBy;
    }


    public Faculty getTaughtBy() {
        return taughtBy;
    }

    public void setTaughtBy(Faculty taughtBy) {
        this.taughtBy = taughtBy;
    }

}
