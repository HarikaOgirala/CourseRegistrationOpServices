package com.registration.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registration.springboot.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{
    
}