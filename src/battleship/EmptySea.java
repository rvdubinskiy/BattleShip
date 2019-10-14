package battleship;

public class EmptySea extends Ship {

    boolean miss = false;

    public EmptySea() {
        length = 1;
    }

    /**
     * @param row
     * @param column
     * @return
     */
    @Override
    boolean shootAt(int row, int column) {
        miss = true;
        return false;
    }

    /**
     * @return false
     */
    @Override
    String getShipType() {
        return null;
    }

    /**
     * @return null
     */
    @Override
    public boolean isEmptySea() {
        return true;
    }

    /**
     * @return true
     */


    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * @return false
     */
    @Override
    public String toString() {
        if (miss) {
            return "*";
        }
        return ".";
    }
}
