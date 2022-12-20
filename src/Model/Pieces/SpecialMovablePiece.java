package Model.Pieces;

import Model.Exceptions.DeadPieceException;

public class SpecialMovablePiece extends MovablePiece {
    /**
     * Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param rank      rank/power
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     */
    public SpecialMovablePiece(int x, int y, String imagePath, int rank, boolean isBlue) {
        super(x, y, imagePath, rank, isBlue);
    }
}
