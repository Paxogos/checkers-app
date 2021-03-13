package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Row is used to with the Spaces to generate the elements in which player can use to play checkers
 * 
 * @version 1.0
 */
public class Row implements Iterable<Space>  {

    //attributes
    private int index; // must be 0 to 7
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

}
