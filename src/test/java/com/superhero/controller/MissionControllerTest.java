package com.superhero.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.model.Hero;
import com.superhero.model.Mission;
import com.superhero.service.HeroService;
import com.superhero.service.MissionService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MissionService missionService;




    @Test
    public void whenPostMission_thenCreateMission() throws Exception{
        List<String> Hereos = Arrays.asList("test1", "test2", "test3");
        Mission mission1 = new Mission("4","test4", true, false, Hereos);
        given(missionService.saveMission(Mockito.any(Mission.class))).willReturn(mission1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(mission1);
        mockMvc.perform(
                post("/api/v1/missions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mission1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.missionName", is(mission1.getMissionName())));
    }
}
