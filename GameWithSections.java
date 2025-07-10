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

    /**
     * Returns true if the given position is inside the required section to play in.
     * 
     * @param row  The row number of the position.
     * @param col  The column number of the position.
     * @return Boolean of if the tile is in the required section.
     */
    protected boolean tileInRequiredSection(int row, int col) {
        if ((nextSection[0] == -1) && (nextSection[1] == -1)) {
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

    protected boolean sectionFull() {
        return true;
    }

    protected void printSectionDetails() {

    }

    protected void updateNextSection(int[] pos) {

    }
}
