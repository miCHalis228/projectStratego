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
    private JButton button;
    private static final int buttonWidth = 105;
    private static final int buttonHeight = 95;

    public Spot(Piece piece) {
        this.s_Piece = piece;
        isPressed=false;
        button = new JButton();

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
        Image img = new ImageIcon(s_Piece.getImagePath()).getImage();
        img = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setSize(buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createBevelBorder(1));
//        if(buttonSpots[row][col].getActionListeners()==null)
    }

    private void setLakeButton() {
        button.setBackground(Color.ORANGE);
        button.setSize(buttonWidth, buttonHeight);
        button.setEnabled(false);
    }



    private void setNullButton() {
        button.setIcon(null);
        button.setSize(buttonWidth, buttonHeight);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(1));

    }

    public JButton getButton() {
        return button;
    }


}
