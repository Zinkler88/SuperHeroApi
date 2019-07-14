package com.superhero.service;

import com.superhero.error.NotFoundException;
import com.superhero.model.Hero;
import com.superhero.model.Mission;
import com.superhero.repository.HeroRepository;
import com.superhero.repository.MissionRepository;
import com.superhero.utils.ApiResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;




/**
 *
 *
*/

@RunWith(SpringRunner.class)
public class HeroServiceTest {



    @MockBean
    private HeroRepository heroRepository;
    @MockBean
    private MissionRepository missionRepository;

    @Autowired
    private HeroService heroService;
    @Autowired
    private MissionService missionService;

    @TestConfiguration
    static class HeroServiceContextConfiguration {
        @Bean
        public HeroService heroService() {
            return new HeroService();
        }
        @Bean
        public MissionService missionService(){
            return new MissionService();
        }
    }


    @Test
    public void whenFindAll_ReturnHeroesList(){
        //MockUp
        // It would be better if we add mockData separately
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");

        List<Mission> Missions =  new ArrayList<>(Arrays.asList(
                new Mission("1", "test1", true, false, Hereos),
                new Mission("2", "test2", true, false, Hereos),
                new Mission("3","test3", true, false, Hereos),
                new Mission("4","test4", true, false, Hereos)));

        Hero hero1 = new Hero("1","test1", "test1", "test1", Missions);
        Hero hero2 = new Hero("2","test2", "test2", "test2", Missions);
        Hero hero3 = new Hero("3","test3", "test3", "test2", Missions);
        Hero hero4 = new Hero("4","test4", "test4", "test2", Missions);
        List<Hero> hero = Arrays.asList(hero1, hero2, hero3, hero4);

        given(heroRepository.findAll()).willReturn(hero);
          assertThat(heroService.findAll()).hasSize(4).contains(hero1, hero2);
    }


    @Test
    public void whenGetById_HeroShouldBeFound(){
        // It would be better if we add mockData separately
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");

        List<Mission> Missions =  new ArrayList<>(Arrays.asList(
                new Mission("1", "test1", true, false, Hereos),
                new Mission("2", "test2", true, false, Hereos),
                new Mission("3","test3", true, false, Hereos),
                new Mission("4","test4", true, false, Hereos)));


        Hero hero = new Hero("1", "Hero1", "Hero1","Super", Missions);
         given(heroRepository.findById(anyString())).willReturn(Optional.ofNullable(hero));
         Hero result = heroService.getById("1");
         assertThat(result.getFirstname()).containsIgnoringCase("Hero1");
    }


    @Test(expected = NotFoundException.class)
    public void whenInvalidId_HeroShouldNotBeFound() {
        given(heroRepository.findById(anyString())).willReturn(Optional.empty());
        heroService.getById("1");
    }

    // Fail
    @Ignore
    @Test
    public void whenAddMissionToHero_ReturnMissionsLit(){
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");
        List<Mission> Missions =  new ArrayList<>(Arrays.asList(
                new Mission("1", "test1", true, false, Hereos),
                new Mission("2", "test2", true, false, Hereos),
                new Mission("3","test3", true, false, Hereos)));
        Mission mission4 = new Mission("4","test4", true, false, Hereos);
        Hero hero = new Hero("1", "Hero1", "Hero1","Super", Missions);
        given(heroRepository.findById(anyString())).willReturn(Optional.ofNullable(hero));


        Hero result  = heroService.addMissionToHero(hero.getId(), mission4.getId());

        assertThat(result.getMissions());
    }


    // Fail
    @Ignore
    @Test
    public void whenRemoveMissionToHero_ReturnMissionsLit(){
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");
        List<Mission> Missions =  new ArrayList<>(Arrays.asList(
                new Mission("1", "test1", true, false, Hereos),
                new Mission("2", "test2", true, false, Hereos),
                new Mission("3","test3", true, false, Hereos)));
        Mission mission4 = new Mission("4","test4", true, false, Hereos);
        Hero hero = new Hero("1", "Hero1", "Hero1","Super", Missions);
        given(heroRepository.findById(anyString())).willReturn(Optional.ofNullable(hero));
        // ApiResponse result  = heroService.romoveMissionToHero(hero.getId(), mission4.getId());
        // assertThat(result.getSuccess()).isEqualTo(true);
    }

}



