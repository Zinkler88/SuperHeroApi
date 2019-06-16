package com.superhero.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.model.Hero;
import com.superhero.model.Mission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HeroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroService heroService;

    @Test
    public void testGetAllHeroes() throws Exception {
        mockMvc.perform(get("/api/v1/heroes")).andExpect(status().isOk());
    }



    @Test
    public void whenGetAllHeroes_thenReturnJsonArray() throws Exception {

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

        given(heroService.findAll()).willReturn(hero);
        assertThat(heroService.findAll()).hasSize(4).contains(hero1, hero2);
        mockMvc.perform(
                get("/api/v1/heroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].firstname", equalTo(hero1.getFirstname())));
    }


    @Test
    public void whenPostHero_thenCreateHero() throws Exception{
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");
        List<Mission> Missions =  new ArrayList<>(Arrays.asList(
                new Mission("1", "test1", true, false, Hereos),
                new Mission("2", "test2", true, false, Hereos),
                new Mission("3","test3", true, false, Hereos),
                new Mission("4","test4", true, false, Hereos)));


        Hero hero1 = new Hero("1", "Hero 1", "Hero 1", "SuperHero 1", Missions);
        given(heroService.save(Mockito.any(Hero.class))).willReturn(hero1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(hero1);
        mockMvc.perform(
                    post("/api/v1/heroes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(hero1)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.firstname", is(hero1.getFirstname())));
    }
}
