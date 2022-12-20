package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;

import java.util.List;

public class Scout extends SpecialMovablePiece {
    /**
     * Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param rank      rank/power
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     */
    Scout(int x, int y, int rank, String imagePath, boolean isBlue) {
        super(x, y, imagePath, rank, isBlue);
    }

    /**
     * It has greater rang of movement.
     *
     * @return
     */
    @Override
    public List<Coordinates> getPossibleMoves(Board board) {
        return super.getPossibleMoves(board);
    }
}
