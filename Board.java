public class Board {
    private char[][] tiles = {
        {'X', 'O', 'X'}, 
        {'_', 'O', '_'}, 
        {'X', '_', '_'}};
    
    private int boardSize = 3;

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
    }
}
