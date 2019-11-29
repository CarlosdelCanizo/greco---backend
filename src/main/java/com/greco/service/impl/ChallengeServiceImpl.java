package com.greco.service.impl;

import com.greco.dtos.ChallengeLevelDto;
import com.greco.dtos.ChallengesInfoDto;
import com.greco.model.Users;
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

    @Override
    public void subscribe(Users user, Long challengeId) {
        // TODO add logic
    }

    private List<ChallengesInfoDto> getMockInfo() {
        List<ChallengesInfoDto> challengesInfoDto = new ArrayList<>();
        ChallengesInfoDto challengeBeAnInfluencer = getChallengeBeAnInfluencer();
        ChallengesInfoDto numberOfSolarPanel = getChallengeNumberOfSolarPanels();
        challengesInfoDto.add(challengeBeAnInfluencer);
        challengesInfoDto.add(numberOfSolarPanel);
        return challengesInfoDto;
    }

    private ChallengesInfoDto getChallengeBeAnInfluencer() {
        ChallengesInfoDto challengesInfoDto = new ChallengesInfoDto();
        challengesInfoDto.setId(1L);
        challengesInfoDto.setChallengeName("Be an influencer");
        challengesInfoDto.setChallengeDescription("Invite friends and expand community");
        challengesInfoDto.setPercentageCompleted(66D);
        challengesInfoDto.setStatus("OPEN");
        challengesInfoDto.setChallengeImage("beAnInfluencer.jpg");
        List<ChallengeLevelDto> levels = getLevelsOfBeAnInfluencer();
        challengesInfoDto.setChallengeLevels(levels);
        return challengesInfoDto;
    }

    private List<ChallengeLevelDto> getLevelsOfBeAnInfluencer() {
        List<ChallengeLevelDto> levels = new ArrayList<>();
        ChallengeLevelDto levelBronze = new ChallengeLevelDto();
        levelBronze.setLevelName("Influencer de bronce");
        levelBronze.setComplete(true);
        levelBronze.setOrderInChallenge(1);
        levelBronze.setLevelImage("bronzeLevel.jpg");
        levels.add(levelBronze);

        ChallengeLevelDto levelSilver= new ChallengeLevelDto();
        levelSilver.setLevelName("Influencer de plata");
        levelSilver.setComplete(true);
        levelSilver.setOrderInChallenge(2);
        levelSilver.setLevelImage("silverLevel.jpg");
        levels.add(levelSilver);

        ChallengeLevelDto levelGold= new ChallengeLevelDto();
        levelGold.setLevelName("Influencer de oro");
        levelGold.setComplete(false);
        levelGold.setOrderInChallenge(3);
        levelGold.setLevelImage("goldLevel.jpg");
        levels.add(levelGold);
        return levels;
    }

    private ChallengesInfoDto getChallengeNumberOfSolarPanels() {
        ChallengesInfoDto challengesInfoDto = new ChallengesInfoDto();
        challengesInfoDto.setId(2L);
        challengesInfoDto.setChallengeName("Número de placas");
        challengesInfoDto.setChallengeDescription("Share your solar panels");
        challengesInfoDto.setPercentageCompleted(0D);
        challengesInfoDto.setStatus("NEW");
        challengesInfoDto.setChallengeImage("numSolarPanels.jpg");
        List<ChallengeLevelDto> levels = getLevelsOfNumSolarPanels();
        challengesInfoDto.setChallengeLevels(levels);
        return challengesInfoDto;
    }

    private List<ChallengeLevelDto> getLevelsOfNumSolarPanels() {
        List<ChallengeLevelDto> levels = new ArrayList<>();
        ChallengeLevelDto levelOneSolarPanel = new ChallengeLevelDto();
        levelOneSolarPanel.setLevelName("One solar panel");
        levelOneSolarPanel.setComplete(false);
        levelOneSolarPanel.setOrderInChallenge(1);
        levelOneSolarPanel.setLevelImage("oneSolarPanel.jpg");
        levels.add(levelOneSolarPanel);

        ChallengeLevelDto levelFiveSolarPanel= new ChallengeLevelDto();
        levelFiveSolarPanel.setLevelName("Five solar panel");
        levelFiveSolarPanel.setComplete(false);
        levelFiveSolarPanel.setOrderInChallenge(2);
        levelFiveSolarPanel.setLevelImage("fiveSolarPanel.jpg");
        levels.add(levelFiveSolarPanel);

        ChallengeLevelDto levelTenSolarPanel= new ChallengeLevelDto();
        levelTenSolarPanel.setLevelName("Ten solar panel");
        levelTenSolarPanel.setComplete(false);
        levelTenSolarPanel.setOrderInChallenge(3);
        levelTenSolarPanel.setLevelImage("tenSolarPanel.jpg");
        levels.add(levelTenSolarPanel);
        return levels;
    }

}
