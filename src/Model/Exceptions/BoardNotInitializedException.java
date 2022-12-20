package Model.Exceptions;

public class BoardNotInitializedException extends Exception {
    BoardNotInitializedException() {
        super("Couldn't Initialize Board");
    }
}
