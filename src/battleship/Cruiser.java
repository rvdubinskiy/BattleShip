package battleship;

public class Cruiser extends Ship {
    public Cruiser() {
        length = 3;
        hit = new boolean[3];
    }

    /**
     * Return length of given ship. It's 3 in {@link Cruiser}
     *
     * @return 3
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * @return "cruiser"
     * @see Ship#getShipType()
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }

}
