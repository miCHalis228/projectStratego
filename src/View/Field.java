package View;
import Model.Board.Board;
import Model.Exceptions.PathNotFoundException;
import javax.swing.*;
import java.awt.*;

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
        initField();//TODO THINK MF

    }

    public void initField(){
        setHiddenBlue();
        setHiddenRed();
    }

    public void setHiddenBlue() {
        hiddenBlue.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                hiddenBlue.add(f_board.getSpot(i,j).getButtonBlue());
            }
        hiddenBlue.setBounds(50,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);

        hiddenBlue.setOpaque(false);
    }

    public void setHiddenRed() {
        hiddenRed.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                hiddenRed.add(f_board.getSpot(i,j).getButtonRed());
            }
        hiddenRed.setBounds(50,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);
        hiddenRed.setOpaque(false);
    }

    public void swapFields(boolean turnBlue){
        if(!turnBlue){
            hiddenBlue.setVisible(true);
            hiddenRed.setVisible(false);
        } else {
            hiddenBlue.setVisible(false);
            hiddenRed.setVisible(true);
        }
    }

    public JPanel getHiddenBlue(){
        return this.hiddenBlue;
    }
    public JPanel getHiddenRed(){
        return this.hiddenRed;
    }
    public void setFieldMode(int mode){

    }

    /**
     * <b>Transformer</b> Updates the gridButtons based on the updated Board reference
     * <b>pre-condition</b> Field is already drawn
     * <b>post-condition</b> Field is updated
     *
     * #@param newBoard A reference to the updated board is sent from the View
     */
    public void updateField(){//Board newBoard) {
        Thread T = new Thread_extended_class(this);
        T.start();
        try {
            T.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>Transformer</b> Changes this instance of Field, adding all the components and also setting the images in the buttons
     * @throws PathNotFoundException when trying to access an Image whose path is given wrong
     */
    public void drawBoard(JFrame frame) throws PathNotFoundException {
        frame.setVisible(true);
    }
    class Thread_extended_class extends Thread {
        Field frame;

        Thread_extended_class(Field frame) {
            super();
            this.frame = frame;
        }
        @Override
        public void run() {
            try {
                do {
                    frame.updateField();
                    Thread.sleep(500);
                } while (true);
            } catch (Exception x) {
            }
        }

    } // end class
}
