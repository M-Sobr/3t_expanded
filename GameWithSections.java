import java.util.Scanner;

/** Represents a game of tic-tac-toe which has a board split into sections which are required to play in. */
public abstract class GameWithSections extends Game {
    
    private int regionSize;
    private int[] nextSection;
    
    /**
     * Creates a new tic-tac-toe game which has sections to play in.
     * 
     * @param boardSize The rows and columns of this board.
     * @param winningPoints The amount of points required to win.
     * @param regionSize The size (rows/columns) of each region in this game.
     */
    public GameWithSections(int boardSize, int winningPoints, int regionSize) {
        super(boardSize, winningPoints);
        this.regionSize = regionSize;
        nextSection = new int[2];
        nextSection[0] = -1;
        nextSection[1] = -1;
    }

    /**
     * Checks if there is a required section or not.
     * 
     * @return True if the next section is [-1, -1] which represents any section possible to place in.
     */
    private boolean anySectionPossible() {
        return (nextSection[0] == -1) && (nextSection[1] == -1);
    }

    /**
     * Returns true if the given position is inside the required section to play in.
     * 
     * @param row  The row number of the position.
     * @param col  The column number of the position.
     * @return Boolean of if the tile is in the required section.
     */
    protected boolean tileInRequiredSection(int row, int col) {
        if (anySectionPossible()) {
            return true;
        }

        if ((row < (nextSection[0] * regionSize)) || (row > (((nextSection[0] + 1) * regionSize) - 1))) {
            return false;
        }
        if ((col < (nextSection[1] * regionSize)) || (col > (((nextSection[1] + 1) * regionSize) - 1))) {
            return false;
        }
        return true;

    }

    /**
     * @return True if every tile in a section is occupied by a tile.
     */
    protected boolean requiredSectionFull() {
        if (anySectionPossible()) {
            return false;
        }

        // Check every square in the region and return false if one tile is empty.
        for (int i = nextSection[0] * regionSize; i < (nextSection[0] + 1) * regionSize; i++) {
            for (int j = nextSection[1] * regionSize; j < (nextSection[1] + 1) * regionSize; j++) {
                if (board.getTile(i,j) == Board.EMPTY_TILE) {
                    return false;
                }
            }
        }
        // All squares in the region are occupied.
        return true;
    }

    /** Tells the user that their attempted placement was in the wrong section and which section is required to place in. */
    protected void printPlacementInInvalidSectionMessage() {
        System.out.println("Placement cannot be made in the wrong section!");
        System.out.println("The required section is [" + (nextSection[0] + 1) + ", " + (nextSection[1] + 1) + "].");
    }

    /** Update the section for placing a tile next from a given position. 
     * 
     * @param pos The position which the tile was placed in, as an array containing [row, col].
    */
    protected void updateNextSection(int[] pos) {
        nextSection[0] = pos[0] % regionSize;
        nextSection[1] = pos[1] % regionSize;
    }

    @Override
    protected void printSectionDetails() {
        if (anySectionPossible() || requiredSectionFull()) {
            System.out.println("You can place anywhere.");
            return;
        }
        
        System.out.println("The required section is [" + (nextSection[0] + 1) + ", " + (nextSection[1] + 1) + "].");
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

            // Check that position is inside the board.
            if (pos[0] >= board.getRows() || pos[1] >= board.getCols() || pos[0] < 0 || pos[1] < 0) {
                System.out.println("\nInvalid position!");
                continue;
            }

            // Check that the quadrant matches what is required.
            if ((!tileInRequiredSection(pos[0], pos[1])) && (!requiredSectionFull())) {
                printPlacementInInvalidSectionMessage();
                continue;
            }

            // Check that the position is empty
            if (board.getTile(pos[0], pos[1]) != Board.EMPTY_TILE) {
                System.out.println("\nThe tile must be empty!");
                continue;
            }

            // The placement has succeeded so exit the loop and then return this position for placement.
            userInputRequired = false;
        }
        updateNextSection(pos);
        return pos;
    }
}
