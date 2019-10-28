//USER_EXIT_METADATA#JAVA_SPRINGBOOT#-901205929#10351
package com.greco.repository.impl;

import com.greco.model.Users;
import com.greco.repository.UsersRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Users users)
    {
        entityManager.detach(users);
    }
}
