package com.superhero.service;


import com.superhero.model.Mission;
import com.superhero.repository.HeroRepository;
import com.superhero.repository.MissionRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MissionServiceTest {


    @MockBean
    private MissionRepository missionRepository;

    @Autowired
    private MissionService missionService;

    @TestConfiguration
    static class HeroServiceContextConfiguration {
        @Bean
        public HeroService heroService() {
            return new HeroService();
        }
    }
}
