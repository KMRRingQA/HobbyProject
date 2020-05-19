package com.qa.rest;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.service.LiftService;
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
public class LiftControllerUnitTest {

    @InjectMocks
    private LiftController controller;

    @Mock
    private LiftService service;

    private List<Lift> liftList;

    private Lift testLift;

    private Lift testLiftWithID;

    private LiftDTO liftDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private LiftDTO mapToDTO(Lift lift) {
        return this.mapper.map(lift, LiftDTO.class);
    }


    @Before
    public void init() {
        this.liftList = new ArrayList<>();
        this.testLift = new Lift("test", "testStyle", 100, 100,"testDimension", BigDecimal.valueOf(2.99));
        this.liftList.add(testLift);
        this.testLiftWithID = new Lift(testLift.getTitle(), testLift.getDescription(), testLift.getCarryCapacity(), testLift.getMaxSpeed(),testLift.getDimensions(), testLift.getCost());
        this.testLiftWithID.setId(id);
        this.liftDTO = this.mapToDTO(testLiftWithID);
    }

    @Test
    public void createLiftTest() {
        when(this.service.createLift(testLift)).thenReturn(this.liftDTO);

        assertEquals(new ResponseEntity<LiftDTO>(this.liftDTO, HttpStatus.CREATED), this.controller.createLift(testLift));
        verify(this.service, times(1)).createLift(this.testLift);
    }

    @Test
    public void deleteLiftTest() {
        this.controller.deleteLift(id);

        verify(this.service, times(1)).deleteLift(id);
    }

    @Test
    public void findLiftByIDTest() {
        when(this.service.findLiftById(this.id)).thenReturn(this.liftDTO);
        assertEquals(new ResponseEntity<LiftDTO>(this.liftDTO, HttpStatus.OK), this.controller.getLiftById(this.id));

        verify(this.service, times(1)).findLiftById(this.id);
    }

    @Test
    public void getAllLiftsTest() {

        when(service.readLifts()).thenReturn(this.liftList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found no lifts", this.controller.getAllLifts().getBody().isEmpty());

        verify(service, times(1)).readLifts();
    }

    @Test
    public void updateLiftsTest() {
        Lift newLift = new Lift("test2", "test2style", 200,200,"test2Dim",BigDecimal.valueOf(27.99));
        Lift updatedLift = new Lift(newLift.getTitle(), newLift.getDescription(), newLift.getCarryCapacity(), newLift.getMaxSpeed(),newLift.getDimensions(), newLift.getCost());
        updatedLift.setId(this.id);

        when(this.service.updateLift(this.id, newLift)).thenReturn(this.mapToDTO(updatedLift));

        assertEquals(new ResponseEntity<LiftDTO>(this.mapToDTO(updatedLift), HttpStatus.OK), this.controller.updateLift(this.id, newLift));

        verify(this.service, times(1)).updateLift(this.id, newLift);
    }

}