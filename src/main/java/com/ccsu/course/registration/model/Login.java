package com.ccsu.course.registration.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class Login {
    private int ccsuId;
    private String userName;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCcsuId() {
        return ccsuId;
    }
    public void setCcsuId(long id) {
        this.ccsuId = ccsuId;
    }

    @Column(name = "username", nullable = false)
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
