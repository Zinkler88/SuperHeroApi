package com.superhero.controller;

        import com.superhero.model.Hero;
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

    @DeleteMapping("/{heroId}")
    public ResponseEntity<Void> deleteHero(@PathVariable String heroId) {
        heroService.delete(heroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{heroId}/{missionId}")
    public ResponseEntity<Hero> addMissionToHero(@PathVariable String heroId, @PathVariable String missionId) {
        Hero result = heroService.addMissionToHero(heroId, missionId);
        return new ResponseEntity<Hero>(result, HttpStatus.CREATED);
    }


    @DeleteMapping("/{heroId}/{missionId}")
    public ApiResponse removeMissionToHero(@PathVariable String heroId, @PathVariable String missionId) {
        return heroService.romoveMissionToHero(heroId, missionId);
    }
}

