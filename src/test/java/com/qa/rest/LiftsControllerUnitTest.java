//package com.qa.rest;
//
//import com.qa.domain.Lift;
//import com.qa.dto.LiftDTO;
//import com.qa.service.LiftService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class LiftsControllerUnitTest {
//
//    @InjectMocks
//    private LiftController liftsController;
//
//    @Mock
//    private LiftService service;
//
//    private List<Lift> lifts;
//
//    private Lift testLift;
//
//    private Lift testLiftWitId;
//
//    private long id = 1L;
//
//    private LiftDTO liftDTO;
//
//    private final ModelMapper mapper = new ModelMapper();
//
//    private LiftDTO mapToDTO(Lift lift){
//        return this.mapper.map(lift, LiftDTO.class);
//    }
//
////    @Before
////    public void setUp(){
////        this.lifts = new ArrayList<>();
////        this.testLift = new Lift("Test title", "Test description");
////        this.lifts.add(testLift);
////        this.testLiftWitId = new Lift(testLift.getTitle(), testLift.getDescription());
////        this.testLiftWitId.setId(this.id);
////        this.liftDTO = this.mapToDTO(testLiftWitId);
////    }
//
//    @Test
//    public void getAllLiftsTest(){
//        when(service.readLifts()).thenReturn(this.lifts.stream().map(this::mapToDTO).collect(Collectors.toList()));
//        assertFalse("No lifts found", this.liftsController.getAllLifts().getBody().isEmpty());
//        verify(service, times(1)).readLifts();
//    }
//
//    @Test
//    public void createLiftTest(){
//        when(this.service.createLift(testLift)).thenReturn(this.liftDTO);
//        assertEquals(this.liftsController.createLift(testLift), new ResponseEntity<LiftDTO>(this.liftDTO, HttpStatus.CREATED));
//        verify(this.service, times(1)).createLift(testLift);
//    }
//
//    @Test
//    public void deleteLiftTestFalse(){
//        this.liftsController.deleteLift(id);
//        verify(service, times(1)).deleteLift(id);
//    }
//
//
//    @Test
//    public void deleteLiftTestTrue(){
//        when(service.deleteLift(3L)).thenReturn(true);
//        this.liftsController.deleteLift(3L);
//        verify(service, times(1)).deleteLift(3L);
//    }
//
//    @Test
//    public void getLiftByIDTest(){
//        when(this.service.findLiftById(id)).thenReturn(this.liftDTO);
//        assertEquals(this.liftsController.getLiftById(id), new ResponseEntity<LiftDTO>(this.liftDTO, HttpStatus.OK));
//        verify(service, times(1)).findLiftById(id);
//    }
//
//}
