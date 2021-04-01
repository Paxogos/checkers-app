package com.webcheckers.model;

import com.webcheckers.util.Position;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.model.Piece.Color;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final Player player1 = new Player("Player1");
    private final Player player2 = new Player("Player2");

    private final Game testGame = new Game(player1, player2);


    private Game getGameFromBoardContents(String[] boardsContents, Piece.Color activeColor) {
        Iterator<String> contentsIterator = Arrays.stream(boardsContents).iterator();
        Space[][] board = new Space[Board.GRID_LENGTH][Board.GRID_LENGTH];

        for (int row = 0; row < Board.GRID_LENGTH; row++) {
            for (int col = 0; col < Board.GRID_LENGTH; col++) {

                String spaceContents = contentsIterator.next();
                Space newSpace;

                switch (spaceContents) {
                    case "w":
                        newSpace = new Space(row, col, new Single(Color.WHITE));
                        break;

                    case "W":
                        newSpace = new Space(row, col, new King(Color.WHITE));
                        break;

                    case "r":
                        newSpace = new Space(row, col, new Single(Color.RED));
                        break;

                    case "R":
                        newSpace = new Space(row, col, new King(Color.RED));
                        break;

                    default:
                        newSpace = new Space(row, col, null);
                }
                board[row][col] = newSpace;
            }
        }

        return new Game(new Board(board), activeColor);
    }

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

    @Test
    public void testRedJump() {
        Game game = getGameFromBoardContents(TestBoards.WHITE_3_4__3_6__2_7_RED_4_5, Color.RED);

        Move validJump = new Move(new Position(4,5), new Position(2,3));

        Move occupiedJump = new Move(new Position(4,5), new Position(2,7));


        System.out.println(game.getBoard());

        MoveResult result = game.makeMove(occupiedJump);

        System.out.println(game.getBoard());

        assertEquals(MoveResult.OCCUPIED, result,
                "Expected "  + MoveResult.OCCUPIED + ", but got " + result);

        result = game.makeMove(validJump);

        System.out.println(game.getBoard());

        assertEquals(MoveResult.JUMP, result,
                "Expected "  + MoveResult.JUMP + ", but got " + result);
    }

    @Test
    public void testWhiteJump() {
        Game game = getGameFromBoardContents(TestBoards.WHITE_3_4__RED_4_3__4_5__5_6, Color.WHITE);

        Move validJump = new Move(new Position(3,4), new Position(5,2));

        Move occupiedJump = new Move(new Position(3,4), new Position(5,6));

        MoveResult result = game.makeMove(occupiedJump);

        assertEquals(MoveResult.OCCUPIED, result,
                "Expected "  + MoveResult.OCCUPIED + ", but got " + result);

        result = game.makeMove(validJump);

        assertEquals(MoveResult.JUMP, result,
                "Expected "  + MoveResult.JUMP + ", but got " + result);
    }

   /* @Test
    public void testBackup() {
        Position FIRST_POSITION = new Position(1,2);
        Position SECOND_POSITION = new Position(2,3);

        Move moveToValidSpace = new Move(FIRST_POSITION, SECOND_POSITION);
        testGame.makeMove(moveToValidSpace);

        assertTrue(testGame.backup());
    }*/

}
