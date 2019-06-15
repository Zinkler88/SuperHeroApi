package com.superhero.repository;

import com.superhero.model.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MissionRepository extends MongoRepository<Mission, String> {
}
