package com.ccsu.course.registration.service;

import com.ccsu.course.registration.model.Courses;
import com.ccsu.course.registration.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.receipents}")
    private String mailReceipents;


    public void sendEmail(Courses courses, Login user) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            String receipents[] = mailReceipents.split(",");
            helper.setTo(receipents);
            helper.setText(buildEmailText(user, courses));
            helper.setSubject("Request to register course number "+courses.getCourseNumber());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    private String buildEmailText(Login user, Courses courses) {
        String emailText = "You are receiving this email from "+user.getFirstName()+" "+user.getLastName()+" ccsuid: " +user.getCcsuId()+
                " requesting to check students eligibility to take the below course \n \n" +
                " Course Number: "+ courses.getCourseNumber()+"\n" +
                " Course Name: "+courses.getCourseName();
        return emailText;

    }
}
