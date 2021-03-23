package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PieceTest {

    @Test
    void ctor_red_single_args() {
        //Create a RED Piece as type SINGLE
        Piece CuT = new Single(Piece.Color.RED);
        //Analyze stats of CuT, should return "Piece: {RED, SINGLE}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_white_single_args() {
        //Create a WHITE Piece as type SINGLE
        Piece CuT = new Single(Piece.Color.WHITE);
        //Analyze stats of CuT, should return "Piece: {WHITE, SINGLE}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_red_king_args() {
        //Create a RED Piece as type KING
        Piece CuT = new King(Piece.Color.RED);
        //Analyze stats of CuT, should return "Piece: {RED, KING}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_white_king_args() {
        //Create a WHITE Piece as type KING
        Piece CuT = new King(Piece.Color.WHITE);
        //Analyze stats of CuT, should return "Piece: {WHITE, KING}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void getter_isSingle_red_true() {
        //Create a RED Piece as type SINGLE
        Piece CuT = new Single(Piece.Color.RED);
        //should return true since CuT is a type SINGLE
        assertTrue(CuT.isSingle(), "Piece is type SINGLE");
    }

    @Test
    void getter_isSingle_white_true() {
        //Create a WHITE Piece as type SINGLE
        Piece CuT = new Single(Piece.Color.WHITE);
        //should return true since CuT is a type SINGLE
        assertTrue(CuT.isSingle(), "Piece is type SINGLE");
    }

    @Test
    void getter_isSingle_red_false() {
        //Create a RED Piece as type KING
        Piece CuT = new Single(Piece.Color.RED);
        //should return false since CuT isn't type SINGLE
        assertFalse(CuT.isSingle(), "Piece isn't type SINGLE");
    }

    @Test
    void getter_isSingle_white_false() {
        //Create a WHITE Piece as type KING
        Piece CuT = new Single(Piece.Color.WHITE);
        //should return false since CuT isn't type SINGLE
        assertFalse(CuT.isSingle(), "Piece isn't type SINGLE");
    }

    @Test
    void getter_isKing_red_true() {
        //Create a RED Piece as type KING
        Piece CuT = new King(Piece.Color.RED);
        //Should hold true since CuT is a KING
        assertTrue(CuT.isKing(), "Piece is type KING");
    }

    @Test
    void getter_isKing_white_true() {
        //Create a WHITE Piece as type KING
        Piece CuT = new King(Piece.Color.WHITE);
        //Should hold true since CuT is a KING
        assertTrue(CuT.isKing(), "Piece is type KING");
    }

    @Test
    void getter_isKing_red_false() {
        //Create a RED Piece as type KING
        Piece CuT = new King(Piece.Color.RED);
        //Should hold true since CuT is a KING
        assertFalse(CuT.isKing(), "Piece isn't type KING");
    }

    @Test
    void getter_isKing_white_false() {
        //Create a WHITE Piece as type KING
        Piece CuT = new King(Piece.Color.WHITE);
        //Should hold true since CuT is a KING
        assertFalse(CuT.isKing(), "Piece isn't type KING");
    }

}
