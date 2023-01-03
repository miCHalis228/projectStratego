package Model.Board;

import Model.Coordinates.Coordinates;
import Model.Exceptions.BoardNotInitializedException;
import Model.Exceptions.DeadPieceException;
import Model.Exceptions.InvalidCoordinatesException;
import Model.Pieces.ImmovablePiece;
import Model.Pieces.MovablePiece;
import Model.Pieces.Piece;
import Model.Pieces.Scout;
import Model.Player.Player;
import Model.Spot.Spot;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {

    private static Spot lastPressedPiece;
    private static Spot targetSpot;
    private Spot[][] spots;
    private int[] lastPressed;
    private Player playerBlue;
    private Player playerRed;

    private Piece pieceToRevive;
    private int m_mode;

    private int movables = 0;
    private boolean moveMade = false;
    private boolean attackMade = false;
    private boolean revivePending = false;
    private boolean reviveMade = false;
    private List<Coordinates> possibleCoordinates = new ArrayList<>();

    private List<JButton> possibleButtons = new ArrayList<JButton>();

    /**
     * <b>Constructor: </b> Creates a board object and sets private fields
     * @param blue instance of blue player
     * @param red instance of blue player
     * @param mode mode given by the user
     */
    public Board(Player blue, Player red, int mode) {
        m_mode = mode;
        playerBlue = blue;
        playerRed = red;
        spots = new Spot[8][10];
        lastPressed = new int[2];
        lastPressed[0] = -1;
        lastPressed[1] = -1;
        pieceToRevive = null;
        if(m_mode == 1 || m_mode==3)
            movables = 13;
        else{
            movables = 23;
        }
    }

    /**
     * <b>Transformer</b>: Initialize Board with all NULL spots and set Lakes/inaccessible spots in the middle
     * <b>post-condition</b>: Board is initialized with all
     *
     * @throws BoardNotInitializedException in case of error
     */
    public void initializeBoard() throws BoardNotInitializedException {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 10; j++) {
                this.spots[i][j] = new Spot(null);
            }

        spots[3][2].setLake(true);
        spots[3][3].setLake(true);
        spots[4][2].setLake(true);
        spots[4][3].setLake(true);
        spots[3][6].setLake(true);
        spots[3][7].setLake(true);
        spots[4][6].setLake(true);
        spots[4][7].setLake(true);

        initButtonSpots();
    }

    /**
     *
     */
    public void initButtonSpots() {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 10; col++) {
                spots[row][col].setButton();
                if (!spots[row][col].isLake()) {
                    spots[row][col].getButtonBlue().addActionListener(new selectedPawn(this));
                    spots[row][col].getButtonRed().addActionListener(new selectedPawn(this));
                }
            }
    }

    /**
     * <b>Accessor</b> Returns a spot of the board
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Spot at given coordinates
     */
    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }

    /**
     * <b>Transformer</b>: Places p's (player) pieces on the board
     *
     * @param p A reference to the player to place its pieces on the board
     */
    public void placePlayer(Player p) {
        Iterator<Piece> iterator = p.getPieces().iterator();
        Piece piece;

        while (iterator.hasNext()) {
            piece = iterator.next();
            if (!piece.isDead()) {
                spots[piece.getY()][piece.getX()].setPiece(piece);
            } else {
                spots[piece.getY()][piece.getX()].setPiece(null);
            }
        }
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 10; col++) {
                spots[row][col].setButton();
            }
    }


    /**
     * simulates movement and/or calls attack method
     * @param temp selected piece's desired coordinates
     * @throws InvalidCoordinatesException if coordinates are off the board
     */
    private void movePiece(Coordinates temp) throws InvalidCoordinatesException {
        Iterator<JButton> iteratorButtons;
        JButton tempButton;
        iteratorButtons = possibleButtons.iterator();
        //highlight possible coordinates buttons
        while (iteratorButtons.hasNext()) {
            tempButton = iteratorButtons.next();
            tempButton.setBorder(BorderFactory.createBevelBorder(1));
        }
        //if there is a piece in the target coordinates make attack else just move
        if (targetSpot.getPiece() != null) {
            moveAndAttack(temp);
            setAttackMade(true);
        } else {
            ((MovablePiece) lastPressedPiece.getPiece()).move(temp);
            swapSpots();
            if (!possibleRevive()) {
                return;
            }
        }
        initFields();
    }

    /**
     * swap the piece of the last pressed spot with the target spot
     */
    private void swapSpots() {
        targetSpot.setPiece(lastPressedPiece.getPiece());
        lastPressedPiece.setPiece(null);
        lastPressedPiece.setButton();
        targetSpot.setButton();
    }

    /**
     * set all private temporary fields to their default values
     */
    private void initFields() {
        possibleButtons.clear();
        lastPressedPiece = null;
        targetSpot = null;
        lastPressed[0] = -1;
        lastPressed[1] = -1;
        setMoveMade(true);
    }

    /**
     * checks if the player has a piece that can make a rescue happen
     * @return true if a player can revive or not
     */
    private boolean possibleRevive() {
        if (!(targetSpot.getPiece() instanceof Scout) && !targetSpot.getPiece().hasRevived()) {
            if (targetSpot.getPiece().isBlue() && targetSpot.getPiece().getY() == 7) {
                //REVIVES
                pieceToRevive = playerBlue.revive();
                if (pieceToRevive == null) {
                    revivePending = false;
                    reviveMade = false;
                    initFields();
                    return false;
                }
                revivePending = true;
                for (int col = 0; col < 10; col++) {
                    if (spots[0][col].isEmpty()) {
                        spots[0][col].getButtonBlue().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                    if (spots[1][col].isEmpty()) {
                        spots[1][col].getButtonBlue().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                    if (spots[2][col].isEmpty()) {
                        spots[2][col].getButtonBlue().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                }
                targetSpot.getPiece().revivedSomebody();
            } else if (!targetSpot.getPiece().isBlue() && targetSpot.getPiece().getY() == 0) {
                //REVIVES
                pieceToRevive = playerRed.revive();
                if (pieceToRevive == null) {
                    revivePending = false;
                    reviveMade = false;
                    initFields();
                    return false;
                }
                revivePending = true;
                for (int col = 0; col < 10; col++) {
                    if (spots[5][col].isEmpty()) {
                        spots[5][col].getButtonRed().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                    if (spots[6][col].isEmpty()) {
                        spots[6][col].getButtonRed().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                    if (spots[7][col].isEmpty()) {
                        spots[7][col].getButtonRed().setBorder(new LineBorder(Color.BLACK, 5));
                    }
                }
                targetSpot.getPiece().revivedSomebody();
            }
        }
        return true;
    }

    /**
     *
     * @param temp selected piece's desired coordinates
     * @throws InvalidCoordinatesException if coordinates are off the board
     */
    private void moveAndAttack(Coordinates temp) throws InvalidCoordinatesException {
        try {
            ((MovablePiece) lastPressedPiece.getPiece()).attack(targetSpot.getPiece());
            if (lastPressedPiece.getPiece().isDead() && targetSpot.getPiece().isDead()) {
                if (lastPressedPiece.getPiece().isBlue()) {
                    playerRed.attacks(targetSpot.getPiece().getRank());
                    playerBlue.defends(lastPressedPiece.getPiece().getRank());
                } else {
                    playerBlue.attacks(targetSpot.getPiece().getRank());
                    playerRed.defends(lastPressedPiece.getPiece().getRank());
                }
                targetSpot.setPiece(null);
                lastPressedPiece.setPiece(null);
                lastPressedPiece.setButton();
                targetSpot.setButton();
            } else if (lastPressedPiece.getPiece().isDead()) {
                if (lastPressedPiece.getPiece().isBlue()) {
                    playerBlue.defends(lastPressedPiece.getPiece().getRank());
                    playerRed.doesAttack();
                } else {
                    playerRed.defends(lastPressedPiece.getPiece().getRank());
                    playerBlue.doesAttack();
                }
                lastPressedPiece.setPiece(null);
                lastPressedPiece.setButton();
                targetSpot.setButton();
            } else if (targetSpot.getPiece().isDead()) {
                if (lastPressedPiece.getPiece().isBlue()) {
                    playerRed.attacks(targetSpot.getPiece().getRank());
                } else {
                    playerBlue.attacks(targetSpot.getPiece().getRank());
                }
                ((MovablePiece) lastPressedPiece.getPiece()).move(temp);
                swapSpots();
                possibleRevive();
            }
        } catch (DeadPieceException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * <b>Accessor</b>: Return the current state of the board
     *
     * @return The board to update the View
     */
    public Spot[][] getBoard() {
        return spots;
    }

    /**
     * <b>Transformer</b>: Updates the board based on both players Pieces
     * <b>post-condition</b> Board is updated
     */
    public void updateBoard() {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 10; col++) {
                spots[row][col].setButton();
            }
    }

    public boolean getMoveMade() {
        return moveMade;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }

    public boolean getAttackMade() {
        return attackMade;
    }

    public void setAttackMade(boolean attackMade) {
        this.attackMade = attackMade;
    }

    public boolean isRevivePending() {
        return revivePending;
    }

    public void setRevivePending(boolean revivePending) {
        this.revivePending = revivePending;
    }

    public boolean isReviveMade() {
        return reviveMade;
    }

    public void setReviveMade(boolean reviveMade) {
        this.reviveMade = reviveMade;
        if (!reviveMade) setRevivePending(false);
    }

    public class selectedPawn implements ActionListener {
        Board m_board;
        public selectedPawn(Board board) {
            m_board = board;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean turnBlue;
            Iterator<Coordinates> iteratorCoordinates;
            JButton source = (JButton) e.getSource();
            int row = 0, col = 0, index = 0;

            while (true) {
                if (index > 79) {
                    row = col = -1;
                    return;
                }
                row = index / 10;
                col = index % 10;
                if (spots[row][col].getButtonRed() == source) {
                    turnBlue = false;
                    break;
                }
                if (spots[row][col].getButtonBlue() == source) {
                    turnBlue = true;

                    break;
                }
                index++;
            }
            if (revivePending && pieceToRevive == null) {
                reviveMade = false;
                revivePending = false;
                moveMade = true;
                lastPressedPiece = null;
                lastPressed[0] = -1;
                lastPressed[1] = -1;
                System.out.println("abort revive");
            }
            if (revivePending && pieceToRevive != null) {
                if (spots[row][col].getPiece() == null) {
                    if (pieceToRevive != null) {
                        if (!turnBlue && (row == 5 || row == 6 || row == 7)) {
                            playerBlue.increaseRescues();
                            playerRed.removeCapture(pieceToRevive.getRank());
                            spots[row][col].setPiece(pieceToRevive);
                            pieceToRevive.setCoordinates(new Coordinates(col, row));
                            pieceToRevive = null;
                            revivePending = false;
                        } else if (turnBlue && (row == 0 || row == 1 || row == 2)) {
                            playerRed.increaseRescues();
                            playerBlue.removeCapture(pieceToRevive.getRank());
                            spots[row][col].setPiece(pieceToRevive);
                            pieceToRevive.setCoordinates(new Coordinates(col, row));
                            revivePending = false;
                            pieceToRevive = null;
                        }
                    }
                }
            }

            if (moveMade) return;

            if (lastPressedPiece == null && spots[row][col].getPiece() instanceof ImmovablePiece) {
                return;
            }
            if (lastPressed[0] == -1 && lastPressed[1] == -1) {
                if ((spots[row][col].getPiece() != null)) {
                    if (spots[row][col].getPiece().isFlipped()) {
                        return;
                    }
                    lastPressedPiece = (spots[row][col]);
                    possibleCoordinates = (lastPressedPiece.getPiece().getPossibleMoves(m_board, m_mode));

                    if (possibleCoordinates.isEmpty()) {
                        return;
                    }
                    lastPressed[0] = row;
                    lastPressed[1] = col;
                    if (turnBlue) {
                        lastPressedPiece.getButtonBlue().setBorder(new LineBorder(Color.WHITE, 4));
                    } else {
                        lastPressedPiece.getButtonRed().setBorder(new LineBorder(Color.WHITE, 4));
                    }
                    iteratorCoordinates = possibleCoordinates.iterator();
                    Coordinates temp;
                    while (iteratorCoordinates.hasNext()) {
                        temp = iteratorCoordinates.next();
                        if (turnBlue) {
                            spots[temp.getY()][temp.getX()].getButtonBlue().setBorder(new LineBorder(Color.BLACK, 5));
                            possibleButtons.add(spots[temp.getY()][temp.getX()].getButtonBlue());
                        } else {
                            spots[temp.getY()][temp.getX()].getButtonRed().setBorder(new LineBorder(Color.BLACK, 5));
                            possibleButtons.add(spots[temp.getY()][temp.getX()].getButtonRed());
                        }
                    }
                }
            } else if (lastPressed[0] == row && lastPressed[1] == col) {
                lastPressed[0] = -1;
                lastPressed[1] = -1;
                Coordinates temp;
                if (turnBlue) {
                    lastPressedPiece.getButtonBlue().setBorder(BorderFactory.createBevelBorder(1));
                } else {
                    lastPressedPiece.getButtonRed().setBorder(BorderFactory.createBevelBorder(1));
                }
                iteratorCoordinates = possibleCoordinates.iterator();
                while (iteratorCoordinates.hasNext()) {
                    temp = iteratorCoordinates.next();
                    if (turnBlue) {
                        spots[temp.getY()][temp.getX()].getButtonBlue().setBorder(BorderFactory.createBevelBorder(1));
                    } else {
                        spots[temp.getY()][temp.getX()].getButtonRed().setBorder(BorderFactory.createBevelBorder(1));
                    }
                }
                lastPressedPiece = null;
                possibleCoordinates.clear();
            } else {
                iteratorCoordinates = possibleCoordinates.iterator();
                Coordinates temp;
                while (iteratorCoordinates.hasNext()) {
                    temp = iteratorCoordinates.next();
                    if (temp.getX() == col && temp.getY() == row) {
                        try {
                            targetSpot = spots[row][col];
                            movePiece(temp);
                            break;
                        } catch (InvalidCoordinatesException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                //TODO ERROR SOUND
            }


        }

    }

    public boolean playerDefeated(){
        int deadMovables = 0;

        Iterator<Piece> iterator;
        Piece p;

        /*BLUE PLAYER*/
        deadMovables=0;
        iterator = playerBlue.getPieces().iterator();
        while (iterator.hasNext()) {
            p = iterator.next();
            if(p instanceof MovablePiece && !p.isDead()){
                if(p.getPossibleMoves(this,m_mode)==null){
                    playerBlue.setDefeated();
                    return true;
                }
            } else if(p instanceof MovablePiece && p.isDead()){
                deadMovables++;
            }
        }
        if(playerBlue.flagCaptured() || deadMovables==movables) {
            playerBlue.setDefeated();
            return true;
        }

        /*RED PLAYER*/
        deadMovables=0;
        iterator = playerRed.getPieces().iterator();
        while (iterator.hasNext()) {
            p = iterator.next();
            if(p instanceof MovablePiece && !p.isDead()){
                if(p.getPossibleMoves(this,m_mode)==null){
                    playerRed.setDefeated();
                    return true;
                }
            }
        }
        if(playerRed.flagCaptured() || deadMovables==movables) {
            playerRed.setDefeated();
            return true;
        }
        return false;
    }
}
