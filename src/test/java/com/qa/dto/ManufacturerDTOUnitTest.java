package com.qa.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ManufacturerDTOUnitTest {

    private ManufacturerDTO manufacturer;
    private ManufacturerDTO other;

    @Before
    public void setUp(){
        manufacturer = new ManufacturerDTO("test2", "test2style", "test2BWF");
        other = new ManufacturerDTO("test2", "test2style", "test2BWF");
    }


    @Test
    public void equalsWithNull(){
        assertFalse(manufacturer.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(manufacturer.equals(new Object()));
    }

    @Test
    public void createManufacturerWithId(){
        manufacturer.setId((long) 1);
        assertEquals(1L, manufacturer.getId(),0);
        assertEquals("test2style", manufacturer.getEmail());
    }

    @Test
    public void checkEquality(){
        assertTrue(manufacturer.equals(manufacturer));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(manufacturer.equals(other));
    }


    @Test
    public void manufacturerIdNotEqual(){
        other.setId(2L);
        assertFalse(manufacturer.equals(other));
    }


    @Test
    public void constructorWithoutId(){
        ManufacturerDTO manufacturer = new ManufacturerDTO("test2", "test2style", "test2BWF");
        assertNull(manufacturer.getId());
        assertNotNull(manufacturer.getEmail());
    }

    @Test
    public void emptyConstructor(){
        ManufacturerDTO manufacturer = new ManufacturerDTO();
        assertNull(manufacturer.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        ManufacturerDTO manufacturer = new ManufacturerDTO(null, null, null);
        ManufacturerDTO other = new ManufacturerDTO(null, null, null);
        assertEquals(manufacturer.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(manufacturer.hashCode(), other.hashCode());
    }

}