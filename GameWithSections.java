/** Represents a game of tic-tac-toe which has a board split into sections which are required to play in. */
public abstract class GameWithSections extends Game {
    
    private int regionSize;
    private int[] nextSection;
    
    public GameWithSections(int boardSize, int winningPoints, int regionSize) {
        super(boardSize, winningPoints);
        this.regionSize = regionSize;
        nextSection = new int[2];
        nextSection[0] = -1;
        nextSection[1] = -1;
    }

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

        if ((row < (nextSection[0] * regionSize)) || (row > ((nextSection[0] + 1) * regionSize - 1))) {
            return false;
        }
        if ((col < (nextSection[1] * regionSize)) || (col > ((nextSection[1] + 1) * regionSize - 1))) {
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

        for (int i = nextSection[0] * regionSize; i < (nextSection[0] + 1) * regionSize; i++) {
            for (int j = nextSection[1] * regionSize; j < (nextSection[1] + 1) * regionSize; j++) {
                if (board.getTile(i,j) == Board.EMPTY_TILE) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void printPlacementInInvalidSectionMessage() {
        System.out.println("Placement cannot be made in an invalid section!");
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
        if (anySectionPossible()) {
            System.out.println("You can place anywhere.");
        }
        
        System.out.println("The required section is [" + (nextSection[0] + 1) + ", " + (nextSection[1] + 1) + "].");
    }
}
