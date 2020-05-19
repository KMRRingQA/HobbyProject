package com.qa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.ManufacturerDTO;
import com.qa.domain.Manufacturer;
import com.qa.repo.ManufacturerRepository;
import com.qa.service.ManufacturerService;

@RunWith(SpringRunner.class)
public class ManufacturerServiceUnitTest {

	@InjectMocks
	private ManufacturerService service;

	@Mock
	private ManufacturerRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Manufacturer> manufacturerList;

	private Manufacturer testManufacturer;

	private Manufacturer testManufacturerWithID;

	private ManufacturerDTO manufacturerDTO;

	final long id = 1L;

	@Before
	public void init() {
		this.manufacturerList = new ArrayList<>();
		this.manufacturerList.add(testManufacturer);
		this.testManufacturer = new Manufacturer("QA", "email@email.com", "password");
		this.testManufacturerWithID = new Manufacturer(testManufacturer.getName(), testManufacturer.getEmail(), testManufacturer.getPassword());
		this.testManufacturerWithID.setId(id);
		this.manufacturerDTO = new ModelMapper().map(testManufacturerWithID, ManufacturerDTO.class);
	}

	@Test
	public void createManufacturerTest() {
		when(this.repo.save(testManufacturer)).thenReturn(testManufacturerWithID);
		when(this.mapper.map(testManufacturerWithID, ManufacturerDTO.class)).thenReturn(manufacturerDTO);

		assertEquals(this.manufacturerDTO, this.service.createManufacturer(testManufacturer));

		verify(this.repo, times(1)).save(this.testManufacturer);
	}

	@Test
	public void deleteManufacturerTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteManufacturer(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findManufacturerByIDTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testManufacturerWithID));
		when(this.mapper.map(testManufacturerWithID, ManufacturerDTO.class)).thenReturn(manufacturerDTO);

		assertEquals(this.manufacturerDTO, this.service.findManufacturerById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readManufacturersTest() {

		when(repo.findAll()).thenReturn(this.manufacturerList);
		when(this.mapper.map(testManufacturerWithID, ManufacturerDTO.class)).thenReturn(manufacturerDTO);

		assertFalse("Controller has found no manufacturers", this.service.readManufacturers().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateManufacturersTest() {
		// given
		Manufacturer newManufacturer = new Manufacturer("new Firm", "new@email.com", "stronger password");

		Manufacturer updatedManufacturer = new Manufacturer(newManufacturer.getName(), newManufacturer.getEmail(), newManufacturer.getPassword());
		updatedManufacturer.setId(this.id);

		ManufacturerDTO updatedDTO = new ModelMapper().map(updatedManufacturer, ManufacturerDTO.class);


		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testManufacturerWithID));
		when(this.mapper.map(updatedManufacturer, ManufacturerDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Manufacturer.java for this to work
		when(this.repo.save(updatedManufacturer)).thenReturn(updatedManufacturer);

		assertEquals(updatedDTO, this.service.updateManufacturer(this.id, newManufacturer));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedManufacturer);
	}

}