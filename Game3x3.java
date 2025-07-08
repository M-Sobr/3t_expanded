/** Used for a 3x3 tic-tac-toe game */
public class Game3x3 implements Game {
    
    private static final int BOARD_SIZE = 3;

    private Board board;
    
    /** Create a new 3x3 game to be played. */
    public Game3x3() {
        this.board = new Board(BOARD_SIZE);
    }

    @Override
    public void run() {
        board.printBoard();
    }
}
