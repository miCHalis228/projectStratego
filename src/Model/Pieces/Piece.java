package Model.Pieces;

import Model.Board.Board;
import Model.Coordinates.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Piece {
    private int rank;
    private String imagePath;
    private String hiddenImagePath;

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
     */

    Piece(int x, int y, int rank, String imagePath, boolean isBlue) {
        this.coordinates = new Coordinates(x,y);
        this.rank = rank;
        this.imagePath = imagePath;
        this.isBlue = isBlue;
        this.isDead = false;
        this.setPieceImage();

    }

    public int getX(){
        return coordinates.getX();
    }

    public int getY(){
        return coordinates.getY();
    }

    public void setX(int x){
        coordinates.setX(x);
    }

    public void setY(int y){
        coordinates.setY(y);
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates=coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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
     * @param imagePath return the imagePath for the view to manage
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return if the piece is alive or dead for the board to see if it is going to place it on the board or not
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * <b>Transformer</b> Changes isAlive state
     * Kill the piece
     */
    public void setDead() {
        isDead = true;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void setHiddenImage(String path) {
        Image img = new ImageIcon(path).getImage();
        img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        this.hiddenImage = new ImageIcon(img);
    }

    public ImageIcon getHiddenImage() {
        return hiddenImage;
    }

    public void setPieceImage() {
        Image img = new ImageIcon(imagePath).getImage();
        img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        this.pieceImage = new ImageIcon(img);
    }

    public ImageIcon getPieceImage() {
        return pieceImage;
    }



    public abstract List<Coordinates> getPossibleMoves(Board board);
}
