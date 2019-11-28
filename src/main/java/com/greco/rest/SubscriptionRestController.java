package com.greco.rest;

import com.greco.model.Users;
import com.greco.service.AuthenticationService;
import com.greco.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subscription")
public class SubscriptionRestController {
    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/{challengeId}")
    public void insert(@PathVariable("challengeId") Long challengeId) {
        Users user = authenticationService.getLoggedUser();
        challengeService.subscribe(user, challengeId);
    }
}
