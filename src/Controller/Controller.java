package Controller;

import Model.Board.Board;
import Model.Exceptions.BoardNotInitializedException;
import Model.Player.Player;
import View.*;

import java.math.RoundingMode;

/**
 * @author Michalis Ierodiakonou
 */
public class Controller {
    private int round = 0;
    private static boolean turnRed;
    private Player playerBlue;
    private Player playerRed;
    private int mode;
//    private JPanel playAgainPanel;
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
        this.startGame();
    }

    public void startGame(){
        this.init();
        this.menu();
        mode = msw.getMode();
        this.createBoard();
        this.afterMenu();
        this.GameLoop();
        this.endGame();
    }
//    public final void killController(){
//        view.dispose();
//        blueWins.dispose();
//        redWins.dispose();
//        loading.dispose();
//
//    }
    /**
     *
     */
    public void init(){
        msw = new ModSelectionWindow();
        loading = new LoadingScreen();
//        playAgainPanel = new JPanel();
//        Play_Again:{
//            JButton playAgainButton = new JButton("PLAY AGAIN");
//            playAgainPanel.setOpaque(false);
//            playAgainPanel.setLayout(new FlowLayout());
//            playAgainPanel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-100,Toolkit.getDefaultToolkit().getScreenSize().height/2+150,200,50);
//            playAgainButton.setBackground(Color.BLACK);
//            playAgainButton.setForeground(Color.DARK_GRAY);
//            playAgainButton.setOpaque(false);
//            playAgainButton.setBorderPainted(false);
//            playAgainButton.setFocusable(false);
//            playAgainButton.setFont(new Font("Garamond", Font.BOLD, 30));
//            playAgainButton.setBorder(BorderFactory.createRaisedBevelBorder());
//            playAgainButton.addActionListener(e -> {
//                killController();
////                Controller controller = new Controller();
//                startGame();
//            });
//            playAgainPanel.add(playAgainButton);
//
//        }
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
        this.board = new Board(playerBlue,playerRed,mode);
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
        while(!board.playerDefeated()){
            try {
                Thread.sleep(90);
                if(board.getMoveMade()){
                    if(!board.isRevivePending()) {
                        if (rsf != null) {
//                            rsf.dispose();
                            rsf = null;
                        }
//                        view.toFront();
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
                        Thread.sleep(1000);
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

    public void endGame(){
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
    private void updateLists() {
            playerBlue.setDeadPieces();
            playerRed.setDeadPieces();
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

