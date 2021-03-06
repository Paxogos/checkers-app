package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardIterator<T> implements Iterator {

    ArrayList<Row> rowArrayList;
    int index;

    public BoardIterator(Board board) {
        rowArrayList = board.getRowArrayList();
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < rowArrayList.size()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object next() {
        Row nextRow = rowArrayList.get(index);
        index++;
        return nextRow;
    }
}
