package com.superhero.service;


import com.superhero.error.NotFoundException;
import com.superhero.model.Mission;
import com.superhero.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    /**
     *
     * @return
     */
    public List<Mission> findAllMissions(){
        return missionRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Mission getMissionById(String id) {
        try {
            return missionRepository.findById(id).get();
        }   catch(NoSuchElementException ex) {
            throw new NotFoundException(String.format("No record with the id [%s] was found in our database", id));
        }
    }

    /**
     *
     * @param mission
     * @return
     */
    public Mission saveMission(Mission mission){
        return missionRepository.insert(mission);
    }


    public Mission updateMission(String missionId, Mission mission) {
        return missionRepository.save(mission);
    }

    /**
     *
     * @param id
     */
    public void deleteMission(String id){

        try {
            missionRepository.deleteById(id);
        }   catch(NoSuchElementException ex) {
            throw new NotFoundException(String.format("No record with the id [%s] was found in our database", id));
        }

    }
}
