package com.ccsu.course.registration.model;

import javax.persistence.*;

@Entity
@Table(name = "student_courses")
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


    public Courses(){

    }

    public Courses(String courseNumber, String courseName, String semester, String type,
                   String preRequisites, String partOfDay, String time, String day, String faculty) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.semester = semester;
        this.type = type;
        this.preRequisites = preRequisites;
        this.partOfDay = partOfDay;
        this.time = time;
        this.day = day;
        this.faculty = faculty;
    }

 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "coursenumber", nullable = false)
    public String getCourseNumber() { return courseNumber; }
    public void setCourseNumber(String courseNumber) { this.courseNumber = courseNumber; }

    @Column(name = "coursename", nullable = false)
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(name = "semester", nullable = false)
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Column(name = "type", nullable = false)
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Column(name = "prerequisites", nullable = false)
    public String getPreRequisites() { return preRequisites; }
    public void setPreRequisites(String preRequisites) { this.preRequisites = preRequisites; }

    @Column(name = "partofday", nullable = false)
    public String getPartOfDay() { return partOfDay; }
    public void setPartOfDay(String partOfDay) { this.partOfDay = partOfDay; }

    @Column(name = "time", nullable = false)
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "day", nullable = false)
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    @Column(name = "faculty", nullable = false)
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }


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
