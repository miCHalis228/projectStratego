package Model.Pieces;

import Model.Exceptions.DeadPieceException;

public class Slayer extends SpecialMovablePiece {
    /**
     * Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param rank      rank/power
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     */
    Slayer(int x, int y, int rank, String imagePath, boolean isBlue) {
        super(x, y, imagePath,rank, isBlue);
    }

    /**
     * Overrides MovablePiece attack as it has a different attack pattern
     *
     * @param Enemy an enemy Piece sent by the controller
     * @throws DeadPieceException if this piece is dead
     */
    @Override
    public void attack(Piece Enemy) throws DeadPieceException {
        super.attack(Enemy);
    }
}
