package com.greco.service;

import com.greco.model.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface UsersService {

    Users findById(Long id);
    Page<Users> findAll(Specification<Users> specs, Pageable pageable);
    Users insert(Users users);
    Users update(Users users);
    void deleteById(Long id);
}
