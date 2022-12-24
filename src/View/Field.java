package View;

import Model.Board.Board;
import Model.Exceptions.BoardNotInitializedException;
import Model.Exceptions.PathNotFoundException;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

public class Field{

    private JPanel hiddenBlue;
    private JPanel hiddenRed;

    private JButton[][] gridButtons;
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

//                if(!f_board.getSpot(i,j).isEmpty()){
//                    if(f_board.getSpot(i,j).getPiece().isBlue()){
//                        hiddenBlue.add(f_board.getSpot(i,j).getHiddenButton());
//                    } else{
//                        hiddenBlue.add(f_board.getSpot(i,j).getButton());
//                    }
//                } else {
//                    System.out.println("hidden blue called empty");
//                    hiddenBlue.add(f_board.getSpot(i,j).getButton());
//                    System.out.println(f_board.getSpot(i,j).getButton().hashCode());
//                }
            }
        hiddenBlue.setBounds(100,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);
        hiddenBlue.setOpaque(false);
    }

    public void setHiddenRed() {
        hiddenRed.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                hiddenRed.add(f_board.getSpot(i,j).getButtonRed());

//                if(!f_board.getSpot(i,j).isEmpty()){
//                    if(!f_board.getSpot(i,j).getPiece().isBlue()){
//                        hiddenRed.add(f_board.getSpot(i,j).getHiddenButton());
//                    } else{
//                        hiddenRed.add(f_board.getSpot(i,j).getButton());
//                    }
//                } else {
//                    hiddenRed.add(f_board.getSpot(i,j).getButton());
//                    System.out.println(f_board.getSpot(i,j).getButton().hashCode());
//
//                }
            }
        hiddenRed.setBounds(100,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);
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

    public static void main(String[] args) {
        Field f = new Field(new Board());

        JFrame frame = new JFrame();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\dragon_background_cropped169.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().width,(int)Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image), JLabel.CENTER);
        frame.setContentPane(background);
        frame.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(f.getHiddenBlue());
        frame.add(f.getHiddenRed());
        f.swapFields(false);
        frame.pack();
        frame.setVisible(true);
//        f.updateField();

//        System.exit(0);
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
