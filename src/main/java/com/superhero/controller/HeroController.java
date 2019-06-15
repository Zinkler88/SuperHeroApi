package com.superhero.controller;

        import com.superhero.model.Hero;
        import com.superhero.service.HeroService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroByLastname(@PathVariable String id){
        Hero result = heroService.getById(id);
        return new ResponseEntity<Hero>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Hero> createHero(@Valid @RequestBody Hero hero) {
        Hero result = heroService.save(hero);
        return new ResponseEntity<Hero>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = {"", "/"})
    public ResponseEntity<Void> deleteHero(@PathVariable String id) {
        heroService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}

