package Model.Exceptions;

/**
 * @author Michalis Ierodiakonou
 *
 * An exception thrown when you try to move/attack a dead piece
 */
public class DeadPieceException extends Exception {
    public DeadPieceException() {
        super("Piece is dead");
    }
}
