package Controller;

import Model.Board.Board;
import Model.Exceptions.BoardNotInitializedException;
import Model.Player.Player;
import View.*;
/**
 * @author Michalis Ierodiakonou
 */
public class Controller {

    private int round = 0;
    private static boolean turnRed;
    private Player playerBlue;
    private Player playerRed;

    private int mode;

    private View view;
    private Board board;
    private LoadingScreen loading;
    private ModSelectionWindow msw;

    /**
     * <b>Constructor</b> : Constructs a new Constructor with 2 players
     * <b>post-condition</b> A Blue and a Red Player are created
     */
    public Controller() {
        msw = new ModSelectionWindow();
        loading = new LoadingScreen();
        Thread T = new Controller.Thread_extended_class(msw);
        T.start();
        try {
            loading.setVisible(true);
            T.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mode = msw.getMode();
        playerBlue = new Player("Blue",mode);
        playerBlue.randomizePositions();
        playerRed = new Player("Red",mode);
        playerRed.randomizePositions();
        board = new Board(playerBlue,playerRed);
        turnRed=true;
//        statsBlue = new Stats(playerBlue,3);
//        statsRed = new Stats(playerRed,mode);
        try {
            board.initializeBoard();
            board.placePlayer(playerBlue);
            board.placePlayer(playerRed);
        } catch (BoardNotInitializedException e) {
            throw new RuntimeException(e);
        }
        view = new View(playerBlue,playerRed,board,mode);
        for(int i=0;i<6;i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            loading.count();
            loading.setVisible(true);
        }
        view.updateView(turnRed,round);
        view.setVisible(true);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loading.setVisible(false);
        loading.dispose();



        while(!playerBlue.isDefeated() && !playerRed.isDefeated()){
            try {
                Thread.sleep(90);
                if(board.getMoveMade()){
                    if(board.getAttackMade()){
                        updateLists();
                        view.updateStats();
                    }
                    /**
                     * make second panel
                     * change between the two
                     */
                    nextTurn();
                    if(turnRed) {
                        nextRound();
                    }
                    board.updateBoard(playerBlue,playerRed);
                    Thread.sleep(1000);
                    view.updateView(turnRed,round);
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
        Controller c = new Controller();
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

    }
}

