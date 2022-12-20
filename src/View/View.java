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
    private Stats[] statScreen;
    private Field fieldScreen;

    private Controller controller;

    private Player p1;
    private Player p2;

    private ArrayList<JButton> graveyard;
    private int mode = -1;
    private ModSelectionWindow msw;

    private JFrame gameView;
    private LoadingScreen loading;

    /**
     * <b>Constructor</b> Constructs a View and Starts the game
     * <b>post-condition</b> The Game has started
     */
    public View(Board board) {
        msw = new ModSelectionWindow();
        loading = new LoadingScreen();
        loading.setVisible(true);
        Thread T = new Thread_extended_class(msw);
        T.start();
        try {
            T.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mode = msw.getMode();

        controller = new Controller(mode);
        for(int i=0;i<6;i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            loading.count();
            loading.setVisible(true);
        }
        loading.setVisible(false);
        loading.dispose();

//        fieldScreen = new Field(controller.getBoard());

//        JFrame frame = new JFrame();
//        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\blue_red_wallpaper.jpg"); // load the image to a imageIcon
//        JLabel background = new JLabel(imageIcon, JLabel.CENTER);
//        frame.setContentPane(background);
//        frame.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
//        frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
//        frame.setResizable(false);
//        frame.setUndecorated(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(fieldScreen);
//        frame.pack();
//        frame.setVisible(true);

//        System.exit(0);
//        //one for each player
//        statScreen = new Stats[2];
//        p1=controller.getPlayerBlue();
//        p2=controller.getPlayerRed();
    }

    public static void main(String[] args) {
        View v = new View(null);
    }

    /**
     * <b>Transformer</b> This is called each Turn
     * <b>pre-condition</b> View is already drawn
     * <b>post-condition</b> View is updated
     *
     * @param newBoard A reference to the updated board is sent from the controller
     */
    public void updateView(Board newBoard) {
        //TODO
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
