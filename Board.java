/** Represents a square board of a game which can be any size */
public class Board {
    private static final char EMPTY_TILE = '_';
    
    private char[][] tiles;
    private int boardSize;

    /**
     * Create a new square game board with a specified side length.
     * @param boardSize The width and height of the board, as an int.
    */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        tiles = new char[boardSize][boardSize];

        // Initialise board to all empty spaces:
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                tiles[i][j] = EMPTY_TILE;
            }
        }
    }

    /** Prints the contents of this board to the terminal. */
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
    }
}
