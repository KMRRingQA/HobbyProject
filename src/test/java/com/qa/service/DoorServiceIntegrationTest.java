package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.qa.domain.Manufacturer;
import com.qa.repo.ManufacturerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.DoorDTO;
import com.qa.domain.Door;
import com.qa.repo.DoorsRepository;
import com.qa.service.DoorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoorServiceIntegrationTest {

	@Autowired
	private DoorService service;

	@Autowired
	private DoorsRepository repo;

	private Door testDoor;

	private Door testDoorWithID;

	private long manId;

	@Autowired
	private ManufacturerRepository repoManu;

	private Manufacturer testManufacturer;

	private Manufacturer testManufacturerWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private DoorDTO mapToDTO(Door door) {
		return this.mapper.map(door, DoorDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testDoor = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
		this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

		this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
		this.testDoor.setManufacturer(testManufacturer);
		this.testDoorWithID = this.repo.save(this.testDoor);
	}
	
	@Test
	public void testCreateDoor() {
		assertEquals(service.mapToDTO(this.testDoorWithID), this.service.createDoor(testDoor));
	}

	@Test
	public void testDeleteDoor() {
		assertThat(this.service.deleteDoor(this.testDoorWithID.getId())).isFalse();
	}

	@Test
	public void testFindDoorByID() {
		assertThat(this.service.findDoorById(this.testDoorWithID.getId())).isEqualTo(service.mapToDTO(this.testDoorWithID));
	}

	@Test
	public void testReadDoors() {
		assertThat(this.service.readDoors()).isEqualTo(Stream.of(service.mapToDTO(testDoorWithID)).collect(Collectors.toList()));
	}

	@Test
	public void testUpdateDoor() {
		Door newDoor = new Door("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
		Door updatedDoor = new Door(testDoor.getTitle(), testDoor.getDescription(), testDoor.getBwf(), testDoor.getThermalResistance(),testDoor.getDimensions(), testDoor.getCost());

		updatedDoor.setManufacturer(testManufacturer);
		newDoor.setManufacturer(testManufacturer);

		updatedDoor.setId(this.testDoorWithID.getId());
		newDoor.setId(this.testDoorWithID.getId());

		assertThat(this.service.updateDoor(this.testDoorWithID.getId(), newDoor)).isEqualTo(service.mapToDTO(newDoor));
	}

}