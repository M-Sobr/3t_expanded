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

    protected boolean tileInRequiredSection(int row, int col) {
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
