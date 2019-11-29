package com.greco.repository;

import com.greco.model.Challenge;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChallengeRepository extends PagingAndSortingRepository<Challenge, Long>, JpaSpecificationExecutor<Challenge> {

}
