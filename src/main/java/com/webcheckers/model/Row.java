package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The type Row.
 */
public class Row implements Iterable<Space>  {

    // must be 0 to 7
    private int index;
    private ArrayList<Space> spaceArrayList = new ArrayList<>();

    /**
     * Instantiates a new Row.
     *
     * @param index the index
     */
    public Row(int index) {
        this.index = index;
        for (int i = 0; i < 8; i++) {
            spaceArrayList.add(new Space(index,i));
        }
    }


    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator() {
        return this.getSpaceArrayList().iterator();
    }

    /**
     * Gets space array list.
     *
     * @return the space array list
     */
    public ArrayList<Space> getSpaceArrayList() {
        return spaceArrayList;
    }

    /**
     * Sets space array list.
     *
     * @param spaceArrayList the space array list
     */
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
