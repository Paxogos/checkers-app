package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Row>{
    
    private ArrayList<Row> rowArrayList = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 8; i++) {
            rowArrayList.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return new BoardIterator<>(this);

    }

    public ArrayList<Row> getRowArrayList() {
        return rowArrayList;
    }
}
