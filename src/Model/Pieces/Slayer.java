package Model.Pieces;

import Model.Exceptions.DeadPieceException;
import Model.Exceptions.PathNotFoundException;

public class Slayer extends SpecialMovablePiece {
    /**
     * Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param rank      rank/power
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     * @throws PathNotFoundException when the image path does not exist
     */
    public Slayer(int x, int y,  String imagePath, int rank, boolean isBlue) throws PathNotFoundException {
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
        if(Enemy instanceof MovablePiece ){
            if(Enemy.getRank()==10){
                Enemy.setDead();
            }else if(this.getRank()> Enemy.getRank()){
                this.setCoordinates(Enemy.getCoordinates());
                Enemy.setDead();
            } else if(this.getRank() == Enemy.getRank()){
                Enemy.setDead();
                this.setDead();
            } else {
                this.setDead();
            }
        } else {
            if(((ImmovablePiece)Enemy).isFlag()){
                Enemy.setDead();
            } else {
                this.setDead();
            }
        }
    }
}
