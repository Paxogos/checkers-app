package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardViewTest {

    int GRID_LENGTH = BoardView.GRID_LENGTH;

    GameCenter gameCenter = new GameCenter();
    Game testGame = gameCenter.getGame(new Player("Test1"), new Player("Test2"),0);
    BoardView boardView;

    @Test
    void ctor(){
        
        boardView = new BoardView(testGame.getBoard());

        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_LENGTH; j++) {
                if((i+j)%2 == 1){
                    if(i < 3){
                        assertTrue(boardView.getRowArrayList().get(i).getSpaceArrayList().get(j).getPiece().equals(
                                new Single(Piece.Color.WHITE)
                        ));
                    }else if(i > 4){
                        assertTrue(boardView.getRowArrayList().get(i).getSpaceArrayList().get(j).getPiece().equals(
                                new Single(Piece.Color.RED)
                        ));
                    }
                }else{
                    assertTrue(boardView.getRowArrayList().get(i).getSpaceArrayList().get(j).getSpaceState() ==
                            Space.SpaceState.INVALID);
                }
            }
        }
    }

}