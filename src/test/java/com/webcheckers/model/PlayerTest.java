package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PlayerTest {

    @Test
    void ctor_test() {
        Player CuT = new Player("Default");
        assertEquals(CuT.toString(), "Player: Default");
    }

    @Test
    void getName_test() {
        Player CuT = new Player("Default");
        assertEquals(CuT.getName(), "Default");
    }

    @Test
    void hashCode_test() {
        Player CuT = new Player("Default");
        assertEquals(CuT.hashCode(), "Default".hashCode());
    }

    @Test
    void equals_test_true() {
        Player CuT = new Player("Default");
        Object other = new Player("Default");
        assertTrue(CuT.equals(other));
    }

    @Test
    void equals_test_false() {
        Player CuT = new Player("Default");
        Object other = new Object();
        assertFalse(CuT.equals(other));
    }
}
