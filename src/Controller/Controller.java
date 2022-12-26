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
    private winningFrame blueWins;
    private winningFrame redWins;

    /**
     * <b>Constructor</b> : Constructs a new Constructor with 2 players
     * <b>post-condition</b> A Blue and a Red Player are created
     */
    public Controller() {
        this.init();
        this.startGame();
    }

    public void startGame(){
        this.menu();
        mode = msw.getMode();
        this.createBoard();
        this.afterMenu();
        this.GameLoop();
        this.endGame();
    }
    /**
     *
     */
    public void init(){
        msw = new ModSelectionWindow();
        loading = new LoadingScreen();
    }

    /**
     *
     */
    public void menu(){
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
     *
     */
    public void createBoard(){
        this.playerBlue = new Player("Blue",mode);
        this.playerBlue.randomizePositions();
        this.playerRed = new Player("Red",mode);
        this.playerRed.randomizePositions();
        this.blueWins = new winningFrame(playerBlue);
        this.redWins = new winningFrame(playerRed);
        this.board = new Board(playerBlue,playerRed);
        turnRed=true;
        try {
            board.initializeBoard();
            board.placePlayer(playerBlue);
            board.placePlayer(playerRed);
        } catch (BoardNotInitializedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    public void afterMenu(){
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
     * Game Loop
     */
    public void GameLoop(){
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
    }

    public void endGame(){
        view.setVisible(false);
        if(playerBlue.isDefeated()){
            redWins.setVisible(true);
        } else {
            blueWins.setVisible(true);
        }
    }
    private void updateLists() {
            playerBlue.setDeadPieces();
            playerRed.setDeadPieces();
    }

    public Player getPlayerBlue() {
        return playerBlue;
    }

    public Player getPlayerRed() {
        return playerRed;
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

