package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{

    @Query("select c from Courses as c, StudentCourses as s, Login as l where c.id = s.courseId and l.ccsuId = s.ccsuId and l.userName =:username")
    public List<Courses> getAllCoursesByUserName(@Param("username") String userName);
    
}