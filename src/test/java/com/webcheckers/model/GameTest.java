package com.webcheckers.model;

import com.webcheckers.util.Position;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.FilteredRowSet;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final Player player1 = new Player("Player1");
    private final Player player2 = new Player("Player2");

    private final Game testGame = new Game(player1, player2);


    @Test
    public void testAddPieces() {
        assertEquals(12, testGame.getNumRedPieces(),
                "Expected 12 Red pieces, but got " + testGame.getNumRedPieces());
        assertEquals(12, testGame.getNumWhitePieces(),
                "Expected 12 White pieces, but ");
    }

    @Test
    public void testMakeMoveRED() {
        Position FIRST_POSITION = new Position(6,1);
        Position OCCUPIED_POSITION = new Position(5,0);
        Position SECOND_POSITION = new Position(5,4);
        Position THIRD_POSITION = new Position(4,3);
        Position FOURTH_POSITION = new Position(3, 4);
        Position SINGLE_RESTRICTED_POSITION = new Position(3,0);

        Move moveToOccupiedSpace = new Move(FIRST_POSITION, OCCUPIED_POSITION);
        Game.MoveResult result = testGame.makeMove(moveToOccupiedSpace);

        assertEquals(Game.MoveResult.OCCUPIED, result,
                "Expected OCCUPIED, but was " + result);

        Move moveSingleRestricted = new Move(FIRST_POSITION, SINGLE_RESTRICTED_POSITION);
        result = testGame.makeMove(moveSingleRestricted);

        assertEquals(Game.MoveResult.SINGLE_RESTRICTED, result,
                "Expected SINGLE_RESTRICTED, but was " + result);

        Move moveSimpleMove = new Move(SECOND_POSITION, THIRD_POSITION);
        result = testGame.makeMove(moveSimpleMove);

        /*assertEquals(Game.MoveResult.SIMPLE_MOVE, result,
                "Expected SINGLE_MOVE, but was " + result);*/

        Move simpleMovesExceeded = new Move(THIRD_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(simpleMovesExceeded);

        assertEquals(Game.MoveResult.SIMPLE_MOVES_EXCEEDED, result,
                "Expected SIMPLE_MOVES_EXCEEDED, but was " + result);

        Move moveNoPiece = new Move(SECOND_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(moveNoPiece);

        /*assertEquals(Game.MoveResult.INVALID, result,
                "Expected INVALID, but was " + result);*/

    }

    @Test
    public void testMakeMoveWHITE() {
        Position FIRST_POSITION = new Position(1,2);
        Position OCCUPIED_POSITION = new Position(2,1);
        Position SECOND_POSITION = new Position(2,3);
        Position THIRD_POSITION = new Position(3,2);
        Position FOURTH_POSITION = new Position(4, 3);
        Position SINGLE_RESTRICTED_POSITION = new Position(3,0);

        Move moveToOccupiedSpace = new Move(FIRST_POSITION, OCCUPIED_POSITION);
        Game.MoveResult result = testGame.makeMove(moveToOccupiedSpace);

        assertEquals(Game.MoveResult.OCCUPIED, result,
                "Expected OCCUPIED, but was " + result);

        Move moveSingleRestricted = new Move(FIRST_POSITION, SINGLE_RESTRICTED_POSITION);
        result = testGame.makeMove(moveSingleRestricted);

        assertEquals(Game.MoveResult.SINGLE_RESTRICTED, result,
                "Expected SINGLE_RESTRICTED, but was " + result);

        Move moveSimpleMove = new Move(SECOND_POSITION, THIRD_POSITION);
        result = testGame.makeMove(moveSimpleMove);

        assertEquals(Game.MoveResult.SIMPLE_MOVE, result,
                "Expected SINGLE_MOVE, but was " + result);

        Move simpleMovesExceeded = new Move(THIRD_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(simpleMovesExceeded);

        assertEquals(Game.MoveResult.SIMPLE_MOVES_EXCEEDED, result,
                "Expected SINGLE_MOVE, but was " + result);

        Move moveNoPiece = new Move(SECOND_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(moveNoPiece);

        assertEquals(Game.MoveResult.INVALID, result,
                "Expected INVALID, but was " + result);
    }


}
