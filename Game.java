import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Represents a game of tic-tac-toe */
public abstract class Game {
    protected Board board;
    protected List<Player> players;
    private int currentPlayerIndex;
    private int winningPoints;

    /**
     * Creates a new game to be played.
     * 
     * @param boardSize The amount of rows and columns of the board.
     * @param winningPoints The amount of points required by one player to win.
    */
    public Game(int boardSize, int winningPoints) {
        this.board = new Board(boardSize);
        initialisePlayers();
        currentPlayerIndex = 0;
        this.winningPoints = winningPoints;
    }

    /** Create and add the 'X' and 'O' players to this game. */
    private void initialisePlayers() {
        this.players = new ArrayList<>();
        players.add(new Player('X'));
        players.add(new Player('O'));
    }

    /**
     * Print a block of text containing scores of each player in this game.
     * 
     * @param titleMessage The heading for this section containing the scores.
     */
    private void printScores(String titleMessage) {
        System.out.println(titleMessage);
            for (Player player : players) {
                System.out.println(player.getSymbol() + ": "+ player.getPoints());
            }
        System.out.println();
    }

    /**
     * Controls the functionality of getting user input for choosing a tile position.
     * 
     * @return An int array of size 2 containing the row and column of the tile entered by the user.
     */
    protected abstract int[] getUserPlacedTilePosition();

    
    /** Calculate the scores of each player on the current board and update them for this game. */
    private void calculateScores() {
        
        int[] scores = (new ScoreCalculator(board, 
            players.get(0).getSymbol(), players.get(1).getSymbol())
            ).calculateScores();
        
        players.get(0).setPoints(scores[0]);
        players.get(1).setPoints(scores[1]);
    }

    /** Nothing happens for a generic game 
     * but for a game with sections, this method prints 
     * information about which section is required to play in.
    */
    protected void printSectionDetails() {
        
    }
    

    /** Runs a tic-tac-toe game from start to finish */
    public void run() {
        // Initialise variables
        boolean gameOngoing = true;
        char currentPlayerSymbol;
        int turnsElapsed = 0;
        
        // Loop for game while running
        while (gameOngoing) {
            currentPlayerSymbol = players.get(currentPlayerIndex).getSymbol();
            
            // Print scores
            printScores("Current Scoreboard:");
            
            // Print information about board
            System.out.println("Board State:");
            board.printBoard();
            System.out.println("\nIt is " + currentPlayerSymbol + "'s turn.");
            
            printSectionDetails();

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
            
            // If all the board spaces are full, the game is a stalemate.
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
        printScores("Final Scoreboard:");

        // Have the console keep showing for as long as the player desires to see final scores.
        Scanner closing = new Scanner(System.in);
        closing.nextLine();
        closing.close();
    }
}
