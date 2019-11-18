package com.greco.service;

import org.springframework.stereotype.Service;


public interface EmailService {
     void sendEmailToResetPassword(String email) throws Exception;
}
