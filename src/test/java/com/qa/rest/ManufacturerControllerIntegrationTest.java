package com.qa.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Manufacturer;
import com.qa.repo.ManufacturerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ManufacturerControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ManufacturerRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private long id;

    private Manufacturer testManufacturer;

    private Manufacturer testManufacturerWithID;

    @Before
    public void init() {
        this.repo.deleteAll();
        this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");
        this.testManufacturerWithID = this.repo.save(this.testManufacturer);
        this.id = this.testManufacturerWithID.getId();
    }

    @Test
    public void testCreateManufacturer() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/manufacturer/createManufacturer").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testManufacturer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(testManufacturerWithID), result);
    }

    @Test
    public void testDeleteManufacturer() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/manufacturer/deleteManufacturer/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetManufacturer() throws Exception {
        String content = this.mock
                .perform(request(HttpMethod.GET, "/manufacturer/get/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(this.testManufacturer), content);
    }

    @Test
    public void testGetAllManufacturers() throws Exception {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        manufacturerList.add(this.testManufacturerWithID);

        String content = this.mock.perform(request(HttpMethod.GET, "/manufacturer/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(manufacturerList), content);
    }

    @Test
    public void testUpdateManufacturer() throws Exception {
        Manufacturer newManufacturer = new Manufacturer("test2","test@2.com","testpass2");
        Manufacturer updatedManufacturer = new Manufacturer(newManufacturer.getName(),newManufacturer.getEmail(),newManufacturer.getPassword());
        updatedManufacturer.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/manufacturer/updateManufacturer/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newManufacturer)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(updatedManufacturer), result);
    }

}
