package com.webcheckers.model;

import com.webcheckers.util.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PositionTest {

    @Test
    public void ctor_test_1() {
        Position CuT = new Position(5, 5);
        int rowValue = CuT.getRow();
        int cellValue = CuT.getCell();
        assertEquals(rowValue, 5);
        assertEquals(cellValue, 5);
    }

    @Test
    public void ctor_test_2() {
        Position CuT = new Position(3, 6);
        int rowValue = CuT.getRow();
        int cellValue = CuT.getCell();
        assertEquals(rowValue, 3);
        assertEquals(cellValue, 6);
    }

    @Test
    public void getRow_test() {
        Position CuT = new Position(4, 8);
        int rowValue = CuT.getRow();
        assertEquals(rowValue, 4);
    }

    @Test
    public void getCell_test() {
        Position CuT = new Position(4, 8);
        int cellValue = CuT.getCell();
        assertEquals(cellValue, 8);
    }

    @Test
    public void equals_test_true() {
        Position CuT = new Position(0, 0);
        Position other = new Position(0, 0);
        assertTrue(CuT.equals(other));
    }

    @Test
    public void equals_test_false() {
        Position CuT = new Position(0, 0);
        Position other = new Position(1, 1);
        assertFalse(CuT.equals(other));
    }
}
