package Controller;

import Model.Board.Board;
import Model.Exceptions.BoardNotInitializedException;
import Model.Player.Player;
import View.Field;
import View.Stats;

import javax.swing.*;
import java.awt.*;

/**
 * @author Michalis Ierodiakonou
 */
public class Controller {

    private int round = 0;
    private static boolean turnRed;
    private Player playerBlue;
    private Player playerRed;
    private Stats statsBlue;
    private Stats statsRed;

    private JPanel hiddenBlue;
    private JPanel hiddenRed;
    private Board board;

    /**
     * <b>Constructor</b> : Constructs a new Constructor with 2 players
     * <b>post-condition</b> A Blue and a Red Player are created
     */
    public Controller(int mode) {
        playerBlue = new Player("Blue",mode);
        playerBlue.randomizePositions();
        playerRed = new Player("Red",mode);
        playerRed.randomizePositions();
        board = new Board(playerBlue,playerRed);
        turnRed=true;
        statsBlue = new Stats(playerBlue,mode);
        statsRed = new Stats(playerRed,mode);

        try {
            board.initializeBoard();
            board.placePlayer(playerBlue);
            board.placePlayer(playerRed);
        } catch (BoardNotInitializedException e) {
            throw new RuntimeException(e);
        }

        Field f = new Field(board);

        JFrame frame = new JFrame();
        frame.setLayout(new CardLayout());
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
        hiddenBlue = f.getHiddenBlue();
        hiddenRed = f.getHiddenRed();
        frame.add(hiddenRed);
        frame.add(hiddenBlue);
        statsBlue.addComponents(frame);
        statsRed.addComponents(frame);
        frame.pack();

        hiddenRed.setVisible(true);
        hiddenBlue.setVisible(false);
        hidePlayer();
        statsRed.hideAll();
        statsBlue.showAll();
        frame.setVisible(true);

        while(!playerBlue.isDefeated() && !playerRed.isDefeated()){
            try {
                Thread.sleep(90);
                if(board.getMoveMade()){
                    if(board.getAttackMade()){
                        updateLists();
                    }
                    /**
                     * make second panel
                     * change between the two
                     */
                    nextTurn();
                    if(!turnRed) {
                        nextRound();
                    }
                    board.updateBoard(playerBlue,playerRed);
                    Thread.sleep(1000);
                    hidePlayer();
                    f.swapFields(turnRed);
                    board.setAttackMade(false);
                    board.setMoveMade(false);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }

    private void updateLists() {
            playerBlue.setDeadPieces();
            playerRed.setDeadPieces();
            statsBlue.update();
            statsRed.update();
    }

    /**
     * <b>Accessor</b>: Returns whose player turn it is
     * <b>post-condition</b> true if Blue is playing, false if Red is
     *
     * @return this round's turn
     */
    public boolean isturnRed() {
        return turnRed;
    }

    /**
     * <b>Transformer</b>: Change whose player turn it is to play
     * <b>post-condition</b> From Blue to Red to Blue...
     */
    public void nextTurn() {
//        if(turnRed)
//            playerRed.nextTurn();
//        else
//            playerBlue.nextTurn();
        turnRed = !turnRed;
    }

    /**
     * <b>Transformer</b>: Go to the next round
     */
    public void nextRound() {
        round++;
    }

    /**
     * <b>Accessor</b>: Returns the number of round we are now in the game
     *
     * @return current round
     */
    public int getRound() {
        return round;
    }

    /**
     * <b>Accessor</b> Returns the current game board
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * <b>Accessor</b> Returns the Blue Player
     * @return the instance of Blue player
     */
    public Player getPlayerBlue() {
        return playerBlue;
    }

    /**
     * <b>Accessor</b> Returns the Red Player
     * @return the instance of Red player
     */
    public Player getPlayerRed() {
        return playerRed;
    }

    public static void main(String[] args) {
        Controller c = new Controller(0);
    }

    public void hidePlayer(){
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

    }
}

