package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements Iterable<Space>  {

    //attributes
    private int index; // must be 0 to 7
    private ArrayList<Space> spaceArrayList = new ArrayList<>();

    public Row(int index) {
        this.index = index;
        for (int i = 0; i < 8; i++) {
            spaceArrayList.add(new Space(index,i));
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator() {
        return this.getSpaceArrayList().iterator();
    }

    public ArrayList<Space> getSpaceArrayList() {
        return spaceArrayList;
    }

    public void setSpaceArrayList(ArrayList<Space> spaceArrayList) {
        this.spaceArrayList = spaceArrayList;
    }

    @Override
    public String toString() {
        return "Row{" +
                "index=" + index +
                '}';
    }
}
