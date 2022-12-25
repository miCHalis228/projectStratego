package Model.Spot;
import Model.Pieces.Piece;
import javax.swing.*;
import java.awt.*;

public class Spot {

    private Piece s_Piece;
    private boolean isLake;
    private final JButton buttonRed = new JButton();
    private final JButton buttonBlue = new JButton();

    private static final int buttonWidth = 105;
    private static final int buttonHeight = 95;

    public Spot(Piece piece) {
        this.s_Piece = piece;
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
     * @param piece Spot's piece
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


    public boolean isEmpty(){
        return this.s_Piece == null;
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
            this.buttonBlue.setIcon(s_Piece.getPieceImage());
            this.buttonBlue.setSize(buttonWidth, buttonHeight);
            this.buttonBlue.setBorder(BorderFactory.createBevelBorder(1));
            setHiddenButton(this.buttonRed);
        } else {
            this.buttonRed.setIcon(s_Piece.getPieceImage());
            this.buttonRed.setSize(buttonWidth, buttonHeight);
            this.buttonRed.setBorder(BorderFactory.createBevelBorder(1));
            setHiddenButton(this.buttonBlue);
        }
    }

    private void setLakeButton() {
        /*Red*/
        this.buttonRed.setBackground(Color.ORANGE);
        this.buttonRed.setSize(buttonWidth, buttonHeight);
        this.buttonRed.setEnabled(false);
        /*Blue*/
        this.buttonBlue.setBackground(Color.ORANGE);
        this.buttonBlue.setSize(buttonWidth, buttonHeight);
        this.buttonBlue.setEnabled(false);
    }

    private void setNullButton() {
        /*Red*/
        this.buttonRed.setIcon(null);
        this.buttonRed.setSize(buttonWidth, buttonHeight);
        this.buttonRed.setContentAreaFilled(false);
        this.buttonRed.setBorder(BorderFactory.createBevelBorder(1));
        /*Blue*/
        this.buttonBlue.setIcon(null);
        this.buttonBlue.setSize(buttonWidth, buttonHeight);
        this.buttonBlue.setContentAreaFilled(false);
        this.buttonBlue.setBorder(BorderFactory.createBevelBorder(1));
    }
    private void setHiddenButton(JButton button){
        button.setSize(buttonWidth, buttonHeight);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(1));
        button.setIcon(s_Piece.getHiddenImage());
    }

    public JButton getButtonBlue() {
        return buttonBlue;
    }

    public JButton getButtonRed() {
        return buttonRed;
    }

}
