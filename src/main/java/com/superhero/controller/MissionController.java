package com.superhero.controller;


import com.superhero.model.Hero;
import com.superhero.model.Mission;
import com.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "/api/v1/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;


    @GetMapping(value = {"","/"})
    public List<Mission> getMissions() {
        return missionService.findAllMissions();
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<Mission> getMissionById(@PathVariable String missionId){
        Mission result = missionService.getMissionById(missionId);
        return new ResponseEntity<Mission>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Mission> createHero(@Valid @RequestBody Mission mission) {
        Mission result = missionService.saveMission(mission);
        return new ResponseEntity<Mission>(result, HttpStatus.CREATED);
    }
    @PutMapping("/{missionId}")
    public ResponseEntity<Mission> updateMission(@PathVariable String missionId, @Valid @RequestBody Mission mission){
         Mission result = missionService.updateMission(missionId, mission);
         return new ResponseEntity<Mission>(result, HttpStatus.OK);
    }


    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> deleteMission(@PathVariable String missionId){
        missionService.deleteMission(missionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
