/** Used for a 4x4 tic-tac-toe game */
public class Game4x4 extends GameWithSections {
    
    private static final int BOARD_SIZE = 4;
    private static final int WINNING_POINTS = 2;
    private static final int REGION_SIZE = 2;    


    /** Create a new 4x4 game to be played. */
    public Game4x4() {
        super(BOARD_SIZE, WINNING_POINTS, REGION_SIZE);
    }
}
