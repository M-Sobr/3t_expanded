import java.util.Scanner;

/** Used for a 4x4 tic-tac-toe game */
public class Game4x4 extends GameWithSections {
    
    private static final int BOARD_SIZE = 4;
    private static final int WINNING_POINTS = 2;
    private static final int REGION_SIZE = 2;    


    /** Create a new 4x4 game to be played. */
    public Game4x4() {
        super(BOARD_SIZE, WINNING_POINTS, REGION_SIZE);
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
                scanner = new Scanner(System.in);
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

            // Check that the quadrant matches what is required
            if ((!super.tileInRequiredSection(pos[0], pos[1])) && (!super.requiredSectionFull())) {
                super.printPlacementInInvalidSectionMessage();
                continue;
            }

            userInputRequired = false;
        }
        super.updateNextSection(pos);
        return pos;
    }
}
