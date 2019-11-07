package com.greco.service.impl;

import com.greco.model.Users;
import com.greco.service.AuthenticationService;
import com.greco.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("autenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UsersService userService;

    public Users getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userService.findByEmail(email);
        return user;
    }
}
