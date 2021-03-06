package com.webcheckers.model;

public class Space {

    public enum SpaceColor{LIGHT, DARK}

    // must be 0 to 7
    private int cellIdx;
    private Piece piece;
    private SpaceColor spaceColor;

    public Space(int cellIdx, Piece piece) {
        this.cellIdx = cellIdx;
        this.piece = piece;

        /*
        Dark peices will generate as odds and Light pieces will generate as even, while building the board
        in an S-shape
         */
        if(cellIdx % 2 != 0)
            spaceColor = SpaceColor.DARK;
        else
            spaceColor = SpaceColor.LIGHT;
    }

    /**
     *
     * @return true if space is vacant, and if the space is a dark square
     */
    public boolean isValid(){
        return piece == null && spaceColor == SpaceColor.DARK;

    }

    public int getCellIdx() {
        return cellIdx;
    }

    public Piece getPiece(){
        return piece;
    }

}
