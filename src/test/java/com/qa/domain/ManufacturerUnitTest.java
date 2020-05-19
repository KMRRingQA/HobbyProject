package com.qa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManufacturerUnitTest {

    private Manufacturer manufacturer;
    private Manufacturer other;

    @Before
    public void setUp(){
        manufacturer = new Manufacturer("test2", "test2style", "test2BWF");
        other = new Manufacturer("test2", "test2style", "test2BWF");
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
        Manufacturer manufacturer = new Manufacturer("test2", "test2style", "test2BWF");
        assertNull(manufacturer.getId());
        assertNotNull(manufacturer.getEmail());
    }

    @Test
    public void emptyConstructor(){
        Manufacturer manufacturer = new Manufacturer();
        assertNull(manufacturer.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        Manufacturer manufacturer = new Manufacturer(null, null, null);
        Manufacturer other = new Manufacturer(null, null, null);
        assertEquals(manufacturer.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(manufacturer.hashCode(), other.hashCode());
    }

}