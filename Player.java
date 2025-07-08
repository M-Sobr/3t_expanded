/** 
 * This class represents a player of the tic-tac-toe game. 
 * It contains information like which symbol represents the player 
 * and how many points this player has.
*/
public class Player {
    private char symbol;

    /**
     * Create a new player for a tic-tac-toe game.
     * 
     * @param symbol The symbol used to represent this player.
     */
    public Player(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing this player as a char type.
     * 
     * @return The symbol representing this player.
     */
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
