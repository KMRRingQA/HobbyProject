package com.qa.service;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.exceptions.LiftNotFoundException;
import com.qa.repo.LiftsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class LiftServiceUnitTest {

    @InjectMocks
    private LiftService service;

    @Mock
    private LiftsRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Lift> liftList;

    private Lift testLift;

    private long id = 1L;

    private Lift testLiftWithID;

    private LiftDTO liftDTO;

    private LiftDTO mapToDTO(Lift lift){
        return this.mapper.map(lift, LiftDTO.class);
    }

//    @Before
//    public void setUp(){
//        this.liftList = new ArrayList<>();
//        this.testLift = new Lift("Shopping list", "Beer and even more beer");
//        this.liftList.add(testLift);
//        this.testLiftWithID = new Lift(testLift.getTitle(), testLift.getDescription());
//        this.testLiftWithID.setId(id);
//        this.liftDTO = this.mapToDTO(testLiftWithID);
//    }

    @Test
    public void getAllLiftsTest(){
        when(repository.findAll()).thenReturn(this.liftList);
        when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);
        assertFalse("Service returned no Lifts", this.service.readLifts().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createLiftTest(){
        when(repository.save(testLift)).thenReturn(testLiftWithID);
        when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);
        assertEquals(this.service.createLift(testLift), this.liftDTO);
        verify(repository, times(1)).save(this.testLift);
    }

    @Test
    public void findLiftByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testLiftWithID));
        when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);
        assertEquals(this.service.findLiftById(this.id), liftDTO);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void deleteLiftByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true, false);
        assertFalse(service.deleteLift(id));
        verify(repository, times(1)).deleteById(id);
        verify(repository, times(2)).existsById(id);
    }

    @Test(expected = LiftNotFoundException.class)
    public void deleteLiftByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deleteLift(id);
        verify(repository, times(1)).existsById(id);
    }

//    @Test
//    public void updateLiftTest(){
//
//        Lift newLift = new Lift("Favourite movies", "The grinch");
//        Lift updateLift = new Lift(newLift.getTitle(), newLift.getDescription());
//        updateLift.setId(id);
//
//        LiftDTO updateLiftDTO = new ModelMapper().map(updateLift, LiftDTO.class);
//
//        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testLiftWithID));
//        when(this.repository.save(updateLift)).thenReturn(updateLift);
//        when(this.mapper.map(updateLift, LiftDTO.class)).thenReturn(updateLiftDTO);
//
//        assertEquals(updateLiftDTO, this.service.updateLift(id, newLift));
//        verify(this.repository, times(1)).findById(id);
//        verify(this.repository, times(1)).save(updateLift);
//    }

}
