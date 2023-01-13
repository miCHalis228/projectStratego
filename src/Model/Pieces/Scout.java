package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;
import Model.Spot.Spot;

import java.util.ArrayList;
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
    public Scout(int x, int y, String imagePath, int rank, boolean isBlue) {
        super(x, y, imagePath, rank, isBlue);
    }


    /**
     * <b>Accessor</b> Calculates possible moves are returned as Coordinate objects which store (x,y) values
     * The scout can move in lines not just a square each time
     * @param board board in which the moves will be made
     * @param mode mode to check if only forward
     * @return an Array List of possible moves (Coordinates)
     */
    @Override
    public List<Coordinates> getPossibleMoves(Board board , int mode) {
        List<Coordinates> possibleCoordinates = new ArrayList<>();
        //Check for the for valid spots if are available for movement
        int x= this.getX();
        int y= this.getY();
        int i=1;
        Spot tempSpot;
        Coordinates coordinates;
        OnlyForward:{
            if(mode==2 || mode==3){
                if(this.isBlue()){
                    //LINE DOWN
                    coordinates = new Coordinates(x,y+i);
                    while(coordinates.isValid()){
                        tempSpot = board.getSpot(y+i,x);
                        if(!tempSpot.isLake()){
                            if(tempSpot.isEmpty()) {
                                possibleCoordinates.add(coordinates);
                            } else if (tempSpot.getPiece().isBlue() != isBlue()){
                                possibleCoordinates.add(coordinates);
                                break;
                            } else if(tempSpot.getPiece().isBlue() == isBlue()){
                                break;
                            }
                        }
                        i++;
                        coordinates = new Coordinates(x,y+i);
                    }
                } else {
                    //LINE UP
                    coordinates = new Coordinates(x,y-i);
                    while(coordinates.isValid()){
                        tempSpot = board.getSpot(y-i,x);
                        if(!tempSpot.isLake()){
                            if(tempSpot.isEmpty()) {
                                possibleCoordinates.add(coordinates);
                            } else if (tempSpot.getPiece().isBlue() != isBlue()){
                                possibleCoordinates.add(coordinates);
                                break;
                            } else if(tempSpot.getPiece().isBlue() == isBlue()){
                                break;
                            }
                        }
                        i++;
                        coordinates = new Coordinates(x,y-i);
                    }
                }
                i=1;
                coordinates = new Coordinates(x-i,y);
                while(coordinates.isValid()){
                    tempSpot = board.getSpot(y,x-i);
                    if(!tempSpot.isLake()){
                        if(tempSpot.isEmpty()) {
                            possibleCoordinates.add(coordinates);
                        } else if (tempSpot.getPiece().isBlue() != isBlue()){
                            possibleCoordinates.add(coordinates);
                            break;
                        } else if(tempSpot.getPiece().isBlue() == isBlue()){
                            break;
                        }
                    }
                    i++;
                    coordinates = new Coordinates(x-i,y);
                }
                i=1;
                //LINE RIGHT
                coordinates = new Coordinates(x+i,y);
                while(coordinates.isValid()){
                    tempSpot = board.getSpot(y,x+i);
                    if(!tempSpot.isLake()){
                        if(tempSpot.isEmpty()) {
                            possibleCoordinates.add(coordinates);
                        } else if (tempSpot.getPiece().isBlue() != isBlue()){
                            possibleCoordinates.add(coordinates);
                            break;
                        } else if(tempSpot.getPiece().isBlue() == isBlue()){
                            break;
                        }
                    }
                    i++;
                    coordinates = new Coordinates(x+i,y);
                }
                return possibleCoordinates;
            }
        }
        NoMods:{
            //LINE LEFT
            coordinates = new Coordinates(x-i,y);
            while(coordinates.isValid()){
                tempSpot = board.getSpot(y,x-i);
                if(!tempSpot.isLake()){
                    if(tempSpot.isEmpty()) {
                        possibleCoordinates.add(coordinates);
                    } else if (tempSpot.getPiece().isBlue() != isBlue()){
                        possibleCoordinates.add(coordinates);
                        break;
                    } else if(tempSpot.getPiece().isBlue() == isBlue()){
                        break;
                    }
                }
                i++;
                coordinates = new Coordinates(x-i,y);
            }
            i=1;
            //LINE RIGHT
            coordinates = new Coordinates(x+i,y);
            while(coordinates.isValid()){
                tempSpot = board.getSpot(y,x+i);
                if(!tempSpot.isLake()){
                    if(tempSpot.isEmpty()) {
                        possibleCoordinates.add(coordinates);
                    } else if (tempSpot.getPiece().isBlue() != isBlue()){
                        possibleCoordinates.add(coordinates);
                        break;
                    } else if(tempSpot.getPiece().isBlue() == isBlue()){
                        break;
                    }
                }
                i++;
                coordinates = new Coordinates(x+i,y);
            }
            //LINE DOWN
            i=1;

            coordinates = new Coordinates(x,y+i);
            while(coordinates.isValid()){
                tempSpot = board.getSpot(y+i,x);
                if(!tempSpot.isLake()){
                    if(tempSpot.isEmpty()) {
                        possibleCoordinates.add(coordinates);
                    } else if (tempSpot.getPiece().isBlue() != isBlue()){
                        possibleCoordinates.add(coordinates);
                        break;
                    } else if(tempSpot.getPiece().isBlue() == isBlue()){
                        break;
                    }
                }
                i++;
                coordinates = new Coordinates(x,y+i);
            }
            //LINE UP
            i=1;

            coordinates = new Coordinates(x,y-i);
            while(coordinates.isValid()){
                tempSpot = board.getSpot(y-i,x);
                if(!tempSpot.isLake()){
                    if(tempSpot.isEmpty()) {
                        possibleCoordinates.add(coordinates);
                    } else if (tempSpot.getPiece().isBlue() != isBlue()){
                        possibleCoordinates.add(coordinates);
                        break;
                    } else if(tempSpot.getPiece().isBlue() == isBlue()){
                        break;
                    }
                }
                i++;
                coordinates = new Coordinates(x,y-i);
            }
            return possibleCoordinates;
        }
    }

}
