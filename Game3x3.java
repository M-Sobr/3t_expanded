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
        // Temporary functionality.
        pos[0] = 0;
        pos[1] = 0;
        return pos;
    }

}
