package com.ccsu.course.registration.model;

import org.springframework.stereotype.Component;

@Component
public class Faculty {

    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String website;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    @Override
    public String toString() {
        return "Faculty [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", website="
                + website + "]";
    }


}