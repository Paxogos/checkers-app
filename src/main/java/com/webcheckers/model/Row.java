package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements Iterable<Space>  {

    // must be 0 to 7
    private int index;
    private ArrayList<Space> spaceArrayList = new ArrayList<>();

    public Row(int index) {
        this.index = index;
        for (int i = 0; i < 8; i++) {
            spaceArrayList.add(new Space(i));
        }
    }


    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Row{" +
                "index=" + index +
                '}';
    }

    @Override
    public Iterator<Space> iterator() {
        return new RowIterator(this);
    }

    public ArrayList<Space> getSpaceArrayList() {
        return spaceArrayList;
    }
}
