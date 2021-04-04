package com.ccsu.course.registration.security;


import com.ccsu.course.registration.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CorsFilter corsFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, SessionManagementFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest().
                authenticated()
                .and().httpBasic();

    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
