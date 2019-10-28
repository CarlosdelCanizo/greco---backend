package com.greco.repository;

import com.greco.model.Users;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


//USER_EXIT_BEGIN#IMPORTS#

//USER_EXIT_END#IMPORTS#

public interface UsersRepository extends PagingAndSortingRepository<Users, Long>, JpaSpecificationExecutor<Users>, UsersRepositoryCustom {
//USER_EXIT_BEGIN#CUSTOM_METHODS#
Users findByUsername(String userName);
//USER_EXIT_END#CUSTOM_METHODS#
}
