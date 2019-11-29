package com.greco.security;

import com.greco.model.Users;
import com.greco.repository.UsersRepository;
import com.greco.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class LoginListener implements ApplicationListener <AuthenticationSuccessEvent> {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (event.getSource() instanceof UsernamePasswordAuthenticationToken) {
            // update if it is the first login time
            Users loggedInUser =  usersRepository.findByEmail(event.getAuthentication().getName());
            if(loggedInUser != null)
                controlLogin(loggedInUser);
        }
    }

    private void controlLogin(Users user) {
        if(user.getFirstTime() == null) {
            user.setFirstTime(true);
            usersRepository.save(user);
        }
        else {
            if(user.getFirstTime() == true) {
                user.setFirstTime(false);
                usersRepository.save(user);
            }
        }
    }

}
