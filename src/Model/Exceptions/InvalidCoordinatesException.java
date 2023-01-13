package Model.Exceptions;

/**
 * @author Michalis Ierodiakonou
 * An exception thrown when you try to set Coordinates which are Invalid
 */
public class InvalidCoordinatesException extends Exception {
    /**
     * A custom Exception thrown when the cooridnates given are not in the board
     */
    public InvalidCoordinatesException() {
        super("Invalid Coordinates");
    }
}
