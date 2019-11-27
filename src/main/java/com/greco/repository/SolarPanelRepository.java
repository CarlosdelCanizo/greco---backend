package com.greco.repository;

import com.greco.model.SolarPanel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface SolarPanelRepository extends PagingAndSortingRepository<SolarPanel, Long>, JpaSpecificationExecutor<SolarPanel> {
    //List<SolarPanel> findByLatLessThanAndLatGreaterThanAndLonLessThanAndLonGreaterThan(Double latMax, Double latMin, Double lonMax, Double lonMin);
    Page<SolarPanel> findByLatGreaterThanAndLatLessThanAndLonGreaterThanAndLonLessThan(Double latMin, Double latMax, Double lonMin, Double lonMax, Pageable pageable);
}
