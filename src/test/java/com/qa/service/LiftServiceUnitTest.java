package com.qa.service;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.repo.LiftsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class LiftServiceUnitTest {

	@InjectMocks
	private LiftService service;

	@Mock
	private LiftsRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Lift> liftList;

	private Lift testLift;

	private Lift testLiftWithID;

	private LiftDTO liftDTO;

	final long id = 1L;

	@Before
	public void init() {
		this.liftList = new ArrayList<>();
		this.liftList.add(testLift);
		this.testLift = new Lift("test", "testStyle", 100, 100,"testDimension", BigDecimal.valueOf(2.99));
		this.testLiftWithID = new Lift(testLift.getTitle(), testLift.getDescription(), testLift.getCarryCapacity(), testLift.getMaxSpeed(),testLift.getDimensions(), testLift.getCost());
		this.testLiftWithID.setId(id);
		this.liftDTO = new ModelMapper().map(testLiftWithID, LiftDTO.class);
	}

	@Test
	public void createLiftTest() {
		when(this.repo.save(testLift)).thenReturn(testLiftWithID);
		when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);

		assertEquals(this.liftDTO, this.service.createLift(testLift));

		verify(this.repo, times(1)).save(this.testLift);
	}

	@Test
	public void deleteLiftTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteLift(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findLiftByIDTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testLiftWithID));
		when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);

		assertEquals(this.liftDTO, this.service.findLiftById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readLiftsTest() {

		when(repo.findAll()).thenReturn(this.liftList);
		when(this.mapper.map(testLiftWithID, LiftDTO.class)).thenReturn(liftDTO);

		assertFalse("Controller has found no lifts", this.service.readLifts().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateLiftsTest() {
		// given
		Lift newLift = new Lift("test2", "test2style", 200,200,"test2Dim",BigDecimal.valueOf(27.99));
		newLift.setId(this.id);
		
		Lift updatedLift = new Lift(newLift.getTitle(), newLift.getDescription(), newLift.getCarryCapacity(), newLift.getMaxSpeed(),newLift.getDimensions(), newLift.getCost());
		updatedLift.setId(this.id);
		
		LiftDTO updatedDTO = new ModelMapper().map(updatedLift, LiftDTO.class);

		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testLiftWithID));
		when(this.mapper.map(updatedLift, LiftDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Lift.java for this to work
		when(this.repo.save(updatedLift)).thenReturn(updatedLift);

		assertEquals(updatedDTO, this.service.updateLift(this.id, newLift));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedLift);
	}

}