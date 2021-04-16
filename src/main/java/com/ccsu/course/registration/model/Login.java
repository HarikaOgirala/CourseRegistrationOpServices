package com.ccsu.course.registration.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class Login {
    private Long ccsuId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCcsuId() {
        return this.ccsuId;
    }
    public void setCcsuId(Long ccsuId) {
        this.ccsuId = ccsuId;
    }

    @Column(name = "username", nullable = false)
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "firstname", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
