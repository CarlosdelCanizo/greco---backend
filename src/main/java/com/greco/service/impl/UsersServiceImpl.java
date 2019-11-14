package com.greco.service.impl;

import com.greco.exception.ServerErrorEnum;
import com.greco.exception.ServerException;
import com.greco.model.Users;
import com.greco.repository.UsersRepository;
import com.greco.service.UsersService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.dao.EmptyResultDataAccessException;


@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users findById(Long id) {
        Optional<Users> optional = usersRepository.findById(id);
        if (!optional.isPresent()){
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Users with ID: "+ id+ "couldn't be found.");
        }
        return optional.get();
    }

    @Override
    public Users insert(Users users) {
    return usersRepository.save(users);
    }

    @Override
    public Users update(Users users) {
        if (users == null || users.getUserId() == null || users.getUserId() == 0) throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The userId couldn't be found or equal to 0.");
        if (!usersRepository.findById(users.getUserId()).isPresent()) {
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Users with ID: "+ users.getUserId() + "couldn't be found.");
        }
        return usersRepository.save(users);
    }

    @Override
    public void deleteById(Long id) {
    try {
        usersRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Users with ID: "+ id+ "couldn't be found.");
        }
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public Users findByEmailAndUuid(String email, String uuid) {
        return usersRepository.findByEmailAndUuid(email, uuid);
    }
}
