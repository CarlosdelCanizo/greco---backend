package com.greco.service.impl;

import com.greco.model.RegistrationSolarPanel;
import com.greco.repository.RegistrationSolarPanelRepository;
import com.greco.service.RegistrationSolarPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registrationSolarPanelService")
public class RegistrationSolarPanelServiceImpl implements RegistrationSolarPanelService {

    @Autowired
    private RegistrationSolarPanelRepository registrationSolarPanelRepository;

    @Override
    public RegistrationSolarPanel insert(RegistrationSolarPanel registrationSolar) {
        return registrationSolarPanelRepository.save(registrationSolar);
    }
}
