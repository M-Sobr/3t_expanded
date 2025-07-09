import java.util.ArrayList;
import java.util.List;

/** Used for calculating scores of players from a board */
public class ScoreCalculator {
    
    private static final int PLAYER_COUNT = 2;

    private Board board;
    private char[] players;

    public ScoreCalculator(Board board, char player1, char player2) {
        this.board = board;
        this.players = new char[PLAYER_COUNT];
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

    public int[] calculateScores() {
        // Initialise scores in local scope from zero, calculate scores from current board state
        //  and then update scores of players.
        
        int[] scores = new int[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            scores[i] = 0;
        }

        // Search all score-giving combinations on the board.

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                int playerOwnerIndex = getPlayerOwnerIndex(row, col);
                
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

                // Search lengths for points:
                for (int lineLength : lineLengths) {
                    if ((lineLength >= 3) && (lineLength % 2 == 1)) {  // Give a point to the player owning the line
                        scores[playerOwnerIndex] ++;
                    } else if ((lineLength >= 3) && (lineLength % 2 == 0)) { // Give a point to the opposing player
                        scores[(playerOwnerIndex + 1) % 2] ++;  
                    }
                }
            }
        }

        return scores;
    }
}
