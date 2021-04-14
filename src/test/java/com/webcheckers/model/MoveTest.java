package com.webcheckers.model;

import com.webcheckers.util.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Model-tier")
public class MoveTest {

    @Test
    public void copy_ctor_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 4);
        Move CuT = new Move(startPosition, endPosition);

        assertEquals(CuT.start(), startPosition);
        assertEquals(CuT.end(), endPosition);
    }

    @Test
    public void capture_ctor_true_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 4);

        Move move = new Move(startPosition, endPosition);
        Piece capturedPiece = new Single(Piece.Color.RED);

        Move CuT = new Move(move, capturedPiece);
        assertEquals(CuT.getType(), Game.MoveResult.JUMP);
    }

    @Test
    public void capture_ctor_false_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 4);

        Move move = new Move(startPosition, endPosition);

        Move CuT = new Move(move, null);
        assertEquals(CuT.getType(), Game.MoveResult.SIMPLE_MOVE);
    }

    @Test
    public void getStart_test() {
        Position startPosition = new Position(9, 6);
        Position endPosition = new Position(1, 4);
        Move CuT = new Move(startPosition, endPosition);

        assertEquals(CuT.start(), startPosition);
    }

    @Test
    public void getEnd_test() {
        Position startPosition = new Position(9, 6);
        Position endPosition = new Position(1, 4);
        Move CuT = new Move(startPosition, endPosition);

        assertEquals(CuT.end(), endPosition);
    }

    @Test
    public void midpoint_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(2, 2);
        Position midpoint = new Position(1, 1);
        Move CuT = new Move(startPosition, endPosition);
        assertEquals(CuT.midpoint(), midpoint);
    }

    @Test
    public void getCapturedPiece_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 4);

        Move move = new Move(startPosition, endPosition);
        Piece capturedPiece = new Single(Piece.Color.RED);

        Move CuT = new Move(move, capturedPiece);
        assertEquals(CuT.getCapturedPiece(), capturedPiece);
    }

    @Test
    public void getType_test() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 4);
        Move CuT = new Move(startPosition, endPosition);

        assertEquals(CuT.getType(), Game.MoveResult.SIMPLE_MOVE);
    }
}
