package com.greco.service.impl;

import com.greco.exception.BadRequestException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Users;
import com.greco.repository.UsersRepository;
import com.greco.service.EmailService;
import com.greco.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
    @Autowired
    UsersRepository usersRepository;
    @Value("${url.redirect}")
    public String URL_REDIRECT;
    @Value("${credential.email.account}")
    public String FROM;
    @Value("${credential.email.password}")
    public String PASSW;
    @Value("${credential.email.name}")
    public String FROMNAME;
    public String TEXT_RESET_PASSWORD = "Hello, <br/><br/>A request to reset your Greco account password was received. Click the next link to reset your password.<br/><br/>";
    @Override
    public void sendEmailToResetPassword(String email) throws Exception {
        if(Utils.isEmpty(email))
            throw new BadRequestException(GenericCheckingMessage.FORGOT_PASSWORD_EMPTY_EMAIL.toString());
        Users user = usersRepository.findByEmail(email);
        if(user == null)
            throw new BadRequestException(GenericCheckingMessage.FORGOT_PASSWORD_UNREGISTERED_USER.toString());
        String uuid = user.getUuid();
        String contentBody = TEXT_RESET_PASSWORD + "<a href='" + URL_REDIRECT + "?"+ uuid +"'>reset password</a>";
        sendMessage(email, "Reset password", contentBody);
    }

    private void sendMessage(String email, String subject, String content) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSW);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject(subject);
        msg.setContent(content, "text/plain; charset=UTF-8");
        msg.setSentDate(Utils.getTimestamp());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg);
    }
}
