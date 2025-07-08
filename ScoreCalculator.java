import java.util.ArrayList;
import java.util.List;

/** Used for calculating scores of players from a board */
public class ScoreCalculator {
    
    private Board board;
    private char[] players;

    public ScoreCalculator(Board board, char player1, char player2) {
        this.board = board;
        this.players = new char[2];
        players[0] = player1;
        players[1] = player2;
    }
    
    public List<Integer> calculateScores() {
        // Initialise scores in local scope from zero, calculate scores from current board state
        //  and then update scores of players.
        
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            scores.add(0);
        }

        // Search all score-giving combinations on the board.

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                // Check NE(1,-1), E(0,1), SE(1,1) and S(1,0) directions for lines:
            }
        }

        return scores;
    }
}
