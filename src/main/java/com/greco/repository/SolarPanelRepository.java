package com.greco.repository;

import com.greco.model.SolarPanel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolarPanelRepository extends PagingAndSortingRepository<SolarPanel, Long>, JpaSpecificationExecutor<SolarPanel> {

}
