package com.qa.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DoorUnitTest {

    private Door door;
    private Door other;

    @Before
    public void setUp(){
        door = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        other = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
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
        Door door = new Door("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        assertNull(door.getId());
        assertNotNull(door.getDescription());
    }

    @Test
    public void emptyConstructor(){
        Door door = new Door();
        assertNull(door.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        Door door = new Door(null, null, null, null, null, null);
        Door other = new Door(null, null, null, null, null, null);
        assertEquals(door.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(door.hashCode(), other.hashCode());
    }

}