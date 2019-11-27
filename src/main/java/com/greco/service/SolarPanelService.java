package com.greco.service;

import com.greco.model.SolarPanel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SolarPanelService {
    SolarPanel findById(Long id);
    SolarPanel insert(SolarPanel solarPanel);
    SolarPanel update(SolarPanel solarPanel);
    Page<SolarPanel> findAll(Specification<SolarPanel> specs, Pageable pageable);
    void deleteById(Long id);
    String getFolderNameFromSolarPanel(SolarPanel solarPanel);
    //List<SolarPanel> findByLatitudeAndLongitudeLimits(Double latMin, Double latMax, Double lonMin, Double lonMax);
    Page<SolarPanel> findByLatitudeAndLongitudeLimits(Double latMin, Double latMax, Double lonMin, Double lonMax, Pageable pageable);

}
