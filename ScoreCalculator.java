/** Used for calculating scores of players from a board */
public class ScoreCalculator {
    
    // There are always 2 players in a game of tic-tac-toe.
    private static final int PLAYER_COUNT = 2;

    // Used for scoring calculations. The game may behave wierdly if these are changed.
    private static final int MIN_LINE_LENGTH = 3;
    private static final int MIN_SQUARE_SIZE = 2;

    private Board board;
    private char[] players;
    private int[] scores;

    /**
     * Creates a new Score Calculator with relevant information about the game.
     * The scoring works the same way for any board size.
     * 
     * @param board  The board of the game required.
     * @param player1 The symbol representing the first player of the game.
     * @param player2 The symbol representing the second player of the game.
     */
    public ScoreCalculator(Board board, char player1, char player2) {
        this.board = board;
        this.players = new char[PLAYER_COUNT];
        this.scores = new int[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            scores[i] = 0;
        }
        players[0] = player1;
        players[1] = player2;
    }

    /**
     * Look at the board and find which player owns the tile at the given position.
     * 
     * @param row The row number of the tile.
     * @param col The column number of the tile.
     * @return The index of which player owns the tile, or -1 if the tile is unowned.
     */
    private int getPlayerOwnerIndex(int row, int col) {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            if (board.getTile(row, col) == players[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the opposing player index to the one inputted into this function.
     * 
     * @param playerIndex The index of the player to find the opponent of.
     * @return The index of the opposing player.
     */
    private int getOpposingPlayerIndex(int playerIndex) {
        return (playerIndex + 1) % 2;
    }
    
    /**
     * Finds the length of the line of tiles owned by the same player, 
     * starting from a given origin position and 
     * going in a given direction which is represented by a vector.
     * 
     * @param rowPos The starting row of the line.
     * @param colPos The starting column of the line.
     * @param rowDirection The amount of rows moved per step of the line.
     * @param colDirection The amount of columns moved per step of the line.
     * 
     * @return The length of this line.
     */
    private int getLineLengthInDirection(int rowPos, int colPos, int rowDirection, int colDirection) {
        
        // Initialise variables
        char originSymbol = board.getTile(rowPos, colPos);
        int lineLength = 1;
        
        // Keep going along the line until it ends.
        while (true) {
            
            // Access the next position along the line.
            rowPos += rowDirection;
            colPos += colDirection;
            
            // If the position being checked is outside of the board, the line has ended.
            if (!board.containsPosition(rowPos, colPos)) {
                return lineLength;
            }

            // Checks if the owner of this tile is the same as the owner of the original tile.
            if (board.getTile(rowPos, colPos) == originSymbol) {
                lineLength ++;
            } else {
                return lineLength;
            }
        }
    }

    /**
     * From a given point, return the size of the square at the point.
     * The square is found going down and right from the point only, ensuring that each square
     * is only counted once.
     * 
     * @param rowPos The row of the tile being checked.
     * @param colPos The column of the tile being checked.
     * @return The square size at this point.
     */
    private int getSquareSizeAtPoint(int rowPos, int colPos) {
        
        // Initialise variables
        char originSymbol = board.getTile(rowPos, colPos);
        int squareSize = 1;
        
        // Keep checking tiles until the square has ended.
        while (true) {
            
            // Check every tile in the square for each square size.
            for (int i = rowPos; i <= rowPos + squareSize; i++) {
                for (int j = colPos; j <= colPos + squareSize; j++) {
                    
                    // If the position being checked is outside of the board, the square has ended.
                    if (!board.containsPosition(i, j)) {
                        return squareSize;
                    }

                    // If the owner of the tile is different, the square has ended.
                    if (board.getTile(i, j) != originSymbol) {
                        return squareSize;
                    }
                }
            }
            squareSize ++;
        }
    }

    /**
     * Calculate and add points for a length of a line. This does not cancel
     * out points earned for other objects within the line.
     * 
     * Amount of points earned:
     *  3: 1 point for own team,
     *  4: 1 point for opposing team,
     *  5: 2 points for own team,
     *  6: 2 points for opposing team,
     *  7: 3 points for own team,
     *  etc.
     * 
     * 
     * @param lineLength The length of the line.
     * @param quantity The number of lines of this length to score.
     * @param playerOwnerIndex The index of the player owning the line.
     */
    private void calculateLineScore(int lineLength, int quantity, int playerOwnerIndex) {
        // Odd number line lengths add score to the player owning the line.
        if ((lineLength >= MIN_LINE_LENGTH) && (lineLength % 2 == 1)) {   
            scores[playerOwnerIndex] += ((lineLength - 1) / 2) * quantity;
        
        // Even number line lengths add score to the opposing player to the one owning the line.
        } else if ((lineLength >= MIN_LINE_LENGTH) && (lineLength % 2 == 0)) { 
            scores[getOpposingPlayerIndex(playerOwnerIndex)] += ((lineLength / 2) - 1) * quantity;
        }
    }


    /**
     * Calculate and add points for a length of a square. This does not cancel out points for other
     * scoring arrangements inside the square.
     * 
     * Points Earned:
     *  2: 1 point for opposing team,
     *  3: 3 point for own team,
     *  4: 5 points for opposing team,
     *  5: 7 points for own team,
     * ...
     * 
     * @param squareSize The length of the square.
     * @param quantity The number of squares of this size to score.
     * @param playerOwnerIndex The index of the player owning the line.
     */
    private void calculateSquareScore(int squareSize, int quantity, int playerOwnerIndex) {
        // Even number square sizes add score to the opposing player to the one owning the square.
        if ((squareSize >= MIN_SQUARE_SIZE) && (squareSize % 2 == 0)) {
            scores[getOpposingPlayerIndex(playerOwnerIndex)] += (squareSize * 2 - 3) * quantity;

        // Odd number square sizes add score to the player owning the square.
        } else if ((squareSize >= MIN_SQUARE_SIZE) && (squareSize % 2 == 1)) {
            scores[playerOwnerIndex] += (squareSize * 2 - 3) * quantity;
                
        }
    }


    public int[] calculateScores() {
        // Initialise scores in local scope from zero, calculate scores from current board state
        //  and then update scores of players.

        // Search all score-giving combinations on the board.

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                int playerOwnerIndex = getPlayerOwnerIndex(row, col);
                
                // If the tile is unowned, no scoring calculations need to take place so continue to the next tile.
                if (playerOwnerIndex == -1) {
                    continue;
                }
                
                // Check NE(-1,1), E(0,1), SE(1,1) and S(1,0) directions for lines.
                // Other 4 directions would double count lines.

                int[] lineLengths = new int[4];
                lineLengths[0] = getLineLengthInDirection(row, col, -1, 1);
                lineLengths[1] = getLineLengthInDirection(row, col, 0, 1);
                lineLengths[2] = getLineLengthInDirection(row, col, 1, 1);
                lineLengths[3] = getLineLengthInDirection(row, col, 1, 0);

                /** Search lines for points.
                 * 
                 * Each line also contains a line of the previous length to cancel out.
                 */
                for (int lineLength : lineLengths) {
                    calculateLineScore(lineLength, 1, playerOwnerIndex);
                    calculateLineScore((lineLength - 1), -1, playerOwnerIndex);
                }

                /** Search squares for points
                 * 
                 *  Amount of lines to cancel out for each square:
                 *  -> n n-length rows
                 *  -> n n-length cols
                 *  -> 2 diagonals of n-length
                 *  -> 4 diagonals of (n-1)-length and all previous diagonal lengths
                 * 
                 *  Also each square should cancel out 3x points earned by previous square, as
                 *   this is contained in the big square.
                 */
                
                int squareSize = getSquareSizeAtPoint(row, col);
                calculateSquareScore(squareSize, 1, playerOwnerIndex);
                calculateSquareScore((squareSize - 1), -3, playerOwnerIndex);
                
                // Cancel lines
                calculateLineScore(squareSize, -(2 * squareSize + 2), playerOwnerIndex);
                for (int i = squareSize - 1; i >= MIN_LINE_LENGTH; i--) {
                    calculateLineScore(i, -4, playerOwnerIndex);
                }
            }
        }

        return scores;
    }
}
