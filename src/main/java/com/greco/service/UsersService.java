package com.greco.service;

import com.greco.model.Users;

public interface UsersService {

    Users findById(Long id);
    Users insert(Users users);
    Users update(Users users);
    void deleteById(Long id);
    Users findByEmail(String email);
    Users findByEmailAndUuid(String email, String uuid);
}
