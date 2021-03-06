package com.greco.service;

import com.greco.model.RegistrationSolarPanel;

public interface RegistrationSolarPanelService {
    RegistrationSolarPanel insert(RegistrationSolarPanel registrationSolarPanel);
    RegistrationSolarPanel findBySolarPanelId(Long solarPanelId);
    void deleteById(Long id);
}
