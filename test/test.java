import Model.Coordinates.Coordinates;
import Model.Exceptions.DeadPieceException;
import Model.Exceptions.InvalidCoordinatesException;
import Model.Exceptions.PathNotFoundException;
import Model.Pieces.MovablePiece;
import Model.Player.Player;
import org.junit.jupiter.api.Test;

public class test {
    MovablePiece piece1, piece2;

    @Test
    void imagePath() {
        try {
            piece1 = new MovablePiece(-1, -1, "FileDoesNotExist", 4, true);
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    void invalidCoordinates() {
        //Give correct imagePath for these as this is not the testing point
        try {
            piece1 = new MovablePiece(-1, -1, "src\\images\\blue_checked.jpg", 4, true);
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
        try {
            piece1.move(new Coordinates(-1,-1));
        } catch (InvalidCoordinatesException e) {
            System.out.println(e);
        }
    }

    @Test
    void attacksDead() {
        //Give correct imagePath for these as this is not the testing point
        try {
            piece1 = new MovablePiece(-1, -1, "src\\images\\blue_checked.jpg", 4, true);
            piece2 = new MovablePiece(-1, -1, "src\\images\\blue_checked.jpg", 4, false);
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
        piece2.setDead();
        try {
            piece1.attack(piece2);
        } catch (DeadPieceException e) {
            System.out.println(e);
        }
    }

    @Test
    void deadAttacks() {
        //Give correct imagePath for these as this is not the testing point
        try {
            piece1 = new MovablePiece(-1, -1, "src\\images\\blue_checked.jpg", 4, true);
            piece2 = new MovablePiece(-1, -1, "src\\images\\blue_checked.jpg", 4, false);
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
        piece1.setDead();
        try {
            piece1.attack(piece2);
        } catch (DeadPieceException e) {
            System.out.println(e);
        }
    }

    Player player;
    @Test
    void playerName() {
        try {
            player = new Player("WrongName");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
