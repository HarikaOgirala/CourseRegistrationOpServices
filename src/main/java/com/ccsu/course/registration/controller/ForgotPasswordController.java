package com.ccsu.course.registration.controller;
import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.model.Login;
import com.ccsu.course.registration.service.LoginService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


   /* @PostMapping("/login/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            loginService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "forgot-password-form";
    }*/

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
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Login login = loginService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (login == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            loginService.updatePassword(login, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }
        return "message";
    }
}