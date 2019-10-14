package battleship;

import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 */
public class Ocean {
    Ship[][] ships = new Ship[10][10];
    private Ship[] ship;

    int shotsFired;
    int hitCount;
    int shipsSunk;


    /**
     * this is a constructor of completely important class
     */
    public Ocean() {
        hitCount = 0;
        shotsFired = 0;
        shipsSunk = 0;
        ship = new Ship[10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
            }
        }
        placeAllShips();
    }

    /**
     * the process of creating 10 suitable ships from enemy's fleet
     */
    void placeAllShips() {
        Random rand = new Random(LocalDateTime.now().getNano());
        for (int i = 0; i < 1; i++) {
            ship[i] = new BattleShip();
        }
        for (int i = 1; i < 3; i++) {
            ship[i] = new Cruiser();
        }
        for (int i = 3; i < 6; i++) {
            ship[i] = new Destroyer();
        }
        for (int i = 6; i < 10; i++) {
            ship[i] = new Submarine();
        }
        for (var ship : ship) {
            setShip(ship, rand);
        }
        System.gc();
        System.runFinalization();
    }

    /**
     * in my application this is useless method cuz everything is done without it
     *
     * @param row
     * @param column
     * @return
     */
    boolean isOccupied(int row, int column) {
        if (ships[row][column].getShipType() != null) {
            return true;
        }
        return false;
    }


    /**
     * this method uses to shoot at essential point by using certain coordinates
     *
     * @param row
     * @param column
     * @return
     */
    boolean shootAt(int row, int column) {
        try {
            shotsFired++;
            if (getShipArray()[row][column].shootAt(row, column)) {
                hitCount++;
                if (getShipArray()[row][column].isSunk()) {
                    shipsSunk++;
                }
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    /**
     * return the number of done shots
     *
     * @return
     */
    int getShotsFired() {
        return shotsFired;
    }

    /**
     * return the number of precise hits
     *
     * @return
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     * return the number of sunk ships
     *
     * @return
     */
    int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * return boolean value if the user killed 10 enemy's ships
     *
     * @return
     */
    boolean isGameOver() {
        return shipsSunk == 10;
    }

    /**
     * return all ocean by using ship array
     *
     * @return
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * here you can see the method which represent all ships and every action onto the map
     */
    void print() {
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
//                System.out.print(ships[i][j].toString());
                printSymbol(i, j, ships[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * printing suitable or certain symbol for each action
     *
     * @param row
     * @param column
     * @param ship
     */
    private void printSymbol(int row, int column, Ship ship) {
        if (ship.isEmptySea()) {
            System.out.print(ships[row][column].toString() + ' ');
        } else if (ship.isSunk()) {
            System.out.print("x" + ' ');
        } else if (ship.isHit(row, column)) {
            System.out.print("s" + ' ');
        } else if (ship.isMissed(row, column, this)) {
            System.out.print("*" + ' ');
        } else {
            System.out.print("." + ' ');
        }
    }

    /**
     * return real ship array. I mean you see the ship is either alive or not
     *
     * @return
     */
    public Ship[] getRealShipArray() {
        return ship;
    }

    /**
     * effort to set a ship on to the map (ocean)
     *
     * @param ship
     * @param rand
     */
    private void setShip(Ship ship, Random rand) {
        int row, column;
        boolean isHorizontal, flag;
        do {
            isHorizontal = rand.nextBoolean();
            row = rand.nextInt(9);
            column = rand.nextInt(9);

            flag = ship.okToPlaceShipAt(row, column, isHorizontal, this);
        } while (!flag);
        ship.placeShipAt(row, column, isHorizontal, this);
    }


}
