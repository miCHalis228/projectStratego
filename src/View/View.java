package View;

import Controller.Controller;
import Model.Board.Board;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * @author Mihalis Ierodiakonou
 * View is A class called by the controller combining the Stats Panel with the Field/Grid Panel
 * This is the main class from which everything is drawn and actions from the user are processed
 */
public class View extends JFrame {
    private Player playerBlue;
    private Player playerRed;
    private JPanel hiddenBlue;
    private JPanel hiddenRed;
    private Field field;
    private Stats statsBlue;
    private Stats statsRed;


    /**
     * <b>Constructor</b> Constructs a View and Starts the game
     * <b>post-condition</b> The Game has started
     */
    public View(Player pblue, Player pred,Board board,int mode) {
        playerBlue = pblue;
        playerRed = pred;
        field = new Field(board);
        statsBlue = new Stats(playerBlue,mode);
        statsRed = new Stats(playerRed,mode);

        initView();

        hiddenBlue = field.getHiddenBlue();
        hiddenRed = field.getHiddenRed();
        this.add(hiddenRed);
        this.add(hiddenBlue);
        statsBlue.addComponents(this);
        statsRed.addComponents(this);
        this.pack();

        statsRed.hideAll();
        statsBlue.showAll();
    }

    public void initView(){
        this.setLayout(new CardLayout());
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\dragon_background_cropped169.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().width,(int)Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image), JLabel.CENTER);
        this.setContentPane(background);
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setResizable(false);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * <b>Transformer</b> This is called each Turn
     * <b>pre-condition</b> View is already drawn
     * <b>post-condition</b> View is updated
     *
     * @param turnRed A reference to the updated board is sent from the controller
     * @param round A reference to the updated board is sent from the controller
     */
    public void updateView(boolean turnRed,int round) {
        field.swapFields(turnRed);
        if(!turnRed){
            playerBlue.unflipCards();
            playerRed.flipCards();
            statsRed.showAll();
            statsBlue.hideAll();
        } else {
            playerBlue.flipCards();
            playerRed.unflipCards();
            statsRed.hideAll();
            statsBlue.showAll();
        }
        statsBlue.nextTurn(round);
        statsRed.nextTurn(round);
        //TODO
    }

    public void updateStats(){
        statsBlue.update();
        statsRed.update();
    }

    class Thread_extended_class extends Thread {
        ModSelectionWindow mon;

        Thread_extended_class(ModSelectionWindow mon) {
            super();
            this.mon = mon;
        }
        @Override
        public void run() {
            try {
                mon.init();
                do {
                    Thread.sleep(100);
                    if (mon.getMode() != -1) {
                        break;
                    }
                } while (true);
            } catch (Exception x) {
            }
        }

    } // end class


}
