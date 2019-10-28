package com.greco.service;

import com.greco.model.Rol;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface RolService {

    Rol findById(Long id);
    Page<Rol> findAll(Specification<Rol> specs, Pageable pageable);
    Rol insert(Rol rol);
    Rol update(Rol rol);
    void deleteById(Long id);
}
