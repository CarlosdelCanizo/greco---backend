package com.greco.service.impl;

import com.greco.model.Rol;
import com.greco.exception.ServerErrorEnum;
import com.greco.exception.ServerException;
import com.greco.repository.RolRepository;
import com.greco.service.RolService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.dao.EmptyResultDataAccessException;


@Service("rolService")
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public Rol findById(Long id) {
        Optional<Rol> optional = rolRepository.findById(id);
        if (!optional.isPresent()){
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Rol with ID: "+ id+ "couldn't be found.");
        }
        return optional.get();
    }

    @Override
    public Page<Rol> findAll(Specification<Rol> specs, Pageable pageable) {
        return rolRepository.findAll(specs, pageable);
    }

    @Override
    public Rol insert(Rol rol) {
    return rolRepository.save(rol);
    }

    @Override
    public Rol update(Rol rol) {
        if (rol == null || rol.getRolId() == null || rol.getRolId() == 0) throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The rolId couldn't be found or equal to 0.");
        if (!rolRepository.findById(rol.getRolId()).isPresent()) {
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Rol with ID: "+ rol.getRolId() + "couldn't be found.");
        }
        return rolRepository.save(rol);
    }

    @Override
    public void deleteById(Long id) {
    try {
        rolRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ServerException(ServerErrorEnum.ENTITY_NOT_FOUND, "The Rol with ID: "+ id+ "couldn't be found.");
        }
    }
}
