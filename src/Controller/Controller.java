package Controller;

import Model.Board.Board;
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
    private winningFrame blueWins;
    private winningFrame redWins;
    private reviveSelectionFrame rsf=null;

    /**
     * <b>Constructor</b> : Constructs a new Constructor with 2 players
     * <b>post-condition</b> A Blue and a Red Player are created
     */
    public Controller() {
        this.init();
    }

    /**
     * <b>Transformer:</b> Starts the game
     * Method which simulates game
     */
    public void startGame(){
        this.menu();
        mode = msw.getMode();
        this.createBoard();
        this.afterMenu();
        this.GameLoop();
        this.endGame();
    }

    /**
     * <b>Transformer:</b>Method creating the menu and loading screen
     * <b>pre-condition</b> objects are null
     * <b>post-condition</b> objects are created
     */
    private void init(){
        msw = new ModSelectionWindow();
        loading = new LoadingScreen();
    }

    /**
     * <b>Transformer:</b>Method creating a menu GUI and wating for mod selection to return game mode
     * <b>post-condition</b> Game mode is set
     */
    private void menu(){
        Thread T = new Controller.Thread_extended_class(msw);
        T.start();
        try {
            loading.setVisible(true);
            T.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>Transformer:</b>Method to create players and randomize piece positions
     * Also creates game board object and places each player's piece on it
     */
    private void createBoard(){
        try {
            this.playerBlue = new Player("Blue",mode);
            this.playerBlue.randomizePositions();
            this.playerRed = new Player("Red",mode);
            this.playerRed.randomizePositions();

        } catch (Exception e){
            System.out.println(e);
        }
        this.blueWins = new winningFrame(playerBlue);
        this.redWins = new winningFrame(playerRed);
        this.board = new Board(playerBlue,playerRed,mode);
        turnRed=true;
        board.initializeBoard();
        board.placePlayer(playerBlue);
        board.placePlayer(playerRed);

    }

    /**
     * <b>Transformer:</b>Method to visualize the transition between the selection screen and the game
     */
    private void afterMenu(){
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
    }

    /**
     * Game Loop checking for defeated players or rescues initiated
     */
    private void GameLoop(){
        while(!board.playerDefeated()){
            try {
                Thread.sleep(90);
                if(board.getMoveMade()){
                    if(!board.isRevivePending()) {
                        if (rsf != null) {
                            rsf = null;
                        }
                        if (board.getAttackMade() && board.isReviveMade()) {
                            updateLists();
                            view.updateStats();
                            board.setReviveMade(false);
                        } else if (board.getAttackMade()) {
                            updateLists();
                            view.updateStats();
                        } else if (board.isReviveMade()) {
                            board.setReviveMade(false);
                            view.setVisible(true);

                        }
                        /**
                         * make second panel
                         * change between the two
                         */
                        nextTurn();
                        if (turnRed) {
                            nextRound();
                        }
                        board.updateBoard();
                        Thread.sleep(500);
                        view.updateView(turnRed, round);
                        board.setAttackMade(false);
                        board.setMoveMade(false);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method for ending the game and showing the winning player screen
     */
    private void endGame(){
        if(playerBlue.isDefeated()){
            redWins.setVisible(true);
        } else {
            blueWins.setVisible(true);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        view.setVisible(false);
    }

    /**
     * <b>Transformer:</b> Used to update the dead pieces of each player after the attacks or moves
     */
    private void updateLists() {
            playerBlue.setDeadPieces();
            playerRed.setDeadPieces();
    }

    /**
     * <b>Transformer</b>: Change whose player turn it is to play
     * <b>post-condition</b> From Blue to Red to Blue...
     */
    private void nextTurn() {
        turnRed = !turnRed;
    }

    /**
     * <b>Transformer</b>: Go to the next round
     */
    private void nextRound() {
        round++;
    }

    /**
     * <b> A custom inner class extending Thread class waits until
     * mode is given</b>
     *
     * <b>post-condition: </b> Mode is given by the user and returned to controller
     */
    private class Thread_extended_class extends Thread {
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

