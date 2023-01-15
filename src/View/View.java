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

    /**
     * <b>Transformer:</b> Initializes the View Frame and adds the background
     */
    public void initView(){
        this.setLayout(new CardLayout());
        ImageIcon imageIcon = new ImageIcon("src\\images\\dragon_background_cropped169.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image), JLabel.CENTER);
        this.setContentPane(background);
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setResizable(false);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(closeButton());
    }

    /**
     * <b>Transformer</b> This is called each Turn
     * <b>pre-condition</b> View is already drawn
     * <b>post-condition</b> View is updated
     *
     * @param turnRed A reference to which players' turn it is, is sent from the controller
     * @param round A reference to the current round is sent from the controller
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
    }

    /**
     * <b>Transformer:</b> Adds a close/ 'X' button for exiting the game
     * @return the panel with the close button
     */
    public JPanel closeButton(){
        JPanel panel = new JPanel();
        JButton exit = new JButton();
        Image img = new ImageIcon("src\\images\\redX.png.jpg").getImage();
        img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        exit.setIcon(new ImageIcon(img));
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - 50,10,30,40);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.DARK_GRAY);
        exit.setOpaque(false);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        exit.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        exit.addActionListener(e -> System.exit(0));
        panel.add(exit);
        return panel;
    }

    /**
     * <b>Transformer:</b> Updates the statsBlue/Red panels for each player
     */
    public void updateStats(){
        statsBlue.update();
        statsRed.update();
    }

}
