package com.qa.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.dto.ManufacturerDTO;
import com.qa.domain.Manufacturer;
import com.qa.rest.ManufacturerController;
import com.qa.service.ManufacturerService;

@RunWith(MockitoJUnitRunner.class)
public class ManufacturerControllerUnitTest {

    @InjectMocks
    private ManufacturerController controller;

    @Mock
    private ManufacturerService service;

    private List<Manufacturer> manufacturerList;

    private Manufacturer testManufacturer;

    private Manufacturer testManufacturerWithID;

    private ManufacturerDTO manufacturerDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private ManufacturerDTO mapToDTO(Manufacturer manufacturer) {
        return this.mapper.map(manufacturer, ManufacturerDTO.class);
    }


    @Before
    public void init() {
        this.manufacturerList = new ArrayList<>();
        this.testManufacturer = new Manufacturer("Marlin", "Marlin-office.mbz.com", "hello1234");
        this.manufacturerList.add(testManufacturer);
        this.testManufacturerWithID = new Manufacturer(testManufacturer.getName(), testManufacturer.getEmail(), testManufacturer.getPassword());
        this.testManufacturerWithID.setId(id);
        this.manufacturerDTO = this.mapToDTO(testManufacturerWithID);
    }

    @Test
    public void createManufacturerTest() {
        when(this.service.createManufacturer(testManufacturer)).thenReturn(this.manufacturerDTO);

        assertEquals(new ResponseEntity<ManufacturerDTO>(this.manufacturerDTO, HttpStatus.CREATED), this.controller.createManufacturer(testManufacturer));
        verify(this.service, times(1)).createManufacturer(this.testManufacturer);
    }

    @Test
    public void deleteManufacturerTest() {
        this.controller.deleteManufacturer(id);

        verify(this.service, times(1)).deleteManufacturer(id);
    }

    @Test
    public void findManufacturerByIDTest() {
        when(this.service.findManufacturerById(this.id)).thenReturn(this.manufacturerDTO);
        assertEquals(new ResponseEntity<ManufacturerDTO>(this.manufacturerDTO, HttpStatus.OK), this.controller.getManufacturerById(this.id));

        verify(this.service, times(1)).findManufacturerById(this.id);
    }

    @Test
    public void getAllManufacturersTest() {

        when(service.readManufacturers()).thenReturn(this.manufacturerList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found no manufacturers", this.controller.getAllManufacturers().getBody().isEmpty());

        verify(service, times(1)).readManufacturers();
    }

    @Test
    public void updateManufacturersTest() {
        Manufacturer newManufacturer = new Manufacturer("Gartec Lifts", "Gartec-Lifts@libz.com", "passw0rd");
        Manufacturer updatedManufacturer = new Manufacturer(newManufacturer.getName(), newManufacturer.getEmail(), newManufacturer.getPassword());
        updatedManufacturer.setId(this.id);

        when(this.service.updateManufacturer(this.id, newManufacturer)).thenReturn(this.mapToDTO(updatedManufacturer));

        assertEquals(new ResponseEntity<ManufacturerDTO>(this.mapToDTO(updatedManufacturer), HttpStatus.OK), this.controller.updateManufacturer(this.id, newManufacturer));

        verify(this.service, times(1)).updateManufacturer(this.id, newManufacturer);
    }

}