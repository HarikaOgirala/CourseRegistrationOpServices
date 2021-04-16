package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.model.CourseResponse;
import com.ccsu.course.registration.model.Courses;
import com.ccsu.course.registration.model.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Long>{

    Optional<StudentCourses> findByCourseIdAndCcsuId(Long courseId, Long ccsuId);

}