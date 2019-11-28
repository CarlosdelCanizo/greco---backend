package com.greco.service;

import com.greco.dtos.ChallengesInfoDto;
import com.greco.model.Users;
import java.util.List;

public interface ChallengeService {
    List<ChallengesInfoDto> getMyChallengeInfo(Long userId);
    void subscribe(Users user, Long challengeId);
}
