package com.greco.service;

import com.greco.model.SolarPanel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SolarPanelService {
    SolarPanel findById(Long id);
    SolarPanel insert(SolarPanel solarPanel);
    SolarPanel update(SolarPanel solarPanel);
    Page<SolarPanel> findAll(Specification<SolarPanel> specs, Pageable pageable);
}
