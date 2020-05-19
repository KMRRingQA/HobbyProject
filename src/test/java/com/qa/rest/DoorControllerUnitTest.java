package com.qa.rest;

import com.qa.domain.Door;
import com.qa.dto.DoorDTO;
import com.qa.service.DoorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoorControllerUnitTest {

    @InjectMocks
    private DoorController controller;

    @Mock
    private DoorService service;

    private List<Door> doorList;

    private Door testDoor;

    private Door testDoorWithID;

    private DoorDTO doorDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private DoorDTO mapToDTO(Door door) {
        return this.mapper.map(door, DoorDTO.class);
    }


    @Before
    public void init() {
        this.doorList = new ArrayList<>();
        this.testDoor = new Door("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
        this.doorList.add(testDoor);
        this.testDoorWithID = new Door(testDoor.getTitle(), testDoor.getDescription(), testDoor.getBwf(), testDoor.getThermalResistance(),testDoor.getDimensions(), testDoor.getCost());
        this.testDoorWithID.setId(id);
        this.doorDTO = this.mapToDTO(testDoorWithID);
    }

    @Test
    public void createDoorTest() {
        when(this.service.createDoor(testDoor)).thenReturn(this.doorDTO);

        assertEquals(new ResponseEntity<DoorDTO>(this.doorDTO, HttpStatus.CREATED), this.controller.createDoor(testDoor));
        verify(this.service, times(1)).createDoor(this.testDoor);
    }

    @Test
    public void deleteDoorTest() {
        this.controller.deleteDoor(id);

        verify(this.service, times(1)).deleteDoor(id);
    }

    @Test
    public void findDoorByIDTest() {
        when(this.service.findDoorById(this.id)).thenReturn(this.doorDTO);
        assertEquals(new ResponseEntity<DoorDTO>(this.doorDTO, HttpStatus.OK), this.controller.getDoorById(this.id));

        verify(this.service, times(1)).findDoorById(this.id);
    }

    @Test
    public void getAllDoorsTest() {

        when(service.readDoors()).thenReturn(this.doorList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found no doors", this.controller.getAllDoors().getBody().isEmpty());

        verify(service, times(1)).readDoors();
    }

    @Test
    public void updateDoorsTest() {
        Door newDoor = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
        Door updatedDoor = new Door(newDoor.getTitle(), newDoor.getDescription(), newDoor.getBwf(), newDoor.getThermalResistance(),newDoor.getDimensions(), newDoor.getCost());
        updatedDoor.setId(this.id);

        when(this.service.updateDoor(this.id, newDoor)).thenReturn(this.mapToDTO(updatedDoor));

        assertEquals(new ResponseEntity<DoorDTO>(this.mapToDTO(updatedDoor), HttpStatus.OK), this.controller.updateDoor(this.id, newDoor));

        verify(this.service, times(1)).updateDoor(this.id, newDoor);
    }

}