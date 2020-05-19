package com.qa.service;

import com.qa.domain.Window;
import com.qa.dto.WindowDTO;
import com.qa.repo.WindowsRepository;
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
public class WindowServiceUnitTest {

	@InjectMocks
	private WindowService service;

	@Mock
	private WindowsRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Window> windowList;

	private Window testWindow;

	private Window testWindowWithID;

	private WindowDTO windowDTO;

	final long id = 1L;

	@Before
	public void init() {
		this.windowList = new ArrayList<>();
		this.windowList.add(testWindow);
		this.testWindow = new Window("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
		this.testWindowWithID = new Window(testWindow.getTitle(), testWindow.getDescription(), testWindow.getBwf(), testWindow.getThermalResistance(),testWindow.getDimensions(), testWindow.getCost());
		this.testWindowWithID.setId(id);
		this.windowDTO = new ModelMapper().map(testWindowWithID, WindowDTO.class);
	}

	@Test
	public void createWindowTest() {
		when(this.repo.save(testWindow)).thenReturn(testWindowWithID);
		when(this.mapper.map(testWindowWithID, WindowDTO.class)).thenReturn(windowDTO);

		assertEquals(this.windowDTO, this.service.createWindow(testWindow));

		verify(this.repo, times(1)).save(this.testWindow);
	}

	@Test
	public void deleteWindowTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteWindow(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findWindowByIDTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testWindowWithID));
		when(this.mapper.map(testWindowWithID, WindowDTO.class)).thenReturn(windowDTO);

		assertEquals(this.windowDTO, this.service.findWindowById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readWindowsTest() {

		when(repo.findAll()).thenReturn(this.windowList);
		when(this.mapper.map(testWindowWithID, WindowDTO.class)).thenReturn(windowDTO);

		assertFalse("Controller has found no windows", this.service.readWindows().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateWindowsTest() {
		// given
		Window newWindow = new Window("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
		newWindow.setId(this.id);
		
		Window updatedWindow = new Window(newWindow.getTitle(), newWindow.getDescription(), newWindow.getBwf(), newWindow.getThermalResistance(),newWindow.getDimensions(), newWindow.getCost());
		updatedWindow.setId(this.id);
		
		WindowDTO updatedDTO = new ModelMapper().map(updatedWindow, WindowDTO.class);

		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testWindowWithID));
		when(this.mapper.map(updatedWindow, WindowDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Window.java for this to work
		when(this.repo.save(updatedWindow)).thenReturn(updatedWindow);

		assertEquals(updatedDTO, this.service.updateWindow(this.id, newWindow));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedWindow);
	}

}