/** 
 * This class represents a player of the tic-tac-toe game. 
 * It contains information like which symbol represents the player 
 * and how many points this player has.
*/
public class Player {
    private char symbol;
    private int points;

    /**
     * Create a new player for a tic-tac-toe game.
     * 
     * @param symbol The symbol used to represent this player.
     */
    public Player(char symbol) {
        this.symbol = symbol;
        this.points = 0;
    }

    /**
     * Returns the symbol representing this player as a char type.
     * 
     * @return The symbol representing this player.
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Returns the number of points this player has. This does not include calculation of the points of any player.
     * 
     * @return The number of points of this player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the number of points this player has to the value provided.
     * 
     * @param points  The number of points this player has.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
