import java.util.List;
import java.util.ArrayList;

/** Used for a 3x3 tic-tac-toe game */
public class Game3x3 implements Game {
    
    private static final int BOARD_SIZE = 3;

    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;

    /** Create a new 3x3 game to be played. */
    public Game3x3() {
        this.board = new Board(BOARD_SIZE);
        initialisePlayers();
        currentPlayerIndex = 0;
    }

    private void initialisePlayers() {
        this.players = new ArrayList<>();
        players.add(new Player('X'));
        players.add(new Player('O'));
    }

    @Override
    public void run() {
        board.printBoard();
    }
}
