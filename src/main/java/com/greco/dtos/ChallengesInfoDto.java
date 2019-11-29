package com.greco.dtos;

import java.util.List;

public class ChallengesInfoDto {
    private Long id;
    private String challengeName;
    private String challengeDescription;
    private String status;
    private Double percentageCompleted;
    private List<ChallengeLevelDto> challengeLevels;
    private String challengeImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(Double percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    public List<ChallengeLevelDto> getChallengeLevels() {
        return challengeLevels;
    }

    public void setChallengeLevels(List<ChallengeLevelDto> challengeLevels) {
        this.challengeLevels = challengeLevels;
    }

    public String getChallengeImage() {
        return challengeImage;
    }

    public void setChallengeImage(String challengeImage) {
        this.challengeImage = challengeImage;
    }
}
