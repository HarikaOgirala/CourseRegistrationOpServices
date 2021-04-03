package com.registration.springboot.repository;

import com.registration.springboot.model.Courses;
import com.registration.springboot.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
    Optional<Login> findByUserNameAndPassword(String uname, String userName);
}