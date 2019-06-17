package com.superhero.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@Document(collection="mission")
public class Mission {

    @Id
    private String id;

    private String MissionName;
    private Boolean IsCompleted;
    private Boolean IsDeleted;
    private List<String> Heroes;


    public Mission(String id, String MissionName, boolean IsCompleted, boolean IsDeleted, List<String> Heroes) {
        this.MissionName = MissionName;
        this.IsCompleted = IsCompleted;
        this.IsDeleted = IsDeleted;
        this.Heroes = Heroes;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMissionName() {
        return MissionName;
    }

    public void setMissionName(String missionName) {
        MissionName = missionName;
    }

    public Boolean isCompleted() {
        return IsCompleted;
    }

    public void setCompleted(Boolean completed) {
        IsCompleted = completed;
    }

    public Boolean isDeleted() {
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
