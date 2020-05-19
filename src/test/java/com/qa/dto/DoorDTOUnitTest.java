package com.qa.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DoorDTOUnitTest {

    private DoorDTO door;
    private DoorDTO other;

    @Before
    public void setUp(){
        door = new DoorDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        other = new DoorDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
    }


    @Test
    public void equalsWithNull(){
        assertFalse(door.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(door.equals(new Object()));
    }

    @Test
    public void createDoorWithId(){
        door.setId((long) 1);
        assertEquals(1L, door.getId(),0);
        assertEquals("test2style", door.getDescription());
    }

    @Test
    public void checkEquality(){
        assertTrue(door.equals(door));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(door.equals(other));
    }


    @Test
    public void doorIdNotEqual(){
        other.setId(2L);
        assertFalse(door.equals(other));
    }


    @Test
    public void constructorWithoutId(){
        DoorDTO door = new DoorDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        assertNull(door.getId());
        assertNotNull(door.getDescription());
    }

    @Test
    public void emptyConstructor(){
        DoorDTO door = new DoorDTO();
        assertNull(door.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        DoorDTO door = new DoorDTO(null, null, null, null, null, null);
        DoorDTO other = new DoorDTO(null, null, null, null, null, null);
        assertEquals(door.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(door.hashCode(), other.hashCode());
    }

}