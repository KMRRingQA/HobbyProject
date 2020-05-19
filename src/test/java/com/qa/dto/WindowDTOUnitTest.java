package com.qa.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class WindowDTOUnitTest {

    private WindowDTO window;
    private WindowDTO other;

    @Before
    public void setUp(){
        window = new WindowDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        other = new WindowDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
    }


    @Test
    public void equalsWithNull(){
        assertFalse(window.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(window.equals(new Object()));
    }

    @Test
    public void createWindowWithId(){
        window.setId((long) 1);
        assertEquals(1L, window.getId(),0);
        assertEquals("test2style", window.getDescription());
    }

    @Test
    public void checkEquality(){
        assertTrue(window.equals(window));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(window.equals(other));
    }


    @Test
    public void windowIdNotEqual(){
        other.setId(2L);
        assertFalse(window.equals(other));
    }


    @Test
    public void constructorWithoutId(){
        WindowDTO window = new WindowDTO("test2", "test2style", "test2BWF","test2Uvalue","test2Dim", BigDecimal.valueOf(27.99));
        assertNull(window.getId());
        assertNotNull(window.getDescription());
    }

    @Test
    public void emptyConstructor(){
        WindowDTO window = new WindowDTO();
        assertNull(window.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        WindowDTO window = new WindowDTO(null, null, null, null, null, null);
        WindowDTO other = new WindowDTO(null, null, null, null, null, null);
        assertEquals(window.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(window.hashCode(), other.hashCode());
    }

}