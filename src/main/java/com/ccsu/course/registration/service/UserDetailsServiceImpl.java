package com.ccsu.course.registration.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ccsu.course.registration.entity.Login;
import com.ccsu.course.registration.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Login> optionalUser = usersRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            Login users = optionalUser.get();
            return User.builder()
                    .username(users.getUserName())
                    //change here to store encoded password in db
                    .password(users.getPassword())
                    .authorities(getAuthority())
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name not Found");
        }
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
