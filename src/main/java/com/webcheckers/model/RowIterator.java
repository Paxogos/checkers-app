package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class RowIterator implements Iterator {

    ArrayList<Space> spaceArrayList;
    int index;

    public RowIterator(Row row) {
        spaceArrayList = row.getSpaceArrayList();
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < spaceArrayList.size()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object next() {
        Space nextSpace = spaceArrayList.get(index);
        index++;
        return nextSpace;
    }
}
