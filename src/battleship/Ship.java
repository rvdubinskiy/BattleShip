package battleship;

public abstract class Ship {
    int bowRow;
    int bowColumn;
    int length;

    boolean horizontal;
    boolean[] hit;


    /**
     * return length of ship
     *
     * @return
     */
    int getLength() {
        return length;
    }

    /**
     * return bow row
     *
     * @return
     */
    int getBowRow() {
        return bowRow;
    }

    /**
     * return bow column
     *
     * @return
     */
    int getBowColumn() {
        return bowColumn;
    }

    /**
     * @return
     */
    boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @param row
     */
    void setBowRow(int row) {
        bowRow = row;
    }

    /**
     * @param column
     */
    void setBowColumn(int column) {
        bowColumn = column;
    }

    /**
     * @param horizontal
     */
    void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * @return
     */
    String getShipType() {
        return "";
    }

    /**
     * checking possibilities to set current ship onto the map
     *
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        int count = 0;
        while (count < length) {
            if (isSuitableToSet(row, column, ocean)) {
                count++;
                if (horizontal) {
                    column++;
                } else {
                    row++;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * return boolean value if you can set current ship
     *
     * @param row
     * @param column
     * @param ocean
     * @return
     */
    private boolean isSuitableToSet(int row, int column, Ocean ocean) {
        if (this.isEmptySea() || row > 9 || row < 0 || column > 9 || column < 0) {
            return false;
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (!ocean.getShipArray()[row + i][column + j].isEmptySea() &&
                            ocean.getShipArray()[row + i][column + j] != this) {
                        return false;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        return true;
    }

    /**
     * if everything is okay, sure thing u can set up essential ship
     *
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        setBowColumn(column);
        setBowRow(row);
        setHorizontal(horizontal);
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                ocean.getShipArray()[row][column + i] = this;
            } else {
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }


    /**
     * @param row
     * @param column
     * @return
     */
    boolean shootAt(int row, int column) {
        if (isSunk()) {
            return false;
        }
        hit[(row - bowRow) + (column - bowColumn)] = true;
        return true;
    }

    /**
     * return boolean value if you killed enemy's ship
     *
     * @return
     */
    boolean isSunk() {
        for (int i = 0; i < hit.length; i++) {
            if (!hit[i]) return false;
        }
        return true;
    }

    /**
     * return ship line string which describe current situation connecting with enemy's fleet
     *
     * @return
     */
    public String getShipLineString() {
        if (isSunk()) {
            return "X".repeat(length);
        } else {
            return "s".repeat(length);
        }
    }

    /**
     * if certain point inside the map is Empty sea^ this method will return yeas, but here we have Ship class
     * that's why it will return false
     *
     * @return
     */
    public boolean isEmptySea() {
        return false;
    }

    public boolean isMissed(int row, int column, Ocean ocean) {
        if (ocean.getShipArray()[row][column].isEmptySea()) {
            return true;
        }
        return false;
    }

    /**
     * if you have hit at enemy's ship this program needs to display this action by changing value inside the hit array
     *
     * @param row
     * @param column
     * @return
     */
    public boolean isHit(int row, int column) {
        return hit[(row - bowRow) + (column - bowColumn)];
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        if (isSunk()) {
            return "x";
        }
        return ".";

    }


}
