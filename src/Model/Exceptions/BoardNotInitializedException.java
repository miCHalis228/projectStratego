package Model.Exceptions;

/**
 * @author Michalis Ierodiakonou
 *
 * An exception thrown when you try to manipulate a not initialised board
 */
public class BoardNotInitializedException extends Exception {
    public BoardNotInitializedException() {
        super("Couldn't Initialize Board");
    }
}
