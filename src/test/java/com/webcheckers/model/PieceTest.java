package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PieceTest {

    @Test
    void ctor_red_single_args() {
        //Create a RED Piece as type SINGLE
        Piece CuT = new Piece(Piece.Color.RED, Piece.Type.SINGLE);
        //Analyze stats of CuT, should return "Piece: {RED, SINGLE}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_white_single_args() {
        //Create a WHITE Piece as type SINGLE
        Piece CuT = new Piece(Piece.Color.WHITE, Piece.Type.SINGLE);
        //Analyze stats of CuT, should return "Piece: {WHITE, SINGLE}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_red_king_args() {
        //Create a RED Piece as type KING
        Piece CuT = new Piece(Piece.Color.RED, Piece.Type.KING);
        //Analyze stats of CuT, should return "Piece: {RED, KING}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }

    @Test
    void ctor_white_king_args() {
        //Create a WHITE Piece as type KING
        Piece CuT = new Piece(Piece.Color.WHITE, Piece.Type.KING);
        //Analyze stats of CuT, should return "Piece: {WHITE, KING}"
        assertEquals(CuT.toString(), "Piece: {" + CuT.getColor() + ", " + CuT.getType() + "}");
    }
}
