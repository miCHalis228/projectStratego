package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;
import Model.Exceptions.DeadPieceException;
import Model.Exceptions.InvalidCoordinatesException;
import Model.Spot.Spot;

import java.util.ArrayList;
import java.util.List;

public class MovablePiece extends Piece {
    private boolean wasRevived = false;

    /**
     * Constructor of piece, initializing it alive (isDead=false)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param rank      rank/power
     * @param imagePath path in the src where to get the image from
     * @param isBlue    if it is in the Blue or Red team
     */
    public MovablePiece(int x, int y, String imagePath, int rank, boolean isBlue) {
        super(x, y, rank, imagePath, isBlue);
    }

    /**
     * Possible moves are returned as Coordinate objects which store (x,y) values
     *
     * @return an Integer array of possible moves
     */
    public List<Coordinates> getPossibleMoves(Board board){
        List<Coordinates> possibleCoordinates = new ArrayList<>();
        //Check for the for valid spots if are available for movement
        int x= this.getX();
        int y= this.getY();
        Spot tempSpot;

        //ONE LEFT
        Coordinates coordinates;
        coordinates = new Coordinates(x-1,y);
        if(coordinates.isValid()){
            tempSpot = board.getSpot(y,x-1);
            if(!tempSpot.isLake()){
                if(tempSpot.isEmpty()) {
                    possibleCoordinates.add(coordinates);
                } else if (tempSpot.getPiece().isBlue() != isBlue()){
                    possibleCoordinates.add(coordinates);
                }
            }
        }
//        //ONE RIGHT
        coordinates = new Coordinates(x+1,y);
        if(coordinates.isValid()){
            tempSpot = board.getSpot(y,x+1);
            if(!tempSpot.isLake()){
                if(tempSpot.isEmpty()) {
                    possibleCoordinates.add(coordinates);
                } else if (tempSpot.getPiece().isBlue() != isBlue()){
                    possibleCoordinates.add(coordinates);
                }
            }
        }
        //ONE DOWN

        coordinates = new Coordinates(x,y+1);
        if(coordinates.isValid()){
            tempSpot = board.getSpot(y+1,x);
            if(!tempSpot.isLake()){
                if(tempSpot.isEmpty()) {
                    possibleCoordinates.add(coordinates);
                } else if (tempSpot.getPiece().isBlue() != isBlue()){
                    possibleCoordinates.add(coordinates);
                }
            }
        }
        //ONE UP

        coordinates = new Coordinates(x,y-1);
        if(coordinates.isValid()){
            tempSpot = board.getSpot(y-1,x);
            if(!tempSpot.isLake()){
                if(tempSpot.isEmpty()) {
                    possibleCoordinates.add(coordinates);
                } else if (tempSpot.getPiece().isBlue() != isBlue()){
                    possibleCoordinates.add(coordinates);
                }
            }
        }
        return possibleCoordinates;
    }

    /**
     * <b>pre-conditions</b> coordinates are already set, here it just changes them
     * <b>post-condition</b> new coordinates are set and are valid from the caller, no checking required
     *
     * @param newPos
     * @throws InvalidCoordinatesException when the Coordinates sent are invalid
     */
    public void move(Coordinates newPos) throws InvalidCoordinatesException {
        this.setX(newPos.getX());
        this.setY(newPos.getY());
    }

    /**
     * method to simulate attack
     * <b>pre-condition</b> this Piece is not Dead
     * <b>post-condition</b> this OR enemy OR both Pieces are defeated
     *
     * @param Enemy an enemy Piece sent by the controller
     * @throws DeadPieceException if this piece trying to attack or is attacked is dead
     */
    public void attack(Piece Enemy) throws DeadPieceException{
         if(Enemy instanceof MovablePiece ){
            if(this.getRank()> Enemy.getRank()){
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

    /**
     * @return if this piece was revived before
     */
    public boolean WasRevived() {
        return wasRevived;
    }

    /**
     * Set true when this piece was revived ,so it won't be again
     */
    public void setRevived() {
        this.wasRevived = true;
    }
}
