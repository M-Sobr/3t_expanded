import java.util.ArrayList;
import java.util.List;

/** Used for calculating scores of players from a board */
public class ScoreCalculator {
    
    private static final int PLAYER_COUNT = 2;

    private Board board;
    private char[] players;
    private int[] scores;

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

    private int getPlayerOwnerIndex(int row, int col) {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            if (board.getTile(row, col) == players[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private int getLineLengthInDirection(int rowPos, int colPos, int rowDirection, int colDirection) {
        char originSymbol = board.getTile(rowPos, colPos);
        int lineLength = 1;
        while (true) {
            rowPos += rowDirection;
            colPos += colDirection;
            if (!board.containsPosition(rowPos, colPos)) {
                return lineLength;
            }

            if (board.getTile(rowPos, colPos) == originSymbol) {
                lineLength ++;
            } else {
                return lineLength;
            }
        }
    }

    private int getSquareSizeAtPoint(int rowPos, int colPos) {
        char originSymbol = board.getTile(rowPos, colPos);
        int squareSize = 1;
        while (true) {
            for (int i = rowPos; i < rowPos + squareSize + 1; i++) {
                for (int j = colPos; j < colPos + squareSize + 1; j++) {
                    if (!board.containsPosition(i, j)) {
                        return squareSize;
                    }
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
        if ((lineLength >= 3) && (lineLength % 2 == 1)) {  
            scores[playerOwnerIndex] += ((lineLength - 1) / 2) * quantity;
        
        } else if ((lineLength >= 3) && (lineLength % 2 == 0)) {
            scores[(playerOwnerIndex + 1) % 2] += ((lineLength / 2) - 1) * quantity;
        }
    }


    /**
     * Calculate and add points for a length of a square. This does not cancel out points for other
     * scoring arrangements inside the square.
     * 
     * Points Earned:
     *  2: 1 point for opposing team,
     *  3: 1 point for own team,
     *  4: 2 points for opposing team,
     *  5: 2 points for own team,
     * ...
     * 
     * @param squareSize The length of the square.
     * @param quantity The number of squares of this size to score.
     * @param playerOwnerIndex The index of the player owning the line.
     */
    private void calculateSquareScore(int squareSize, int quantity, int playerOwnerIndex) {
        if ((squareSize > 1) && (squareSize % 2 == 0)) {
            scores[(playerOwnerIndex + 1) % 2] += (squareSize / 2) * quantity;

        } else if ((squareSize > 1) && (squareSize % 2 == 1)) {
            scores[playerOwnerIndex] += ((squareSize - 1) / 2) * quantity;
                
        }
    }


    public int[] calculateScores() {
        // Initialise scores in local scope from zero, calculate scores from current board state
        //  and then update scores of players.

        // Search all score-giving combinations on the board.

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                int playerOwnerIndex = getPlayerOwnerIndex(row, col);
                int opposingPlayerIndex = (playerOwnerIndex + 1) % 2;
                
                // If the tile is unowned, continue.
                if (playerOwnerIndex == -1) {
                    continue;
                }
                
                // Check NE(-1,1), E(0,1), SE(1,1) and S(1,0) directions for lines:
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
                    calculateLineScore(lineLength - 1, -1, playerOwnerIndex);
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
                calculateSquareScore(squareSize - 1, -3, playerOwnerIndex);
                
                // Cancel lines
                calculateLineScore(squareSize, -(2 * squareSize + 2), playerOwnerIndex);
                for (int i = squareSize - 1; i >= 3; i--) {
                    calculateLineScore(i, -4, playerOwnerIndex);
                }
            }
        }

        return scores;
    }
}
