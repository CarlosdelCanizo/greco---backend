package com.greco.repository;

import com.greco.model.RegistrationSolarPanel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegistrationSolarPanelRepository extends PagingAndSortingRepository<RegistrationSolarPanel, Long>, JpaSpecificationExecutor<RegistrationSolarPanel> {
    RegistrationSolarPanel findBySolarPanelId(Long solarPanelId);
}
