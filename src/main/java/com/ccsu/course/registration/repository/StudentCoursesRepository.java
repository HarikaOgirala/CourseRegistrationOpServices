package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.entity.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Long>{

    Optional<StudentCourses> findByCourseIdAndCcsuId(Long courseId, Long ccsuId);

}