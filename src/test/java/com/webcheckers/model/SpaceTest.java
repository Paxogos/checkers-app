package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
class SpaceTest {

    private static final int SMALL_IDX = -1;
    private static final int LARGE_IDX = 8;
    private static final int IDX = 7;




    @Test
    void ctor_too_small_1st_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(SMALL_IDX,IDX);}, "Allowed small 1st index");
    }

    @Test
    void ctor_too_small_2nd_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(IDX,SMALL_IDX);}, "Allowed small 2nd index");
    }

    @Test
    void ctor_too_large_1st_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(LARGE_IDX,IDX);}, "Allowed large 1st index");
    }

    @Test
    void ctor_too_large_2nd_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(IDX,LARGE_IDX);}, "Allowed large 2nd index");
    }

    @Test
    void getCellIdx() {
    }

    @Test
    void getRowIdx() {
    }

    @Test
    void isValid() {
    }

    @Test
    void getPiece() {
    }

    @Test
    void testToString() {
    }
}