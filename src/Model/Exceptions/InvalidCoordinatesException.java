package Model.Exceptions;

/**
 * @author Michalis Ierodiakonou
 * <p>
 * An exception thrown when you try to set Coordinates which are Invalid
 */
public class InvalidCoordinatesException extends Exception {
    public InvalidCoordinatesException() {
        super("Invalid Coordinates");
    }
}
