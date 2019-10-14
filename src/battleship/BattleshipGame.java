package battleship;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 */
public class BattleshipGame {

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        Ocean ocean = new Ocean();
        playGame(ocean);
        ocean.print();
    }

    /**
     * Here you can start your game and play this one until all ships wouldn't be destroyed
     *
     * @param ocean
     */
    private static void playGame(Ocean ocean) {
        System.out.println("Bro, nice to meet you :)\n");
        while (!ocean.isGameOver()) {
            ocean.print();
            displayEnemysFleet(ocean);

            PositionData rowColumn = new PositionData();
            checkInputData(rowColumn);

            System.out.println("______________________");
            System.out.println("______________________");

            shootAt(ocean, rowColumn.row, rowColumn.column);
            System.out.printf("Hits: %d/%d\n", ocean.getHitCount(), ocean.getShotsFired());
            System.out.printf("Sunk ships: %d\n", ocean.getShipsSunk());
        }

        System.out.println("Bro, thank you for this game! It was incredible effort to be the master of the sea!\n" +
                "Here you can see your total:");
        System.out.printf("Hits: %d/%d\n", ocean.getHitCount(), ocean.getShotsFired());
        System.out.printf("Sunk ships: %d\n", ocean.getShipsSunk());
    }

    /**
     * This method display all ships which are alive and have already destroyed
     *
     * @param ocean
     */
    private static void displayEnemysFleet(Ocean ocean) {
        System.out.print("You can check enemy's fleet there: ");

        for (var ship : ocean.getRealShipArray()) {
            System.out.print(ship.getShipLineString() + " ");
        }
        System.out.println();
    }

    /**
     * By using this method you can shoot at the ocean and also try to crumble enemy's fleet
     *
     * @param ocean
     * @param row
     * @param column
     */
    private static void shootAt(Ocean ocean, int row, int column) {
        if (ocean.shootAt(row, column)) {
            System.out.println("Yeah, right!!! You have hit at the ship!");
        } else {
            System.out.println("Oooops! You have missed(");
        }
    }


    /**
     * This method checks your input data while entering a row and column inside the terminal
     *
     * @param rowColumn
     */
    private static void checkInputData(PositionData rowColumn) {
        boolean flag = true;
        do {
            try {
                flag = true;
                System.out.print("Enter the row and column by using space between: ");

                Scanner in = new Scanner(System.in);
                String input = in.nextLine().trim();
                String[] numbers = input.split("[ ]+");
                if (numbers.length != 2) {
                    throw new BattleShipException("Bro, please, you need to enter two numbers by using space between!");
                } else {
                    rowColumn.row = Integer.parseInt(numbers[0]);
                    rowColumn.column = Integer.parseInt(numbers[1]);
                }

                if (rowColumn.row > 9 || rowColumn.row < 0 || rowColumn.column > 9 || rowColumn.column < 0) {
                    throw new BattleShipException("Numbers must be inside the interval: [0; 9]");
                }

            } catch (NumberFormatException ex) {
                flag = false;
                System.out.println("Bro, please, enter the right input numbers. All of us want to see this perfect game!");
            } catch (NoSuchElementException ex) {
                System.exit(1);
            } catch (Exception ex) {
                flag = false;
                System.out.println(ex.getMessage());
            }
        } while (!flag);
    }

    /**
     * This is a kinda structure which you use to save and forward the entered row and column another methods
     */
    static class PositionData {
        int row;
        int column;

        PositionData() {
        }
    }
}

/**
 * i think that is suitable example to avoid replicating of code and that's why i have used this one
 */
class BattleShipException extends Exception {
    BattleShipException(String mess) {
        super(mess);
    }
}
