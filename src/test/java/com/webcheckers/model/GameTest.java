package com.webcheckers.model;

import com.webcheckers.util.Position;
import com.webcheckers.util.TestBoards;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.model.Piece.Color;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class GameTest {

    private final Player player1 = new Player("Player1");
    private final Player player2 = new Player("Player2");
    private final int gameID = 0;

    private final Game testGame = new Game(player1, player2, gameID);




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

        assertEquals(Game.MoveResult.SIMPLE_MOVE, result,
                "Expected SINGLE_MOVE, but was " + result);

        Move simpleMovesExceeded = new Move(THIRD_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(simpleMovesExceeded);

        assertEquals(Game.MoveResult.SIMPLE_MOVES_EXCEEDED, result,
                "Expected SIMPLE_MOVES_EXCEEDED, but was " + result);

        Move moveNoPiece = new Move(SECOND_POSITION, FOURTH_POSITION);
        result = testGame.makeMove(moveNoPiece);

        assertEquals(Game.MoveResult.INVALID, result,
                "Expected INVALID, but was " + result);

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
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_3_4__3_6__2_7_RED_4_5, Color.RED);

        Move validJump = new Move(new Position(4,5), new Position(2,3));

        Move occupiedJump = new Move(new Position(4,5), new Position(2,7));


        MoveResult result = game.makeMove(occupiedJump);
        assertEquals(MoveResult.OCCUPIED, result,
                "Expected "  + MoveResult.OCCUPIED + ", but got " + result);

        result = game.makeMove(validJump);
        assertEquals(MoveResult.JUMP, result,
                "Expected "  + MoveResult.JUMP + ", but got " + result);
    }

    @Test
    public void testWhiteJump() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_3_4__RED_4_3__4_5__5_6, Color.WHITE);

        Move validJump = new Move(new Position(3,4), new Position(5,2));

        Move occupiedJump = new Move(new Position(3,4), new Position(5,6));

        MoveResult result = game.makeMove(occupiedJump);

        assertEquals(MoveResult.OCCUPIED, result,
                "Expected "  + MoveResult.OCCUPIED + ", but got " + result);

        result = game.makeMove(validJump);

        assertEquals(MoveResult.JUMP, result,
                "Expected "  + MoveResult.JUMP + ", but got " + result);
    }


    @Test
    public void testMultiJumpRed() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_1_4__1_6__3_6_RED_4_5, Color.RED);

        Position start = new Position(4,5);
        Position simpleMoveSpace = new Position(3,4);
        Position jumpOneSpace = new Position(2,7);
        Position jumpTwoSpace = new Position(0,5);


        Move invalidSimpleMove = new Move(start, simpleMoveSpace);
        MoveResult result = game.makeMove(invalidSimpleMove);

        assertEquals(MoveResult.CAN_PLAY_JUMP, result,
                "Expected " + MoveResult.CAN_PLAY_JUMP + ", but got " + result);

        Move jumpOne = new Move(start, jumpOneSpace);
        result = game.makeMove(jumpOne);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
        assertTrue(game.canPlayJumpMove());

        Move jumpTwo = new Move(jumpOneSpace, jumpTwoSpace);
        result = game.makeMove(jumpTwo);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);

        assertFalse(game.canPlayJumpMove());


    }


    @Test
    public void testMultiJumpWhite() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_3_4__RED_4_5__6_3__6_5, Color.WHITE);

        Position start = new Position(3,4);
        Position simpleMoveSpace = new Position(4,3);
        Position jumpOneSpace = new Position(5,6);
        Position jumpTwoSpace = new Position(7,4);


        Move invalidSimpleMove = new Move(start, simpleMoveSpace);
        MoveResult result = game.makeMove(invalidSimpleMove);

        assertEquals(MoveResult.CAN_PLAY_JUMP, result,
                "Expected " + MoveResult.CAN_PLAY_JUMP + ", but got " + result);

        Move jumpOne = new Move(start, jumpOneSpace);
        result = game.makeMove(jumpOne);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
        assertTrue(game.canPlayJumpMove());

        Move jumpTwo = new Move(jumpOneSpace, jumpTwoSpace);
        result = game.makeMove(jumpTwo);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);

        assertFalse(game.canPlayJumpMove());


    }

    @Test
    public void testRedKingSimpleMove() {

        Game game = TestBoards.getGameFromBoardContents(TestBoards.RED_KING_4_5, Color.RED);

        Position FIRST_POSITION = new Position(4,5);
        Position SECOND_POSITION = new Position(5,4);

        Move validMove = new Move(FIRST_POSITION, SECOND_POSITION);
        MoveResult result = game.makeMove(validMove);

        assertEquals(MoveResult.SIMPLE_MOVE, result);
    }

    @Test
    public void testWhiteKingSimpleMove() {

        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_KING_3_4, Color.WHITE);

        Position FIRST_POSITION = new Position(3,4);
        Position SECOND_POSITION = new Position(2,3);

        Move validMove = new Move(FIRST_POSITION, SECOND_POSITION);
        MoveResult result = game.makeMove(validMove);

        assertEquals(MoveResult.SIMPLE_MOVE, result);
    }

    @Test
    public void testMultiJumpRedKing() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_1_4__1_6__3_6_RED_KING_4_5, Color.RED);

        Position start = new Position(4, 5);
        Position simpleMoveSpace = new Position(3, 4);
        Position jumpOneSpace = new Position(2, 7);
        Position jumpTwoSpace = new Position(0, 5);
        Position jumpThreeSpace = new Position(2, 3);


        Move invalidSimpleMove = new Move(start, simpleMoveSpace);
        MoveResult result = game.makeMove(invalidSimpleMove);

        assertEquals(MoveResult.CAN_PLAY_JUMP, result,
                "Expected " + MoveResult.CAN_PLAY_JUMP + ", but got " + result);

        Move jumpOne = new Move(start, jumpOneSpace);
        result = game.makeMove(jumpOne);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
        assertTrue(game.canPlayJumpMove());

        Move jumpTwo = new Move(jumpOneSpace, jumpTwoSpace);
        result = game.makeMove(jumpTwo);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);

        assertTrue(game.canPlayJumpMove());

        Move jumpThree = new Move(jumpTwoSpace, jumpThreeSpace);
        result = game.makeMove(jumpThree);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
    }

    @Test
    public void testMultiJumpWhiteKing() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.WHITE_KING_3_4__RED_4_5__6_3__6_5, Color.WHITE);

        Position start = new Position(3, 4);
        Position simpleMoveSpace = new Position(4, 5);
        Position jumpOneSpace = new Position(5, 6);
        Position jumpTwoSpace = new Position(7, 4);
        Position jumpThreeSpace = new Position(5, 2);


        Move invalidSimpleMove = new Move(start, simpleMoveSpace);
        MoveResult result = game.makeMove(invalidSimpleMove);

        assertEquals(MoveResult.OCCUPIED, result,
                "Expected " + MoveResult.OCCUPIED + ", but got " + result);

        Move jumpOne = new Move(start, jumpOneSpace);
        result = game.makeMove(jumpOne);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
        assertTrue(game.canPlayJumpMove());

        Move jumpTwo = new Move(jumpOneSpace, jumpTwoSpace);
        result = game.makeMove(jumpTwo);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);

        assertTrue(game.canPlayJumpMove());

        Move jumpThree = new Move(jumpTwoSpace, jumpThreeSpace);
        result = game.makeMove(jumpThree);

        assertEquals(MoveResult.JUMP, result,
                "Expected " + MoveResult.JUMP + ", but got " + result);
    }

    @Test
    public void getResignee_Test() {
        Game CuT = testGame;
        Player resignee = player1;
        CuT.resignGame(resignee);
        assertEquals(CuT.getResignee(), resignee);
    }

    @Test
    public void getWinnerThroughResignation_Test() {
        Game CuT = testGame;
        Player resignee = player2;
        Player winner = player1;
        CuT.resignGame(resignee);

        assertEquals(CuT.getWinner(), winner);
    }

    @Test
    public void victoryThroughCaputresRed_Test() {
        String[] redWinningConfig = TestBoards.RED_KING_4_5;
        Piece.Color redPieceColor = Color.RED;
        Game winningBoard = TestBoards.getGameFromBoardContents(redWinningConfig, redPieceColor);
        Game CuT = winningBoard;

        assertTrue(CuT.isGameOver());
        if(CuT.isGameOver())
            assertEquals(CuT.getWinner(), CuT.getRedPlayer());
    }

    @Test
    public void victoryThroughCaptruesWhite_Test() {
        String[] whiteWinningConfig = TestBoards.WHITE_KING_3_4;
        Piece.Color whitePieceColor = Color.WHITE;
        Game winningBoard = TestBoards.getGameFromBoardContents(whiteWinningConfig, whitePieceColor);
        Game CuT = winningBoard;

        assertTrue(CuT.isGameOver());
        if(CuT.isGameOver())
            assertEquals(CuT.getWinner(), CuT.getWhitePlayer());
    }

   @Test
    public void testBackup() {
       Game game = TestBoards.getGameFromBoardContents(TestBoards.RED_KING_4_5, Color.RED);

       Position FIRST_POSITION = new Position(4,5);
       Position SECOND_POSITION = new Position(5,4);

       Move validMove = new Move(FIRST_POSITION, SECOND_POSITION);
       game.makeMove(validMove);
       assertTrue(game.backup());
    }

    @Test
    public void testBackupSingle() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Game game = new Game(p1, p2, 0);

        Position FIRST_POSITION = new Position(5,0);
        Position SECOND_POSITION = new Position(4,1);

        Move validMove = new Move(FIRST_POSITION, SECOND_POSITION);
        game.makeMove(validMove);

        //asserts true if the piece backed up from first position
        assertTrue(game.backup());

        //checks to see if piece has returned to its first position
        assertTrue(game.getBoard().getPieceAt(FIRST_POSITION) != null);
    }

    @Test
    public void getGameID_test() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Game CuT = new Game(p1, p2, 9999);

        assertEquals(CuT.getGameID(), 9999);
    }

    @Test
    public void getIsGameResigned_false_test() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Game CuT = new Game(p1, p2, 9999);

        assertFalse(CuT.getIsGameResigned());
    }

    @Test
    public void getIsGameResigned_true_test() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Game CuT = new Game(p1, p2, 9999);
        CuT.resignGame(p1);

        assertTrue(CuT.getIsGameResigned());
    }

    @Test
    public void getActiveColor_red_test() {
        Player redPlayer = new Player("Player1");
        Player whitePlayer = new Player("Player2");
        Game CuT = new Game(redPlayer, whitePlayer, 9999);
        Color activeColor = CuT.getActiveColor();

        assertEquals(activeColor, Color.RED);
    }

    @Test
    public void getActiveColor_white_test() {
        String[] whiteWinningConfig = TestBoards.WHITE_KING_3_4;
        Piece.Color whitePieceColor = Color.WHITE;
        Game CuT = TestBoards.getGameFromBoardContents(whiteWinningConfig, whitePieceColor);
        Color activeColor = CuT.getActiveColor();

        assertEquals(activeColor, Color.WHITE);
    }

    @Test
    public void getDirectMessages_test() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Game CuT = new Game(p1, p2, 9999);

        assertEquals(CuT.getDirectMessages(), "");
    }

    /*
    @Test
    public void kingPiece_Test() {
        Game game = TestBoards.getGameFromBoardContents(TestBoards.RED_1_3__3_6__2_7_WHITE_4_5, Color.RED);

        Position FIRST_POSITION = new Position(1,3);
        Position SECOND_POSITION = new Position(0,2);

        Move validMove = new Move(FIRST_POSITION, SECOND_POSITION);
        game.makeMove(validMove);
        game.kingPiece();

        assertEquals(game.getBoard().getPieceAt(SECOND_POSITION).getType(), Piece.Type.KING);

    }
     */
}
