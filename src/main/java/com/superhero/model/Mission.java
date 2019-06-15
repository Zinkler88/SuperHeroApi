package com.superhero.model;

import java.util.List;



public class Mission {
    private String MissionName;
    private Boolean IsCompleted;
    private Boolean IsDeleted;
    private List<String> Heroes;


    public Mission(String MissionName, boolean IsCompleted, boolean IsDeleted, List<String> Heroes) {
        this.MissionName = MissionName;
        this.IsCompleted = IsCompleted;
        this.IsDeleted = IsDeleted;
        this.Heroes = Heroes;
    }

    public String getMissionName() {
        return MissionName;
    }

    public void setMissionName(String missionName) {
        MissionName = missionName;
    }

    public Boolean getCompleted() {
        return IsCompleted;
    }

    public void setCompleted(Boolean completed) {
        IsCompleted = completed;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    public List<String> getHeroes() {
        return Heroes;
    }

    public void setHeroes(List<String> heroes) {
        Heroes = heroes;
    }
}
