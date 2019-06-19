package com.superhero.service;

import com.superhero.error.ConflictException;
import com.superhero.error.NotFoundException;
import com.superhero.model.Hero;
import com.superhero.model.Mission;
import com.superhero.repository.HeroRepository;
import com.superhero.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;



@Service
public class HeroService {


    @Autowired
    private HeroRepository heroRepository;
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
            System.out.println("getById  "+heroRepository.findById(id).get());
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


    public Hero update(String id, Hero hero ) {
        return heroRepository.save(hero);
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

    public ApiResponse romoveMissionToHero(String heroId, String missionId) {
        Hero hero = this.getById(heroId);
        Mission mission = missionService.getMissionById(missionId);
        if(mission.isCompleted()){
            return new ApiResponse(false, "Unable to remove a completed mission");
        }
        hero.removeMission(mission);
        heroRepository.save(hero);
        return new ApiResponse(true, "Mission removed from Hero");
    }


}
