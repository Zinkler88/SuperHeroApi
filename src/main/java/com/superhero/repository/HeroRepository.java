package com.superhero.repository;



import com.superhero.model.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {
    Hero findBySuperheroname(String superheroname);


    @Query("{'mission.MissionName': ?0}")
    List<Hero> findByMissions(final String MissionName);
}
