package com.greco.repository;

import com.greco.model.Rol;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


//USER_EXIT_BEGIN#IMPORTS#

//USER_EXIT_END#IMPORTS#

public interface RolRepository extends PagingAndSortingRepository<Rol, Long>, JpaSpecificationExecutor<Rol>, RolRepositoryCustom {
//USER_EXIT_BEGIN#CUSTOM_METHODS#

//USER_EXIT_END#CUSTOM_METHODS#
}
