package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.constants.CourseStatus;
import com.ccsu.course.registration.model.CourseResponse;
import com.ccsu.course.registration.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{

    @Query("select new com.ccsu.course.registration.model.CourseResponse(c.id, c.courseNumber, c.courseName, c.semester, c.type, c.preRequisites, c.partOfDay, c.time, c.day, c.faculty, s.status) from Courses as c, StudentCourses as s, Login as l where c.id = s.courseId and l.ccsuId = s.ccsuId and l.userName =:username")
    public List<CourseResponse> getAllCoursesByUserName(@Param("username") String userName);


    @Query("select new com.ccsu.course.registration.model.CourseResponse(c.id, c.courseNumber, c.courseName, c.semester, c.type, c.preRequisites, c.partOfDay, c.time, c.day, c.faculty, s.status) from Courses as c, StudentCourses as s, Login as l where c.id = s.courseId and l.ccsuId = s.ccsuId and l.userName =:username and s.status =:status")
    public List<CourseResponse> getAllCoursesByUserNameAndStatus(@Param("username") String userName, @Param("status") CourseStatus status);

    Optional<Courses> findByCourseNumber(String courseNumber);
}