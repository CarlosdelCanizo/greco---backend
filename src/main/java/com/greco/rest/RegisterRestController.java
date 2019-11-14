package com.greco.rest;

import com.greco.exception.BadRequestException;
import com.greco.exception.ForbiddenException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Users;
import com.greco.request.RegistrationRequest;
import com.greco.service.UsersService;
import com.greco.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.validator.routines.EmailValidator;

import javax.rmi.CORBA.Util;
import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("register")
public class RegisterRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void insert(@RequestBody RegistrationRequest registrationRequest) {

        // Checks
        if(Utils.isEmpty(registrationRequest.getEmail()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_EMPTY_EMAIL.toString());
        if(usersService.findByEmail(registrationRequest.getEmail()) != null)
            throw new ForbiddenException(GenericCheckingMessage.REGISTRATION_USER_ALREADY_REGISTERED.toString());
        if(usersService.findByUsername(registrationRequest.getUsername()) != null)
            throw new ForbiddenException(GenericCheckingMessage.REGISTRATION_USERNAME_ALREADY_REGISTERED.toString());
        if(Utils.isEmpty(registrationRequest.getPassword()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_EMPTY_PASSWORD.toString());
        if(Utils.isEmpty(registrationRequest.getConfirmPassword()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_EMPTY_CONFIRM_PASSWORD.toString());
        if(!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH.toString());
        if(!EmailValidator.getInstance().isValid(registrationRequest.getEmail()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_INVALID_EMAIL.toString());
        if(Utils.hasSpecialCharacters(registrationRequest.getPassword()))
            throw new BadRequestException(GenericCheckingMessage.REGISTRATION_INVALID_PASSWORD_FORMAT.toString());

        //Creation of user
        Users newUser = createUserFromRequest(registrationRequest);
        usersService.insert(newUser);
    }

    private Users createUserFromRequest(RegistrationRequest registrationRequest){
        Users user = new Users();
        user.setEmail(registrationRequest.getEmail());
        String encryptedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(encryptedPassword);
        user.setPrivacyPolicyAcceptanceDate(new Timestamp(System.currentTimeMillis()));
        user.setUuid(UUID.randomUUID().toString());
        return user;
    }
}
