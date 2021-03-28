package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardViewTest {

    BoardView boardView;
    int GRID_LENGTH = BoardView.GRID_LENGTH;

    @Test
    void ctor(){
        
        boardView = new BoardView();

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