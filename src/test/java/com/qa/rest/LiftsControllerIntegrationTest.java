package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.repo.LiftsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LiftsControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private LiftsRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Lift testLift;

    private Lift testLiftWithID;

    private long id;

    private LiftDTO liftDTO;

    private LiftDTO mapToDTO(Lift lift){
        return this.mapper.map(lift, LiftDTO.class);
    }

//    @Before
//    public void setUp(){
//        this.repository.deleteAll();
//        this.testLift = new Lift("test lift", "test description");
//        this.testLiftWithID = this.repository.save(testLift);
//        this.id = testLiftWithID.getId();
//        this.liftDTO = this.mapToDTO(testLiftWithID);
//    }

    @Test
    public void getAllLiftsTest() throws Exception {
        List<LiftDTO> liftDTOList = new ArrayList<>();
        liftDTOList.add(liftDTO);
        String content = this.mock.perform(
            request(HttpMethod.GET, "/getAllLifts")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(liftDTOList));
    }

    @Test
    public void getLiftByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getLiftById/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(liftDTO));
    }

    @Test
    public void createLiftTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createLift")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testLift))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(liftDTO));
    }

    @Test
    public void deleteLiftTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteLift/" + this.id)
        ).andExpect(status().isNoContent());
    }


}
