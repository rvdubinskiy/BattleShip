package battleship;

public class Submarine extends Ship {
    public Submarine() {
        length = 1;
        hit = new boolean[1];
    }

    /**
     * Return length of given ship. It's 1 in this {@link Submarine}
     *
     * @return 1
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * @return "submarine"
     * @see Ship#getShipType()
     */
    @Override
    public String getShipType() {
        return "submarine";
    }


}
