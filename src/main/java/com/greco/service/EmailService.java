package com.greco.service;

import org.springframework.stereotype.Service;

@Service("emailService")
public interface EmailService {
     void sendEmailToResetPassword(String email) throws Exception;
}
