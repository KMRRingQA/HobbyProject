package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.ManufacturerDTO;
import com.qa.domain.Manufacturer;
import com.qa.repo.ManufacturerRepository;
import com.qa.service.ManufacturerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufacturerServiceIntegrationTest {

	@Autowired
	private ManufacturerService service;

	@Autowired
	private ManufacturerRepository repo;

	private Manufacturer testManufacturer;

	private Manufacturer testManufacturerWithID;

	@Autowired
	private ModelMapper mapper;

	private ManufacturerDTO mapToDTO(Manufacturer manufacturer) {
		return this.mapper.map(manufacturer, ManufacturerDTO.class);
	}

	@Before
	public void init() {
		this.testManufacturer = new Manufacturer("test", "test@email.com", "password");

		this.repo.deleteAll();
		this.testManufacturerWithID = this.repo.save(this.testManufacturer);
	}


	@Test
	public void testDeleteManufacturer() {
		assertThat(this.service.deleteManufacturer(this.testManufacturerWithID.getId())).isFalse();
	}

}