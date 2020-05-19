package com.qa.service;

import com.qa.domain.Window;
import com.qa.domain.Manufacturer;
import com.qa.dto.WindowDTO;
import com.qa.repo.WindowsRepository;
import com.qa.repo.ManufacturerRepository;
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
public class WindowServiceIntegrationTest {

	@Autowired
	private WindowService service;

	@Autowired
	private WindowsRepository repo;

	private Window testWindow;

	private Window testWindowWithID;

	private long manId;

	@Autowired
	private ManufacturerRepository repoManu;

	private Manufacturer testManufacturer;

	private Manufacturer testManufacturerWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private WindowDTO mapToDTO(Window window) {
		return this.mapper.map(window, WindowDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testWindow = new Window("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
		this.testManufacturer = new Manufacturer("testName", "test@email.com","testpass");

		this.testManufacturerWithID = this.repoManu.save(this.testManufacturer);
		this.testWindow.setManufacturer(testManufacturer);
		this.testWindowWithID = this.repo.save(this.testWindow);
	}
	
	@Test
	public void testCreateWindow() {
		assertEquals(this.mapToDTO(this.testWindowWithID), this.service.createWindow(testWindow));
	}

	@Test
	public void testDeleteWindow() {
		assertThat(this.service.deleteWindow(this.testWindowWithID.getId())).isFalse();
	}

	@Test
	public void testFindWindowByID() {
		assertThat(this.service.findWindowById(this.testWindowWithID.getId())).isEqualTo(this.mapToDTO(this.testWindowWithID));
	}

	@Test
	public void testReadWindows() {
		assertThat(this.service.readWindows()).isEqualTo(Stream.of(this.mapToDTO(testWindowWithID)).collect(Collectors.toList()));
	}

	@Test
	public void testUpdateWindow() {
		Window newWindow = new Window("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
		Window updatedWindow = new Window(testWindow.getTitle(), testWindow.getDescription(), testWindow.getBwf(), testWindow.getThermalResistance(),testWindow.getDimensions(), testWindow.getCost());

		updatedWindow.setManufacturer(testManufacturer);
		newWindow.setManufacturer(testManufacturer);

		updatedWindow.setId(this.testWindowWithID.getId());
		newWindow.setId(this.testWindowWithID.getId());

		assertThat(this.service.updateWindow(this.testWindowWithID.getId(), newWindow)).isEqualTo(this.mapToDTO(newWindow));
	}

}