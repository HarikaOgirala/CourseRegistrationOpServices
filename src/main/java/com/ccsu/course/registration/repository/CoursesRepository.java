package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{
    
}