package View;

import Model.Board.Board;
import Model.Exceptions.BoardNotInitializedException;
import Model.Exceptions.PathNotFoundException;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

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
        initField();//TODO THINK MF



        this.setLayout(new GridLayout(8,10 , 5 ,5 ));

        for(int i=0;i<8;i++)
            for(int j=0;j<10;j++){
                this.add(board.getSpot(i,j).getButton());
            }
        this.setBounds(100,(Toolkit.getDefaultToolkit().getScreenSize().height-800)/2,10*105+40,8*95+40);
        this.setOpaque(false);
    }

    public void initField(){

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
        frame.add(f);
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
                    System.out.println("hello");
                    Thread.sleep(500);
                } while (true);
            } catch (Exception x) {
            }
        }

    } // end class
}
