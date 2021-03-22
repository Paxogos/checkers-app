package com.webcheckers.application;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class GameCenterTest {

    String RED_PLAYER_NAME = "redPlayer";
    String WHITE_PLAYER_NAME = "whitePlayer";

    Player RED_PLAYER = new Player(RED_PLAYER_NAME);
    Player WHITE_PLAYER = new Player(WHITE_PLAYER_NAME);
    Player THIRD_PLAYER = new Player("notActive");

    Game testGameRW = new Game(RED_PLAYER, WHITE_PLAYER);
    Game testGameWR = new Game(WHITE_PLAYER, RED_PLAYER);

    @Test
    public void testGetGame() {
        GameCenter testGC = new GameCenter();
        Game newGame = testGC.getGame(RED_PLAYER, WHITE_PLAYER);
        assertEquals(newGame, testGameRW);
        assertEquals(newGame.getRedPlayer(), RED_PLAYER);
        assertEquals(newGame.getWhitePlayer(), WHITE_PLAYER);
    }

    @Test
    public void testGameDoesNotExist() {
        GameCenter testGC = new GameCenter();
        assertFalse(testGC.gameExists(RED_PLAYER, WHITE_PLAYER),
                "Detected a game when there shouldn't be one");
    }

    @Test
    public void testGameDoesExist_RegularArgs() {
        GameCenter testGC = new GameCenter();
        testGC.getGame(RED_PLAYER, WHITE_PLAYER);
        assertTrue(testGC.gameExists(RED_PLAYER, WHITE_PLAYER),
                "Game should exist between Red and White");
    }

    @Test
    public void testGameDoesExist_ReversedArgs() {
        GameCenter testGC = new GameCenter();
        testGC.getGame(RED_PLAYER, WHITE_PLAYER);
        assertTrue(testGC.gameExists(WHITE_PLAYER, RED_PLAYER),
                    "Game should exist between White and Red");
    }

    @Test
    public void testGetOpponent() {
        GameCenter testGC = new GameCenter();
        testGC.getGame(RED_PLAYER, WHITE_PLAYER);

        assertEquals(WHITE_PLAYER, testGC.getCurrentOpponent(RED_PLAYER),
                WHITE_PLAYER.getName() + "'s opponent was supposed to be " + RED_PLAYER.getName() +
                        ", but was " +testGC.getCurrentOpponent(RED_PLAYER));
        assertEquals(RED_PLAYER, testGC.getCurrentOpponent(WHITE_PLAYER),
                RED_PLAYER.getName() + "'s opponent was supposed to be " + WHITE_PLAYER.getName() +
                        ", but was " +testGC.getCurrentOpponent(WHITE_PLAYER));
    }

    @Test
    public void testGetCorrectKey() {
        GameCenter testGC = new GameCenter();
        testGC.getGame(RED_PLAYER, WHITE_PLAYER);

        assertEquals(RED_PLAYER_NAME + WHITE_PLAYER_NAME, testGC.getCorrectKey(RED_PLAYER, WHITE_PLAYER),
                "Key should exist for Red and White with arg(Red,White).");
        assertEquals(RED_PLAYER_NAME + WHITE_PLAYER_NAME, testGC.getCorrectKey(WHITE_PLAYER, RED_PLAYER),
                "Key should exist for Red and White with arg(White,Red).");
        assertNull(testGC.getCorrectKey(RED_PLAYER, THIRD_PLAYER),
                "Key should return null for arg(Red,Third_User).");
    }


}
