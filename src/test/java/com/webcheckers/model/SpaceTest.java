package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
class SpaceTest {

    private static final int SMALL_IDX = -1;
    private static final int LARGE_IDX = 8;
    private static final int IDX = 7;

    GameCenter gameCenter = new GameCenter();
    BoardView boardView;

    private Game testGame = gameCenter.getGame(new Player("Test1"), new Player("Test2"));


    @BeforeEach
    void createBoardView(){
        boardView = new BoardView(testGame.getBoard());
    }


    @Test
    void ctor_too_small_1st_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(SMALL_IDX,IDX);}, "Allowed small 1st index");
    }

    @Test
    void ctor_too_small_2nd_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(IDX,SMALL_IDX);}, "Allowed small 2nd index");
    }

    @Test
    void ctor_too_large_1st_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(LARGE_IDX,IDX);}, "Allowed large 1st index");
    }

    @Test
    void ctor_too_large_2nd_arg() {
        assertThrows(IllegalArgumentException.class, () ->{
            new Space(IDX,LARGE_IDX);}, "Allowed large 2nd index");
    }

    @Test
    void ctor_create_board(){

        // check if board was created properly
        Iterator iterator = boardView.getRowArrayList().iterator();
        Row row;
        Space space;
        while(iterator.hasNext()){
            row = (Row) iterator.next();
            Iterator iterator1 = row.getSpaceArrayList().iterator();
            while(iterator1.hasNext()){
                space = (Space) iterator1.next();
                if((space.getRowIdx()+space.getCellIdx())%2 == 0){
                    assertEquals(space.getSpaceState(), Space.SpaceState.INVALID);
                }
            }

        }

    }

    @Test
    void getCellIdx() {
        Space CuT = new Space(2, 3);
        assertEquals(CuT.getCellIdx(), 3);
    }

    @Test
    void getRowIdx() {
        Space CuT = new Space(2, 3);
        assertEquals(CuT.getRowIdx(), 2);
    }

    @Test
    void isValid() {
        Space CuT = new Space(1, 1);
        assertFalse(CuT.isValid());
    }

    @Test
    void testToString() {
        Space CuT = new Space(1, 1);
        assertEquals(CuT.toString(), "Space{cellIdx=1}");
    }
}