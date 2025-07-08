import java.util.ArrayList;
import java.util.List;

/** Represents a game of tic-tac-toe */
public abstract class Game {
    protected Board board;
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

    /**
     * Controls the functionality of getting user input for choosing a tile position.
     * 
     * @return An int array of size 2 containing the row and column of the tile entered by the user.
     */
    protected abstract int[] getUserPlacedTilePosition();

    /** Runs a tic-tac-toe game from start to finish */
    public void run() {
        boolean gameOngoing = true;
        char currentPlayerSymbol;
        
        while (gameOngoing) {
            currentPlayerSymbol = players.get(currentPlayerIndex).getSymbol();
            
            // Place a tile on the board of the appropriate player.
            System.out.println("Board State:");
            board.printBoard();
            System.out.println("\nIt is " + currentPlayerSymbol + "'s turn.");
            
            int[] placeTilePosition = getUserPlacedTilePosition();
            board.setTile(placeTilePosition[0], placeTilePosition[1], currentPlayerSymbol);
            System.out.println();
            
            // Change turn to next player.
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }

    }
}
