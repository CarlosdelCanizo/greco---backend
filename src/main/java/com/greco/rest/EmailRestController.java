package com.greco.rest;

import com.greco.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailRestController {
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendEmailToResetThePassword")
    public String sendEmailToResetThePassword (@RequestParam(value="email", required = true) String email) throws Exception {
        emailService.sendEmailToResetPassword(email);
        return "Email sent successfully to reset password";
    }
}
