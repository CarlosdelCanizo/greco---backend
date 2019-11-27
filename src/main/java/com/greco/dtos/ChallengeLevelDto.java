package com.greco.dtos;

public class ChallengeLevelDto {
    private String levelName;
    private int orderInChallenge;
    private boolean isComplete;
    private String levelImage;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getOrderInChallenge() {
        return orderInChallenge;
    }

    public void setOrderInChallenge(int orderInChallenge) {
        this.orderInChallenge = orderInChallenge;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getLevelImage() {
        return levelImage;
    }

    public void setLevelImage(String levelImage) {
        this.levelImage = levelImage;
    }
}
