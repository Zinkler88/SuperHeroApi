package com.superhero.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Document(collection="hero")
public class Hero {

    @Id
    private String id;

    @NotNull(message= "firstname is required")
    @Size(min=2, message = "firstname should be at least 2 char")
    private String Firstname;

    @NotNull(message = "lastname is required")
    private String Lastname;
    private String Superheroname;
    private Set<Mission> Missions = new HashSet<>();
    private long timestamp;

    public Hero() {
        this.timestamp =System.currentTimeMillis();
    }
    public Hero(String id ,String Firstname, String Lastname, String Superheroname, List<Mission> Missions) {
        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Superheroname = Superheroname;
        this.Missions = new HashSet<>(Missions);
        this.timestamp = System.currentTimeMillis();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getSuperheroname() {
        return Superheroname;
    }

    public void setSuperheroname(String superheroname) {
        Superheroname = superheroname;
    }

    public Set<Mission> getMissions() {
        return Missions;
    }

    public void setMissions(Set<Mission> missions) {
        Missions = missions;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void addMission(Mission mission) {

            this.Missions.add(mission);
    }

    public void removeMission(Mission mission){
        this.Missions.remove(mission);
    }
}

