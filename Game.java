import java.util.ArrayList;
import java.util.List;

/** Represents a game of tic-tac-toe */
public abstract class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;

    /** Create a new 3x3 game to be played. */
    public Game(int boardSize) {
        this.board = new Board(boardSize);
        initialisePlayers();
        currentPlayerIndex = 0;
    }

    private void initialisePlayers() {
        this.players = new ArrayList<>();
        players.add(new Player('X'));
        players.add(new Player('O'));
    }

    private int[] getUserPlacedTilePosition() {
        int[] pos = new int[2];
        // Temporary functionality.
        pos[0] = 0;
        pos[1] = 0;
        return pos;
    }

    /** Runs a tic-tac-toe game from start to finish */
    public void run() {
        boolean gameOngoing = true;
        while (gameOngoing) {
            System.out.println("Board State:");
            board.printBoard();
            int[] placeTilePosition = getUserPlacedTilePosition();
            board.setTile(placeTilePosition[0], placeTilePosition[1], players.get(currentPlayerIndex).getSymbol());
        }

    }
}
