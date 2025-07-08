/** Represents a square board of a game which can be any size */
public class Board {
    public static final char EMPTY_TILE = '_';
    
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

    /**
     * Returns the number of rows of this board.
     * 
     * @return The number of rows of the board.
     */
    public int getRows() {
        return boardSize;
    }

    /**
     * Returns the number of columns of this board.
     * 
     * @return The number of columns of the board.
     */
    public int getCols() {
        return boardSize;
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

    public char getTile(int row, int col) {
        if ((row < boardSize) && (col < boardSize)) {  // Tile position is valid
            return tiles[row][col];
        } else {
            throw new InvalidPositionException("Position [" + row + ", " + col + "] is not valid on this board!");
        }
    }


    /**
     * Set the character of a symbol on the board.
     * 
     * @param row The row number of the tile to change on the board.
     * @param col The column number of the tile to change on the board.
     * @param symbol  The symbol to replace the tile with, given as a char.
     * 
     * @throws InvalidPositionException When the given row and col is not a valid position on the board.
     */
    public void setTile(int row, int col, char symbol) {
        if ((row < boardSize) && (col < boardSize)) {  // Tile position is valid
            tiles[row][col] = symbol;
        } else {
            throw new InvalidPositionException("Position [" + row + ", " + col + "] is not valid on this board!");
        }
    }
}
