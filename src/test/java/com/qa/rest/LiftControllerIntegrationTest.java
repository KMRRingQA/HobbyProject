package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Lift;
import com.qa.domain.Manufacturer;
import com.qa.dto.LiftDTO;
import com.qa.repo.LiftsRepository;
import com.qa.repo.ManufacturerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LiftControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private LiftsRepository repo;

    @Autowired
    private ManufacturerRepository repoManu;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ModelMapper modelMapper;

    private long id;

    private long manId;

    private Lift testLift;

    private Manufacturer testManufacturer;

    private Manufacturer testManufacturerWithID;

    private Lift testLiftWithID;

    @Before
    public void init() {
        this.repo.deleteAll();

        this.testLift = new Lift("test", "testStyle", 100, 100,"testDimension", BigDecimal.valueOf(2.99));
        this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

        this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
        this.testLift.setManufacturer(testManufacturer);
        this.testLiftWithID = this.repo.save(this.testLift);

        this.manId = this.testManufacturerWithID.getId();
        this.id = this.testLiftWithID.getId();
    }

    @Test
    public void testCreateLift() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/createLift").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testLift)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testLiftWithID, LiftDTO.class)), result);
    }

    @Test
    public void testDeleteLift() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/deleteLift/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetLift() throws Exception {
        String content = this.mock
                .perform(request(HttpMethod.GET, "/getLiftById/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testLiftWithID, LiftDTO.class)), content);
    }

    @Test
    public void testGetAllLifts() throws Exception {
        List<LiftDTO> liftList = new ArrayList<>();
        liftList.add(modelMapper.map(testLiftWithID, LiftDTO.class));

        String content = this.mock.perform(request(HttpMethod.GET, "/getAllLifts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(liftList), content);
    }

    @Test
    public void testUpdateLift() throws Exception {
        Lift newLift = new Lift("test2", "test2style", 200,200,"test2Dim",BigDecimal.valueOf(27.99));
        Lift updatedLift = new Lift(newLift.getTitle(), newLift.getDescription(), newLift.getCarryCapacity(), newLift.getMaxSpeed(),newLift.getDimensions(), newLift.getCost());
        updatedLift.setManufacturer(testManufacturer);
        newLift.setManufacturer(testManufacturer);
        updatedLift.setId(this.id);
        newLift.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/updateLift/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newLift)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(updatedLift, LiftDTO.class)), result);
    }

}
