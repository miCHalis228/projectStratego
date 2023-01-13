package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;

import java.util.List;

public class ImmovablePiece extends Piece {

    private boolean isFlag;

    /**
     * <b>Constructor</b> Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     */
    public ImmovablePiece(int x, int y, String imagePath, boolean isBlue, boolean isFlag) {

        super(x, y, isFlag ? 0 : 11, imagePath, isBlue);
        this.isFlag = isFlag;
    }

    /**
     * <b>Accessor</b> Return if this ImmovablePiece is Flag or not(trap)
     *
     * @return if this piece is flag
     */
    public boolean isFlag() {
        return isFlag;
    }

    /**
     * <b>Accessor</b> null because immovable pieces cannot move
     * @param board board in which the moves will be made
     * @param mode mode to check if only forward
     * @return null
     */
    @Override
    public List<Coordinates> getPossibleMoves(Board board,int mode) {
        return null;
    }
}
