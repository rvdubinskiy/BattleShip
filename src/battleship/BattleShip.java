package battleship;

public class BattleShip extends Ship {
    public BattleShip() {
        length = 4;
        hit = new boolean[4];
    }

    /**
     * Return length of given ship. It's 4 in {@link Battleship}
     *
     * @return 4
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * @return "battleship"
     * @see Ship#getShipType()
     */
    @Override
    public String getShipType() {
        return "battleship";
    }

}
