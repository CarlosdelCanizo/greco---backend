package com.greco.service.impl;

import com.greco.dtos.ChallengesInfoDto;
import com.greco.service.ChallengeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public List<ChallengesInfoDto> getMyChallengeInfo(Long userId) {
        // TODO add logic
        return getMockInfo();
    }

    private List<ChallengesInfoDto> getMockInfo() {
        List<ChallengesInfoDto> challengesInfoDto = new ArrayList<>();
        challengesInfoDto.add(new ChallengesInfoDto());
        return challengesInfoDto;
    }
}
