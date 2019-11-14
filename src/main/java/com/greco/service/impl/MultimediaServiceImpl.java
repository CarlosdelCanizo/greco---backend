package com.greco.service.impl;

import com.greco.exception.NotFoundException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Multimedia;
import com.greco.repository.MultimediaRepository;
import com.greco.service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("multimediaService")
public class MultimediaServiceImpl implements MultimediaService {
    @Autowired
    private MultimediaRepository multimediaRepository;
    private final String UPLOAD_FOLDER = "./uploads/";

    @Override
    public Multimedia findById(Long id) {
        Optional<Multimedia> optional = multimediaRepository.findById(id);
        if(!optional.isPresent())
            throw new NotFoundException(GenericCheckingMessage.ENTITY_NOT_FOUND.toString());
        return optional.get();
    }

    @Override
    public List<Multimedia> findBySolarPanelId(Long solarPanelId) {
        return multimediaRepository.findBySolarPanelId(solarPanelId);
    }

    @Override
    public List<Multimedia> findBySolarPanelIdAndName(Long solarPanelId, String name) {
        return multimediaRepository.findBySolarPanelIdAndName(solarPanelId, name);
    }

    @Override
    public Multimedia insert(Multimedia multimedia){
        return multimediaRepository.save(multimedia);
    }

    @Override
    public void deleteById(Long id) {
        multimediaRepository.deleteById(id);
    }

    @Override
    public String getFolderNameFromMultimedia(Multimedia multimedia) {
        Long userId = multimedia.getSolarPanel().getRegistrationSolarPanel().getOwner().getUserId();
        Long solarPanelId =  multimedia.getSolarPanel().getId();
        String userFolder = UPLOAD_FOLDER + userId + "/" + solarPanelId + "/";
        return userFolder;
    }

}
