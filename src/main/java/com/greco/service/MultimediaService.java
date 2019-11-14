package com.greco.service;

import com.greco.model.Multimedia;
import java.util.List;

public interface MultimediaService {
    Multimedia findById(Long id);
    List<Multimedia> findBySolarPanelId(Long solarPanelId);
    List<Multimedia> findBySolarPanelIdAndName(Long solarPanelId, String name);
    Multimedia insert(Multimedia multimedia);
    void deleteById(Long id);
    String getFolderNameFromMultimedia(Multimedia multimedia);
}
