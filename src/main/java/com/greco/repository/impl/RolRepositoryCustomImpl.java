//USER_EXIT_METADATA#JAVA_SPRINGBOOT#-901205929#10349
package com.greco.repository.impl;

import com.greco.model.Rol;
import com.greco.repository.RolRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class RolRepositoryCustomImpl implements RolRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Rol rol)
    {
        entityManager.detach(rol);
    }
}
