package com.superhero.service;

import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.superhero.error.NotFoundException;
import com.superhero.model.Hero;
import com.superhero.model.Mission;
import com.superhero.repository.HeroRepository;
import com.superhero.repository.MissionRepository;
import com.superhero.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class HeroService {


    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private MissionService missionService;

    // private List<String> Hereos = Arrays.asList("test1", "test2", "test3");
    /* private List<Mission> Missions =  new ArrayList<>(Arrays.asList(
            new Mission("test1", true, false, Hereos),
            new Mission("test2", true, false, Hereos),
            new Mission("test3", true, false, Hereos),
            new Mission("test4", true, false, Hereos)));


    private List<Hero> data = new ArrayList<>(Arrays.asList(
            new Hero(1,"test1", "test1", "test1", Missions),
            new Hero(2,"test2", "test2", "test2", Missions),
            new Hero(3,"test3", "test3", "test2", Missions),
            new Hero(4,"test4", "test4", "test2", Missions))); */

    /**
     * Get All Heroes
     * @return List of Heroes
     */

    public List<Hero> findAll(){
        return heroRepository.findAll();
    }

    public Hero getById(String id){
        try {
            return heroRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", id));
        }
    }


    public Hero save(Hero hero) {
           /*if(heroRepository.findBySuperheroname(hero.getSuperheroname()) != null)
         {
            throw new ConflictException("Another record with the same record heroname exists");
         } */
        return heroRepository.insert(hero);
    }


    public Hero update(String HeroId, Hero newHero ) {
        try {
            heroRepository.findById(HeroId).map(hero -> {
                hero.setFirstname(newHero.getFirstname());
                hero.setLastname(newHero.getLastname());
                hero.setSuperheroname(newHero.getSuperheroname());
                hero.setMissions(newHero.getMissions());
            return heroRepository.save(hero);
            });
        }
        catch (NoSuchElementException ex){
            throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", HeroId));
        }
        return null;
    }


    public void delete(String id) {
        try {
             heroRepository.deleteById(id);
        } catch (NoSuchElementException ex) {
            throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", id));
        }
    }

    public Hero addMissionToHero(String heroId, String missionId) {
        Hero hero = this.getById(heroId);
        Mission mission = missionService.getMissionById(missionId);
        hero.addMission(mission);
        return heroRepository.save(hero);
    }
    public Hero addMissionsToHero(String heroId, Mission mission) {
        Hero hero = this.getById(heroId);
        hero.addMission(mission);
        return heroRepository.save(hero);
    }

    public Set<Mission> getHeroMissions(String heroId) {
        Hero hero = this.getById(heroId);
        Set<Mission> missions = hero.getMissions();
        return missions;
    }

    // get hero mission
    public Mission getHeroMission(String heroId , String missionId) {
        Set<Mission> missions = getHeroMissions(heroId);
        for(Mission mission: missions) {
            if(mission.id.equals(missionId)) {
               return mission;
            }
        }
        return null;
    }

    // update hero mission
    public Hero updateHeroMission(String heroId, String missionId, Mission newMission) {
        Mission mission = getHeroMission(heroId, missionId);
            mission.setDeleted(newMission.isDeleted());
            mission.setCompleted(newMission.isCompleted());
            mission.setMissionName(newMission.getMissionName());
            mission.setHeroes(newMission.getHeroes());
            Hero hero = getById(heroId);
        return update(heroId, hero);
    }


   // hard delete from DB
    public ApiResponse deleteMission(String missionId) {
            Mission mission = missionService.getMissionById(missionId);
            if(mission.isCompleted()){
                return new ApiResponse(false, "Unable to remove a completed mission");
            }
            missionRepository.deleteById(missionId);
            return new ApiResponse(true, "Mission removed from Hero");

    }



    // soft delete
    public ApiResponse softDelete(String heroId, String missionId){
        Mission mission = missionService.getMissionById(missionId);
        if(mission.isCompleted()) {
            return new ApiResponse(false, "Unable to remove a completed mission");
        }
        Set<Mission> missions = getHeroMissions(heroId);
        for(Mission miss: missions) {
            if(miss.id.equals(missionId)) {
                miss.setDeleted(true);
                missionRepository.save(miss);
                return new ApiResponse(true, "Mission removed from hero");
            }
        }
        return new ApiResponse(false, "something went wrong");
    }


    // delete from Hero
    public ApiResponse DeleteFromHero(String heroId, String missionId){
        Mission mission = missionService.getMissionById(missionId);
        if(mission.isCompleted()) {
            System.out.println("mission   " + mission.isCompleted());
            return new ApiResponse(false, "Unable to remove a completed mission");
        }
        Set<Mission> missions = getHeroMissions(heroId);
        for(Mission miss: missions) {
            if(miss.id.equals(missionId)) {
                Hero hero = getById(heroId);
                Set <Mission> newMissions = missions.stream()
                        .filter(x -> !missionId.equals(x.getId())).collect(Collectors.toSet());
                hero.setMissions(newMissions);
                heroRepository.save(hero);
                return new ApiResponse(true, "Mission removed from hero");
            }
        }
        return new ApiResponse(false, "something went wrong");
    }

}
