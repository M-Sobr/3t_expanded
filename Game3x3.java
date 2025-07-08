import java.util.Scanner;

/** Used for a 3x3 tic-tac-toe game */
public class Game3x3 extends Game {
    
    private static final int BOARD_SIZE = 3;

    /** Create a new 3x3 game to be played. */
    public Game3x3() {
        super(BOARD_SIZE);
    }

    @Override
    protected int[] getUserPlacedTilePosition() {
        int[] pos = new int[2];
        boolean userInputRequired = true;
        Scanner scanner = new Scanner(System.in);

        while (userInputRequired) {
            System.out.println("Enter a valid position on the board:");
            System.out.print("Row: ");
            try {
                // Shift row and col numbers by -1 so user input is between 1-3 and code uses 0-2.
                pos[0] = scanner.nextInt() - 1;
                System.out.print("Col: ");
                pos[1] = scanner.nextInt() - 1;
            
            } catch(Exception e) {
                System.out.println("\nInvalid number entered!");
                continue;
            }
            // Check that position is inside the board
            if (pos[0] >= BOARD_SIZE || pos[1] >= BOARD_SIZE) {
                System.out.println("\nInvalid position!");
                continue;
            }

            // Check that the position is empty
            if (board.getTile(pos[0], pos[1]) != Board.EMPTY_TILE) {
                System.out.println("\nThe tile must be empty!");
                continue;
            }
            userInputRequired = false;
        }
        return pos;
    }

}
