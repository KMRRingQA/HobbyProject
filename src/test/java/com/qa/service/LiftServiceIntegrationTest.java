package com.qa.service;

import com.qa.domain.Manufacturer;
import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.repo.ManufacturerRepository;
import com.qa.repo.LiftsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiftServiceIntegrationTest {

	@Autowired
	private LiftService service;

	@Autowired
	private LiftsRepository repo;

	private Lift testLift;

	private Lift testLiftWithID;

	private long manId;

	@Autowired
	private ManufacturerRepository repoManu;

	private Manufacturer testManufacturer;

	private Manufacturer testManufacturerWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private LiftDTO mapToDTO(Lift lift) {
		return this.mapper.map(lift, LiftDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testLift = new Lift("test2", "test2style", 100,100,"test2Dim", BigDecimal.valueOf(27.99));
		this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

		this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
		this.testLift.setManufacturer(testManufacturer);
		this.testLiftWithID = this.repo.save(this.testLift);
	}
	
	@Test
	public void testCreateLift() {
		assertEquals(this.mapToDTO(this.testLiftWithID), this.service.createLift(testLift));
	}

	@Test
	public void testDeleteLift() {
		assertThat(this.service.deleteLift(this.testLiftWithID.getId())).isFalse();
	}

	@Test
	public void testFindLiftByID() {
		assertThat(this.service.findLiftById(this.testLiftWithID.getId())).isEqualTo(this.mapToDTO(this.testLiftWithID));
	}

	@Test
	public void testReadLifts() {
		assertThat(this.service.readLifts()).isEqualTo(Stream.of(this.mapToDTO(testLiftWithID)).collect(Collectors.toList()));
	}

	@Test
	public void testUpdateLift() {
		Lift newLift = new Lift("test", "testStyle", 200, 200,"testDimension", BigDecimal.valueOf(2.99));
		Lift updatedLift = new Lift(testLift.getTitle(), testLift.getDescription(), testLift.getCarryCapacity(), testLift.getMaxSpeed(),testLift.getDimensions(), testLift.getCost());

		updatedLift.setManufacturer(testManufacturer);
		newLift.setManufacturer(testManufacturer);

		updatedLift.setId(this.testLiftWithID.getId());
		newLift.setId(this.testLiftWithID.getId());

		assertThat(this.service.updateLift(this.testLiftWithID.getId(), newLift)).isEqualTo(this.mapToDTO(newLift));
	}

}