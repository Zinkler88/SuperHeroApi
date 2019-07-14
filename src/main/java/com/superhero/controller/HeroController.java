package com.superhero.controller;

        import com.superhero.model.Hero;
        import com.superhero.model.Mission;
        import com.superhero.service.HeroService;
        import com.superhero.utils.ApiResponse;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1/heroes")

public class HeroController {

    @Autowired
    private HeroService heroService;


    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Hero>> findAllHeroes() {
        List<Hero> result = heroService.findAll();
        return new ResponseEntity<List<Hero>>(result, HttpStatus.OK);
    }

    @GetMapping("/{heroId}")
    public ResponseEntity<Hero> getHeroById(@PathVariable String heroId){
        Hero result = heroService.getById(heroId);
        return new ResponseEntity<Hero>(result, HttpStatus.OK);
    }

    @GetMapping("/{heroId}/missions")
    public ResponseEntity<Set<Mission>> getHeroMissions(@PathVariable String heroId){
        Set<Mission> result = heroService.getHeroMissions(heroId);
        return new ResponseEntity<Set<Mission>>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Hero> createHero(@Valid @RequestBody Hero hero) {
        Hero result = heroService.save(hero);
        return new ResponseEntity<Hero>(result, HttpStatus.CREATED);
    }


    @PutMapping("/{heroId}")
    public ResponseEntity<Hero> updateHero(@PathVariable String heroId, @Valid @RequestBody Hero hero) {
        Hero result = heroService.update(heroId, hero);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{heroId}/missions/{missionId}")
    public ResponseEntity<Hero> updateHeroMission(@PathVariable String heroId, @PathVariable String missionId, @Valid @RequestBody Mission mission) {
        Hero result = heroService.updateHeroMission(heroId, missionId ,mission);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping("/{heroId}/{missionId}")
    public ResponseEntity<Hero> addMissionToHero(@PathVariable String heroId, @PathVariable String missionId) {
        Hero result = heroService.addMissionToHero(heroId, missionId);
        return new ResponseEntity<Hero>(result, HttpStatus.CREATED);
    }


    @PostMapping("/{heroId}/missions")
    public ResponseEntity<Hero> addMissionsToHero(@PathVariable String heroId, @Valid @RequestBody Mission mission) {
        Hero result = heroService.addMissionsToHero(heroId, mission);
        return new ResponseEntity<Hero>(result, HttpStatus.CREATED);
    }


    @DeleteMapping("/{heroId}")
    public ResponseEntity<Void> deleteHero(@PathVariable String heroId) {
        heroService.delete(heroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{heroId}/missions/{missionId}")
    public ApiResponse removeMissionFromHero(@PathVariable String heroId, @PathVariable String missionId) {
         return heroService.softDelete(heroId, missionId);
       //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

