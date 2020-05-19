package com.qa.rest;

import com.qa.domain.Window;
import com.qa.dto.WindowDTO;
import com.qa.service.WindowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WindowControllerUnitTest {

    @InjectMocks
    private WindowController controller;

    @Mock
    private WindowService service;

    private List<Window> windowList;

    private Window testWindow;

    private Window testWindowWithID;

    private WindowDTO windowDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private WindowDTO mapToDTO(Window window) {
        return this.mapper.map(window, WindowDTO.class);
    }


    @Before
    public void init() {
        this.windowList = new ArrayList<>();
        this.testWindow = new Window("test", "testStyle", "testBWF", "TestUvalue","testDimension", BigDecimal.valueOf(2.99));
        this.windowList.add(testWindow);
        this.testWindowWithID = new Window(testWindow.getTitle(), testWindow.getDescription(), testWindow.getBwf(), testWindow.getThermalResistance(),testWindow.getDimensions(), testWindow.getCost());
        this.testWindowWithID.setId(id);
        this.windowDTO = this.mapToDTO(testWindowWithID);
    }

    @Test
    public void createWindowTest() {
        when(this.service.createWindow(testWindow)).thenReturn(this.windowDTO);

        assertEquals(new ResponseEntity<WindowDTO>(this.windowDTO, HttpStatus.CREATED), this.controller.createWindow(testWindow));
        verify(this.service, times(1)).createWindow(this.testWindow);
    }

    @Test
    public void deleteWindowTest() {
        this.controller.deleteWindow(id);

        verify(this.service, times(1)).deleteWindow(id);
    }

    @Test
    public void findWindowByIDTest() {
        when(this.service.findWindowById(this.id)).thenReturn(this.windowDTO);
        assertEquals(new ResponseEntity<WindowDTO>(this.windowDTO, HttpStatus.OK), this.controller.getWindowById(this.id));

        verify(this.service, times(1)).findWindowById(this.id);
    }

    @Test
    public void getAllWindowsTest() {

        when(service.readWindows()).thenReturn(this.windowList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found no windows", this.controller.getAllWindows().getBody().isEmpty());

        verify(service, times(1)).readWindows();
    }

    @Test
    public void updateWindowsTest() {
        Window newWindow = new Window("test2", "test2style", "test2BWF","test2Uvalue","test2Dim",BigDecimal.valueOf(27.99));
        Window updatedWindow = new Window(newWindow.getTitle(), newWindow.getDescription(), newWindow.getBwf(), newWindow.getThermalResistance(),newWindow.getDimensions(), newWindow.getCost());
        updatedWindow.setId(this.id);

        when(this.service.updateWindow(this.id, newWindow)).thenReturn(this.mapToDTO(updatedWindow));

        assertEquals(new ResponseEntity<WindowDTO>(this.mapToDTO(updatedWindow), HttpStatus.OK), this.controller.updateWindow(this.id, newWindow));

        verify(this.service, times(1)).updateWindow(this.id, newWindow);
    }

}