package battleship;

public class Destroyer extends Ship {
    public Destroyer() {
        length = 2;
        hit = new boolean[2];
    }

    /**
     * Return length of given ship. It's 2 in {@link Destroyer}
     * @return 2
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * @see Ship#getShipType()
     * @return "destroyer"
     */
    @Override
    public String getShipType() {
        return "destroyer";
    }

}
