package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Door;
import com.qa.domain.Manufacturer;
import com.qa.dto.DoorDTO;
import com.qa.repo.DoorsRepository;
import com.qa.repo.ManufacturerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
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
public class DoorControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private DoorsRepository repo;

    @Autowired
    private ManufacturerRepository repoManu;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ModelMapper modelMapper;

    private long id;

    private long manId;

    private Door testDoor;

    private Manufacturer testManufacturer;

    private Manufacturer testManufacturerWithID;

    private Door testDoorWithID;

    @Before
    public void init() {
        this.repo.deleteAll();

        this.testDoor = new Door("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
        this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

        this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
        this.testDoor.setManufacturer(testManufacturer);
        this.testDoorWithID = this.repo.save(this.testDoor);

        this.manId = this.testManufacturerWithID.getId();
        this.id = this.testDoorWithID.getId();
    }

    @Test
    public void testCreateDoor() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/createDoor").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testDoor)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testDoorWithID, DoorDTO.class)), result);
    }

    @Test
    public void testDeleteDoor() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/deleteDoor/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetDoor() throws Exception {
        String content = this.mock
                .perform(request(HttpMethod.GET, "/getDoorById/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testDoorWithID, DoorDTO.class)), content);
    }

    @Test
    public void testGetAllDoors() throws Exception {
        List<DoorDTO> doorList = new ArrayList<>();
        doorList.add(modelMapper.map(testDoorWithID, DoorDTO.class));

        String content = this.mock.perform(request(HttpMethod.GET, "/getAllDoors").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(doorList), content);
    }

    @Test
    public void testUpdateDoor() throws Exception {
        Door newDoor = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
        Door updatedDoor = new Door(newDoor.getTitle(), newDoor.getDescription(), newDoor.getBwf(), newDoor.getThermalResistance(),newDoor.getDimensions(), newDoor.getCost());
        updatedDoor.setManufacturer(testManufacturer);
        newDoor.setManufacturer(testManufacturer);
        updatedDoor.setId(this.id);
        newDoor.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/updateDoor/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newDoor)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(updatedDoor, DoorDTO.class)), result);
    }

}
