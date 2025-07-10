import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Represents a game of tic-tac-toe */
public abstract class Game {
    protected Board board;
    protected List<Player> players;
    private int currentPlayerIndex;
    private int winningPoints;

    /** Create a new 3x3 game to be played. */
    public Game(int boardSize, int winningPoints) {
        this.board = new Board(boardSize);
        initialisePlayers();
        currentPlayerIndex = 0;
        this.winningPoints = winningPoints;
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

    
    /** Calculate the scores of each player on the current board. */
    private void calculateScores() {
        
        int[] scores = (new ScoreCalculator(board, 
            players.get(0).getSymbol(), players.get(1).getSymbol())
            ).calculateScores();
        
        players.get(0).setPoints(scores[0]);
        players.get(1).setPoints(scores[1]);
    }
    

    /** Runs a tic-tac-toe game from start to finish */
    public void run() {
        boolean gameOngoing = true;
        char currentPlayerSymbol;
        int turnsElapsed = 0;
        
        while (gameOngoing) {
            currentPlayerSymbol = players.get(currentPlayerIndex).getSymbol();
            
            // Print scores
            System.out.println("Current Scoreboard:");
            for (Player player : players) {
                System.out.println(player.getSymbol() + ": "+ player.getPoints());
            }
            System.out.println();
            
            // Print information about board
            System.out.println("Board State:");
            board.printBoard();
            System.out.println("\nIt is " + currentPlayerSymbol + "'s turn.");
            
            // Place a tile on the board of the appropriate player.
            int[] placeTilePosition = getUserPlacedTilePosition();
            board.setTile(placeTilePosition[0], placeTilePosition[1], currentPlayerSymbol);
            System.out.println();
            
            // Calculate scores of players and check if game has ended or not.
            calculateScores();
            for (Player player : players) {
                if (player.getPoints() >= winningPoints) {
                    gameOngoing = false;
                    System.out.println("Player " + player.getSymbol() + " has won the game!");
                }
            }

            turnsElapsed ++;
            if (turnsElapsed == board.getRows() * board.getCols()) {
                gameOngoing = false;
                System.out.println("All board positions are filled so the game is a stalemate.");
                System.out.println("No player wins.");
            }

            // Change turn to next player.
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }

        // Print final scores
        System.out.println("Final Scoreboard:");
        for (Player player : players) {
            System.out.println(player.getSymbol() + ": "+ player.getPoints());
        }
        System.out.println();

        Scanner closing = new Scanner(System.in);
        closing.nextLine();
        closing.close();
    }
}
