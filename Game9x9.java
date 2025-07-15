/** Used for a 9x9 tic-tac-toe game */
public class Game9x9 extends GameWithSections {
    
    private static final int BOARD_SIZE = 9;
    private static final int WINNING_POINTS = 13;
    private static final int REGION_SIZE = 3;

    /** Create a new 9x9 game to be played. */
    public Game9x9() {
        super(BOARD_SIZE, WINNING_POINTS, REGION_SIZE);
    }
}
