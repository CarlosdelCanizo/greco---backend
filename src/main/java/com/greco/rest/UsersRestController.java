package com.greco.rest;

import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersRestController {
    @Autowired
    UsersService usersService;

    @GetMapping("{id}")
    public IProjectable byId(@PathVariable("id") Long id) {
        return Projection.convertSingle(usersService.findById(id), "users");
    }
}
