package com.ccsu.course.registration.controller;

import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.model.ForgotPassword;
import com.ccsu.course.registration.model.Login;
import com.ccsu.course.registration.service.LoginService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v3")

public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private LoginService loginService;

    @Value("${courseOp.registration.url}")
    private String registrationUrl;

    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);

    @PostMapping("/login/forgot_password")
    public String processForgotPassword(@RequestBody ForgotPassword forgotPassword) {
        String email = forgotPassword.getEmail();
        String token = RandomString.make(30);

        try {
            loginService.updateResetPasswordToken(token, email);
            String resetPasswordLink = registrationUrl + "/resetpassword?token=" + token;
            sendEmail(email, resetPasswordLink);

        } catch (ResourceNotFoundException ex) {
            logger.info("Error while sending email {}", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            logger.info("Error while sending email {}", e.getMessage());
        }
        return "forgot-password-form";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        //helper.setFrom("nagaharika.ogirala@gmail.com", "Password Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }


   /* @GetMapping("/login/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Login login = loginService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (login == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }*/

    @PostMapping("/login/reset_password")
    public String processResetPassword(@RequestBody ForgotPassword forgotPassword) {
        String token = forgotPassword.getToken();
        String password = forgotPassword.getPassword();

        Login login = loginService.getByResetPasswordToken(token);
        if (login == null) {
            logger.error("Invalid Token");
            return "message";
        } else {
            loginService.updatePassword(login, password);
            logger.error("Successfully updated the password");
        }
        return "message";
    }
}