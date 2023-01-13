package Model.Exceptions;

/**
 * @author Michalis Ierodiakonou
 *
 * An exception thrown when you try to access an Image which doesn't exist
 */
public class PathNotFoundException extends Exception {
    public PathNotFoundException() {
        super("Image URL not found");
    }
}
