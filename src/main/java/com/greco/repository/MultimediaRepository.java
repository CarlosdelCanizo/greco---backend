package com.greco.repository;

import com.greco.model.Multimedia;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface MultimediaRepository extends PagingAndSortingRepository<Multimedia, Long>, JpaSpecificationExecutor<Multimedia> {
    List<Multimedia> findBySolarPanelId(Long solarPanelId);
    List<Multimedia> findBySolarPanelIdAndName(Long solarPanelId, String name);
}
