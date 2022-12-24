package Model.Spot;

import Model.Board.Board;
import Model.Coordinates.Coordinates;
import Model.Pieces.Piece;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spot {

    private Piece s_Piece;
    private boolean isLake;
    private boolean isPressed;
    private JButton buttonRed;
    private JButton buttonBlue;

    private JButton hiddenButton;
    private ImageIcon hiddenImage;
    private ImageIcon pieceImage;
    private static final int buttonWidth = 105;
    private static final int buttonHeight = 95;

    public Spot(Piece piece) {
        this.s_Piece = piece;
        isPressed=false;
//        button = new JButton();
        hiddenButton = new JButton();

        buttonBlue = new JButton();
        buttonRed = new JButton();
    }

    /**
     * <b>Accessor</b> Returns the Piece in this Spot
     * @return Piece of this Spot
     */
    public Piece getPiece() {
        return s_Piece;
    }

    /**
     * <b>Transformer</b> Sets the Piece of the Spot
     * <b>post-condition</b> Piece is Set as either an object or NULL in case of no piece on this Spot
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.s_Piece = piece;
        this.setButton();
    }

    /**
     * <b>Accessor</b> Returns if this Spot is lake or not
     * @return true if it s a lake, so it is inaccessible for pieces
     */
    public boolean isLake() {
        return isLake;
    }

    /**
     * <b>Transformer</b> Sets this Spot as Lake
     * @param isLake if it is any spot other than the lakes
     */
    public void setLake(boolean isLake) {
        this.isLake = isLake;
    }



//    public class selectedPawn implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JButton source = (JButton) e.getSource();
//            isPressed=!isPressed;
//            if(isPressed){
//                source.setBorder(BorderFactory.createBevelBorder(1));
//            } else{
//                source.setBorder(null);
//            }
//        }

    public boolean isEmpty(){
        if(this.s_Piece == null){
            return true;
        }
        return false;
    }

    public void setButton() {
        if (isLake()) {
            setLakeButton();
        } else if (s_Piece == null) {
            setNullButton();
        } else {
            setPieceButton();
        }
    }

    private void setPieceButton() {
        if(this.s_Piece.isBlue()){
            Image img = new ImageIcon(s_Piece.getImagePath()).getImage();
            img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            pieceImage= new ImageIcon(img);
            this.buttonBlue.setIcon(pieceImage);
            this.buttonBlue.setSize(buttonWidth, buttonHeight);
            this.buttonBlue.setBorder(BorderFactory.createBevelBorder(1));
            setHiddenButton(this.buttonRed);
        } else {
            Image img = new ImageIcon(s_Piece.getImagePath()).getImage();
            img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            pieceImage= new ImageIcon(img);
            this.buttonRed.setIcon(pieceImage);
            this.buttonRed.setSize(buttonWidth, buttonHeight);
            this.buttonRed.setBorder(BorderFactory.createBevelBorder(1));
            setHiddenButton(this.buttonBlue);
        }

//        this.button.setIcon(pieceImage);
//        this.button.setSize(buttonWidth, buttonHeight);
//        this.button.setBorder(BorderFactory.createBevelBorder(1));
//        if(buttonSpots[row][col].getActionListeners()==null)
    }

    private void setLakeButton() {
        this.buttonRed.setBackground(Color.ORANGE);
        this.buttonRed.setSize(buttonWidth, buttonHeight);
        this.buttonRed.setEnabled(false);
        this.buttonBlue.setBackground(Color.ORANGE);
        this.buttonBlue.setSize(buttonWidth, buttonHeight);
        this.buttonBlue.setEnabled(false);
    }

    private void setNullButton() {
        this.buttonRed.setIcon(null);
        this.buttonRed.setSize(buttonWidth, buttonHeight);
        this.buttonRed.setContentAreaFilled(false);
        this.buttonRed.setBorder(BorderFactory.createBevelBorder(1));
        this.buttonBlue.setIcon(null);
        this.buttonBlue.setSize(buttonWidth, buttonHeight);
        this.buttonBlue.setContentAreaFilled(false);
        this.buttonBlue.setBorder(BorderFactory.createBevelBorder(1));
    }

    public void setHiddenImage(ImageIcon img) {
//        Image img2 = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\RedPieces\\Hidden.png").getImage();
//        img2 = img2.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
//        pieceImage= new ImageIcon(img2);

        this.hiddenImage=((img));


    }

    private void setHiddenButton(JButton button){
        button.setSize(buttonWidth, buttonHeight);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(1));
        button.setIcon(hiddenImage);
    }


    public JButton getHiddenButton() {
        return this.hiddenButton;
    }

//    public JButton getButton() {
//        return this.button;
//    }


    public JButton getButtonBlue() {
        return buttonBlue;
    }

    public JButton getButtonRed() {
        return buttonRed;
    }

}
