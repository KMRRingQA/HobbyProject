package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Window;
import com.qa.domain.Manufacturer;
import com.qa.dto.WindowDTO;
import com.qa.repo.WindowsRepository;
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
public class WindowControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private WindowsRepository repo;

    @Autowired
    private ManufacturerRepository repoManu;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ModelMapper modelMapper;

    private long id;

    private long manId;

    private Window testWindow;

    private Manufacturer testManufacturer;

    private Manufacturer testManufacturerWithID;

    private Window testWindowWithID;

    @Before
    public void init() {
        this.repo.deleteAll();

        this.testWindow = new Window("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
        this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

        this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
        this.testWindow.setManufacturer(testManufacturer);
        this.testWindowWithID = this.repo.save(this.testWindow);

        this.manId = this.testManufacturerWithID.getId();
        this.id = this.testWindowWithID.getId();
    }

    @Test
    public void testCreateWindow() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/createWindow").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testWindow)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testWindowWithID, WindowDTO.class)), result);
    }

    @Test
    public void testDeleteWindow() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/deleteWindow/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetWindow() throws Exception {
        String content = this.mock
                .perform(request(HttpMethod.GET, "/getWindowById/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(testWindowWithID, WindowDTO.class)), content);
    }

    @Test
    public void testGetAllWindows() throws Exception {
        List<WindowDTO> windowList = new ArrayList<>();
        windowList.add(modelMapper.map(testWindowWithID, WindowDTO.class));

        String content = this.mock.perform(request(HttpMethod.GET, "/getAllWindows").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(windowList), content);
    }

    @Test
    public void testUpdateWindow() throws Exception {
        Window newWindow = new Window("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
        Window updatedWindow = new Window(newWindow.getTitle(), newWindow.getDescription(), newWindow.getBwf(), newWindow.getThermalResistance(),newWindow.getDimensions(), newWindow.getCost());
        updatedWindow.setManufacturer(testManufacturer);
        newWindow.setManufacturer(testManufacturer);
        updatedWindow.setId(this.id);
        newWindow.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/updateWindow/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newWindow)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(modelMapper.map(updatedWindow, WindowDTO.class)), result);
    }

}
