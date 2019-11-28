package com.greco.rest;

import com.greco.dtos.AuthenticationDto;
import com.greco.dtos.ChallengesInfoDto;
import com.greco.exception.BadRequestException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Users;
import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.AuthenticationService;
import com.greco.service.ChallengeService;
import com.greco.service.UsersService;
import com.greco.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersRestController {
    @Autowired
    UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ChallengeService challengeService;

    @GetMapping("{id}")
    public IProjectable byId(@PathVariable("id") Long id) {
        IProjectable result =  Projection.convertSingle(usersService.findById(id), "users");
        fillAdditionalFields((com.greco.model.projection.Users)result);
        return result;
    }

    @GetMapping("/getMyUserInfo")
    public IProjectable getMyUserInfo() {
        Users loggedInUser = authenticationService.getLoggedUser();
        IProjectable result =  Projection.convertSingle(loggedInUser, "users");
        fillAdditionalFields((com.greco.model.projection.Users)result);
        return result;
    }

    @GetMapping("/challenges")
    public List<ChallengesInfoDto> getMyChallengeInfo() {
        Users loggedInUser = authenticationService.getLoggedUser();
        return challengeService.getMyChallengeInfo(loggedInUser.getUserId());
    }

    @PutMapping("/resetPassword")
    public void resetPassword(@RequestBody AuthenticationDto auth) {
        String password = auth.getPassword();
        String email = auth.getEmail();
        if(Utils.isEmpty(password) || Utils.isEmpty(email))
            throw new BadRequestException("Email and password are required");
        if(!auth.getPassword().equals(auth.getConfirmPassword()))
            throw new BadRequestException(GenericCheckingMessage.RESETPASSWORD_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH.toString());
        Users user = usersService.findByEmailAndUuid(auth.getEmail(), auth.getUuid());
        if(user == null) {
            user = usersService.findByEmail(auth.getEmail());
            if(user == null)
                throw new BadRequestException(GenericCheckingMessage.RESETPASSWORD_USER_NOT_REGISTERED.toString());
            else
                throw new BadRequestException(GenericCheckingMessage.RESETPASSWORD_INVALID_UUID.toString());
        }

        //Create a new uuid value
        user.setUuid(Utils.getRandomUUID());
        user.setPassword(passwordEncoder.encode(password));
        usersService.update(user);
    }

    /*@DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        usersService.deleteById(id);
    }*/

    private void fillAdditionalFields(com.greco.model.projection.Users usersProjection) {
        if(usersProjection.getFirstTime() == null || usersProjection.getFirstTime() == true)
            usersProjection.setIsPreviouslyLogged(false);
        else
            usersProjection.setIsPreviouslyLogged(true);
    }
}
