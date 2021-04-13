package com.ccsu.course.registration.model;

import com.ccsu.course.registration.constants.CourseStatus;

import javax.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourses {

    private Long id;
    private Long ccsuId;
    private Long courseId;
    private CourseStatus status;

    public StudentCourses() {}

    public StudentCourses(Long ccsuId, Long courseId, CourseStatus status) {
        this.ccsuId = ccsuId;
        this.courseId = courseId;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ccsu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCcsuId() {
        return ccsuId;
    }
    public void setCcsuId(long ccsuId) {
        this.ccsuId = ccsuId;
    }

    @Column(name = "course_id", nullable = false)
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public CourseStatus getStatus() {
        return status;
    }
    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
