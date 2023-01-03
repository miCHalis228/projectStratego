package Model.Exceptions;

public class BoardNotInitializedException extends Exception {
    public BoardNotInitializedException() {
        super("Couldn't Initialize Board");
    }
}
