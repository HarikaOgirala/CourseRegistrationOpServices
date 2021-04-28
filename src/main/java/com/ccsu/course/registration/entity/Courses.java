package com.ccsu.course.registration.entity;

import com.ccsu.course.registration.constants.CourseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {

    private long id;
    private String courseNumber;
    private String courseName;
    private String semester;
    private String type;
    private String preRequisites;
    private String partOfDay;
    private String time;
    private String day;
    private String faculty;
    private String courseDesc;
    private String crn;

    public Courses(){

    }

    public Courses(String courseNumber, String courseName, String semester, String type,
                   String preRequisites, String partOfDay, String time, String day, String faculty, String courseDesc) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.semester = semester;
        this.type = type;
        this.preRequisites = preRequisites;
        this.partOfDay = partOfDay;
        this.time = time;
        this.day = day;
        this.faculty = faculty;
        this.courseDesc = courseDesc;
    }

 
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "coursenumber")
    public String getCourseNumber() { return courseNumber; }
    public void setCourseNumber(String courseNumber) { this.courseNumber = courseNumber; }

    @Column(name = "coursename")
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(name = "semester")
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Column(name = "type")
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Column(name = "prerequisites")
    public String getPreRequisites() { return preRequisites; }
    public void setPreRequisites(String preRequisites) { this.preRequisites = preRequisites; }

    @Column(name = "partofday", nullable = true)
    public String getPartOfDay() { return partOfDay; }
    public void setPartOfDay(String partOfDay) { this.partOfDay = partOfDay; }

    @Column(name = "time")
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "day")
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    @Column(name = "faculty")
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    @Column(name = "coursedesc")
    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    @Column(name = "crn")
    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }


    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", semester='" + semester + '\'' +
                ", type='" + type + '\'' +
                ", preRequisites='" + preRequisites + '\'' +
                ", partOfDay='" + partOfDay + '\'' +
                ", time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
