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
    public void testMakeMove() {
        Position FIRST_POSITION = new Position(5,0);
        Position OCCUPIED_POSITION = new Position(4,1);
        testGame.getBoard().setSpaceToPiece(new Position(4,1),new Single(Piece.Color.RED));
        Position SECOND_POSITION = new Position(5,4);
        testGame.getBoard().removePieceAt(new Position(5,4));
        Position SINGLE_RESTRICTED_POSITION = new Position(3,0);

        Move moveToOccupiedSpace = new Move(FIRST_POSITION, OCCUPIED_POSITION);
        Game.MoveResult result = testGame.makeMove(moveToOccupiedSpace);

        assertEquals(Game.MoveResult.OCCUPIED, result,
                "Expected OCCUPIED, but was " + result);

        Move moveSingleRestricted = new Move(FIRST_POSITION, SINGLE_RESTRICTED_POSITION);
        result = testGame.makeMove(moveSingleRestricted);

        assertEquals(Game.MoveResult.SINGLE_RESTRICTED, result,
                "Expected SINGLE_RESTRICTED, but was " + result);

        Move moveSimpleMove = new Move(FIRST_POSITION, SECOND_POSITION);
        result = testGame.makeMove(moveSimpleMove);

        /*assertEquals(Game.MoveResult.SIMPLE_MOVE, result,
                "Expected SINGLE_MOVE, but was " + result);*/

        Move moveNoPiece = new Move(FIRST_POSITION, SECOND_POSITION);
        result = testGame.makeMove(moveNoPiece);

        /*assertEquals(Game.MoveResult.INVALID, result,
                "Expected INVALID, but was " + result);*/

    }


}
