package com.superhero.service;

import com.superhero.model.Hero;
import com.superhero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;




        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.NoSuchElementException;

@Service
public class HeroService {


    @Autowired
    private HeroRepository heroRepository;

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


        return heroRepository.findById(id).get();
    }


    public Hero save(Hero hero) {
         /*if(heroRepository.findBySuperheroname(hero.getSuperheroname()) != null) {
            throw new ConflictException("Another record with the same record heroname exists");
        } */
        return heroRepository.insert(hero);
    }


    public void delete(String id) {
        heroRepository.deleteById(id);
    }


}
