/** This RuntimeException is used when trying to access a position on the board which does not exist. */
public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(String msg) {
        super(msg);
    }
}
