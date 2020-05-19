package com.qa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qa.repo.DoorsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.DoorDTO;
import com.qa.domain.Door;
import com.qa.repo.DoorsRepository;
import com.qa.service.DoorService;

@RunWith(SpringRunner.class)
public class DoorServiceUnitTest {

	@InjectMocks
	private DoorService service;

	@Mock
	private DoorsRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Door> doorList;

	private Door testDoor;

	private Door testDoorWithID;

	private DoorDTO doorDTO;

	final long id = 1L;

	@Before
	public void init() {
		this.doorList = new ArrayList<>();
		this.doorList.add(testDoor);
		this.testDoor = new Door("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
		this.testDoorWithID = new Door(testDoor.getTitle(), testDoor.getDescription(), testDoor.getBwf(), testDoor.getThermalResistance(),testDoor.getDimensions(), testDoor.getCost());
		this.testDoorWithID.setId(id);
		this.doorDTO = new ModelMapper().map(testDoorWithID, DoorDTO.class);
	}

	@Test
	public void createDoorTest() {
		when(this.repo.save(testDoor)).thenReturn(testDoorWithID);
		when(this.mapper.map(testDoorWithID, DoorDTO.class)).thenReturn(doorDTO);

		assertEquals(this.doorDTO, this.service.createDoor(testDoor));

		verify(this.repo, times(1)).save(this.testDoor);
	}

	@Test
	public void deleteDoorTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteDoor(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findDoorByIDTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testDoorWithID));
		when(this.mapper.map(testDoorWithID, DoorDTO.class)).thenReturn(doorDTO);

		assertEquals(this.doorDTO, this.service.findDoorById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readDoorsTest() {

		when(repo.findAll()).thenReturn(this.doorList);
		when(this.mapper.map(testDoorWithID, DoorDTO.class)).thenReturn(doorDTO);

		assertFalse("Controller has found no doors", this.service.readDoors().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateDoorsTest() {
		// given
		Door newDoor = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
		newDoor.setId(this.id);
		
		Door updatedDoor = new Door(newDoor.getTitle(), newDoor.getDescription(), newDoor.getBwf(), newDoor.getThermalResistance(),newDoor.getDimensions(), newDoor.getCost());
		updatedDoor.setId(this.id);
		
		DoorDTO updatedDTO = new ModelMapper().map(updatedDoor, DoorDTO.class);

		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testDoorWithID));
		when(this.mapper.map(updatedDoor, DoorDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Door.java for this to work
		when(this.repo.save(updatedDoor)).thenReturn(updatedDoor);

		assertEquals(updatedDTO, this.service.updateDoor(this.id, newDoor));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedDoor);
	}

}