package com.ccsu.course.registration.repository;

import com.ccsu.course.registration.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
    Optional<Login> findByUserName(String userName);
}