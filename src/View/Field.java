package View;
import Model.Board.Board;
import javax.swing.*;
import java.awt.*;

/**
 * @author Michalis Ierodiakonou
 * A
 */
public class Field{
    private JPanel hiddenBlue;
    private JPanel hiddenRed;

    private Board f_board;

    /**
     * <b>Constructor</b> Constructs an object of Field to be used by the view
     * Creates an initial board by referencing the given one
     *
     * @param board
     */
    public Field(Board board) {
        this.f_board=board;
        hiddenBlue = new JPanel();
        hiddenRed = new JPanel();
        initField();

    }

    /**
     * A method to reduce code
     *
     */
    public void initField(){
        setHiddenBlue();
        setHiddenRed();
    }

    /**
     * <b>Transformer:</b> Sets blue panel, panel red player is seeing
     * with the (blue)buttons (from the spot) in a grid layout
     */
    private void setHiddenBlue() {
        hiddenBlue.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                hiddenBlue.add(f_board.getSpot(i,j).getButtonBlue());
            }
        hiddenBlue.setBounds(50,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);

        hiddenBlue.setOpaque(false);
    }
    /**
     * <b>Transformer:</b> Sets red panel, panel blue player is seeing
     * with the (red)buttons (from the spot) in a grid layout
     */
    private void setHiddenRed() {
        hiddenRed.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                hiddenRed.add(f_board.getSpot(i,j).getButtonRed());
            }
        hiddenRed.setBounds(50,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);
        hiddenRed.setOpaque(false);
    }

    /**
     * <b>Transformer:</b> Changes which players' panel is visible
     * @param turnBlue depending on the turn switches either panel on or off and the other off or on
     */
    public void swapFields(boolean turnBlue){
        if(!turnBlue){
            hiddenBlue.setVisible(true);
            hiddenRed.setVisible(false);
        } else {
            hiddenBlue.setVisible(false);
            hiddenRed.setVisible(true);
        }
    }

    /**
     * <b>Accessor:</b> Returns the panel red player is seeing
     * @return blue player's panel
     */
    public JPanel getHiddenBlue(){
        return this.hiddenBlue;
    }

    /**
     * <b>Accessor:</b> Returns the panel blue player is seeing
     * @return red player's panel
     */
    public JPanel getHiddenRed(){
        return this.hiddenRed;
    }

}
