package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;
import Model.Exceptions.PathNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Piece {
    private int rank;
    private String imagePath;

    private boolean hasRevived;
    private boolean isBlue;

    private boolean isDead;

    private Coordinates coordinates;
    private boolean flipped = false;

    private ImageIcon pieceImage;
    private ImageIcon hiddenImage;
    private static final int buttonWidth = 105;
    private static final int buttonHeight = 95;

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


    Piece(int x, int y, int rank, String imagePath, boolean isBlue) throws PathNotFoundException{
        this.coordinates = new Coordinates(x,y);
        this.rank = rank;
        this.imagePath = imagePath;
        this.isBlue = isBlue;
        this.isDead = false;
        try {
            this.setPieceImage();
        } catch (PathNotFoundException e){
            throw e;
        }
        this.hasRevived=false;
    }

    /**
     * <b>Accessor:</b> Get X-coordinate
     * @return X-coordinate
     */
    public int getX(){
        return coordinates.getX();
    }

    /**
     * <b>Accessor:</b> Get Y-coordinate
     * @return Y-coordinate
     */
    public int getY(){
        return coordinates.getY();
    }

    /**
     * <b>Accessor:</b> Get coordinates
     * @return both coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * <b>Transformer:</b> Set X-coordinate
     * @param x
     */
    public void setX(int x){
        coordinates.setX(x);
    }

    /**
     * <b>Transformer:</b> Set Y-coordinate
     * @param y
     */
    public void setY(int y){
        coordinates.setY(y);
    }

    /**
     * <b>Transformer:</b> Set Both coordinates
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates=coordinates;
    }


    /**
     * <b>pre-condition</b> Rank was set in the constructor
     *
     * @return the rank of the piece (1-10)
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the path to the image of the Piece
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * <b>Transformer</b> gives an imagePath to the piece
     *
     * @param imagePath the imagePath for the view to manage
     */
    public void setImagePath(String imagePath){

        this.imagePath = imagePath;
    }

    /**
     * <b>Accessor:</b>
     * @return if the piece is alive or dead for the board to see if it is going to place it on the board or not
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * <b>Transformer</b> Changes isAlive state to false
     * Kill the piece
     */
    public void setDead() {
        isDead = true;
    }

    /**
     * <b>Accessor:</b>
     * @return if the piece is owned by the red or blue player
     */
    public boolean isBlue() {
        return isBlue;
    }

    /**
     * <b>Accessor:</b>
     * @return if the piece is flipped or not
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * <b>Accessor:</b>
     * @param flipped if the piece is flipped or not
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * <b>Transformer</b> Generates and stores an ImageIcon of the piece when the piece is flipped
     * @throws PathNotFoundException when the image path does not exist
     * @param path path of hidden image from source
     */
    public void setHiddenImage(String path)   throws PathNotFoundException {
        if (!Files.exists(Paths.get(imagePath))){
            throw  new PathNotFoundException();
        }
        Image img = new ImageIcon(path).getImage();
        img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        this.hiddenImage = new ImageIcon(img);
    }

    /**
     * <b>Accessor:</b>
     * <b>pre-condition</b> the hiddenImage of the piece is set
     * <b>post-condition</b> the hiddenImage of the piece is returned
     * @return the ImageIcon of the piece when it is flipped
     */
    public ImageIcon getHiddenImage() {
        return hiddenImage;
    }

    /**
     * <b>Transformer</b> Generates and stores an ImageIcon of the piece when the piece is shown
     * @throws PathNotFoundException when the image path does not exist
     *
     */
    public void setPieceImage()  throws PathNotFoundException {
        if (!Files.exists(Paths.get(imagePath))){
            throw  new PathNotFoundException();
        }
        Image img = new ImageIcon(imagePath).getImage();
        img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        this.pieceImage = new ImageIcon(img);
    }

    /**
     * <b>Accessor:</b>
     * <b>pre-condition</b> the Image of the piece is set
     * <b>post-condition</b> the Image of the piece is returned
     * @return the ImageIcon of the piece when it is shown
     */
    public ImageIcon getPieceImage() {
        return pieceImage;
    }


    /**
     * <b>Accessor:</b>
     *
     * @param board board on the player is stored
     * @param mode mode for
     * @return
     */
    public abstract List<Coordinates> getPossibleMoves(Board board,int mode);

    /**
     * <b>Accessor:</b>
     *
     * @return
     */
    public String toString(){
        return "Rank: "+this.rank;
    }

    /**
     * <b>Transformers:</b> Sets isDead to false because piece has been revived
     */
    public void isRevived(){
        isDead = true;
    }

    /**
     * <b>Accessor:</b>
     * @return if the piece has rescued another piece or not
     */
    public boolean hasRevived() {
        return hasRevived;
    }

    /**
     * <b>Transformers:</b> Sets true that this piece revived another piece
     */
    public void revivedSomebody(){
        hasRevived = true;
    }
}

