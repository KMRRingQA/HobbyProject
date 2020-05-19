package com.qa.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class LiftDTOUnitTest {

    private LiftDTO lift;
    private LiftDTO other;

    @Before
    public void setUp(){
        lift = new LiftDTO("test2", "test2style", 100,110,"test2Dim", BigDecimal.valueOf(27.99));
        other = new LiftDTO("test2", "test2style", 100,110,"test2Dim", BigDecimal.valueOf(27.99));
    }


    @Test
    public void equalsWithNull(){
        assertFalse(lift.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(lift.equals(new Object()));
    }

    @Test
    public void createLiftWithId(){
        lift.setId((long) 1);
        assertEquals(1L, lift.getId(),0);
        assertEquals("test2style", lift.getDescription());
    }

    @Test
    public void checkEquality(){
        assertTrue(lift.equals(lift));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(lift.equals(other));
    }


    @Test
    public void liftIdNotEqual(){
        other.setId(2L);
        assertFalse(lift.equals(other));
    }


    @Test
    public void constructorWithoutId(){
        LiftDTO lift = new LiftDTO("test2", "test2style", 100,110,"test2Dim", BigDecimal.valueOf(27.99));
        assertNull(lift.getId());
        assertNotNull(lift.getDescription());
    }

    @Test
    public void emptyConstructor(){
        LiftDTO lift = new LiftDTO();
        assertNull(lift.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        LiftDTO lift = new LiftDTO(null, null, null, null, null, null);
        LiftDTO other = new LiftDTO(null, null, null, null, null, null);
        assertEquals(lift.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(lift.hashCode(), other.hashCode());
    }

}