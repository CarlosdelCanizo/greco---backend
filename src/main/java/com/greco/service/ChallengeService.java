package com.greco.service;

import com.greco.dtos.ChallengesInfoDto;
import java.util.List;

public interface ChallengeService {
    List<ChallengesInfoDto> getMyChallengeInfo(Long userId);
}
