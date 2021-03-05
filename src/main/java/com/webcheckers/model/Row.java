package com.webcheckers.model;

import java.util.Iterator;

public class Row<T> extends BoardView {

    // must be 0 to 7
    private int index;

    @Override
    public Iterator iterator() {
        return super.iterator();
    }

    public int getIndex() {
        return index;
    }
}
