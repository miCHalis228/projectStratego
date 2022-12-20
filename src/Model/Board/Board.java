package Model.Board;

import Model.Coordinates.Coordinates;
import Model.Exceptions.BoardNotInitializedException;
import Model.Exceptions.DeadPieceException;
import Model.Exceptions.InvalidCoordinatesException;
import Model.Pieces.ImmovablePiece;
import Model.Pieces.MovablePiece;
import Model.Pieces.Piece;
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

    private static boolean moveMade = false;
    private static boolean attackMade = false;
    private List<Coordinates> possibleCoordinates = new ArrayList<Coordinates>();

    private List<JButton> possibleButtons = new ArrayList<JButton>();

    public Board() {
        spots = new Spot[8][10];
        lastPressed = new int[2];
        lastPressed[0] = -1;
        lastPressed[1] = -1;
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

    public void initButtonSpots() {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 10; col++) {
                spots[row][col].setButton();
                if (!spots[row][col].isLake()) {
                    spots[row][col].getButton().addActionListener(new selectedPawn(this));
                }
            }
    }


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
                spots[piece.getY()][piece.getX()].setButton();
            } else {
                spots[piece.getY()][piece.getX()].setPiece(null);
                spots[piece.getY()][piece.getX()].setButton();

            }
        }

    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.getSpot(i, j).getPiece() != null ? this.getSpot(i, j).getPiece().getRank() + " " : "null ");
            }
            System.out.println();
        }
    }


    public void movePiece(Coordinates temp) throws InvalidCoordinatesException {
        Iterator<JButton> iteratorButtons;
        JButton tempButton;
        if (targetSpot.getPiece() != null) {
            setAttackMade(true);
            try {
                ((MovablePiece) lastPressedPiece.getPiece()).attack(targetSpot.getPiece());
                if (lastPressedPiece.getPiece().isDead() && targetSpot.getPiece().isDead()) {
                    System.out.println("both deeated");
                    lastPressedPiece.setPiece(null);
                    targetSpot.setPiece(null);
                } else if (lastPressedPiece.getPiece().isDead()) {
                    System.out.println("died");
                    lastPressedPiece.setPiece(null);
                } else if(targetSpot.getPiece().isDead()){
                    System.out.println("enemy defeated");
                    ((MovablePiece) lastPressedPiece.getPiece()).move(temp);
                    targetSpot.setPiece(lastPressedPiece.getPiece());
                    lastPressedPiece.setPiece(null);
                }
            } catch (DeadPieceException e) {
                throw new RuntimeException(e);
            }
        } else {
            ((MovablePiece) lastPressedPiece.getPiece()).move(temp);
            targetSpot.setPiece(lastPressedPiece.getPiece());
            lastPressedPiece.setPiece(null);
            possibleCoordinates.clear();

        }
        iteratorButtons = possibleButtons.iterator();
        while (iteratorButtons.hasNext()) {
            tempButton = iteratorButtons.next();
            tempButton.setBorder(BorderFactory.createBevelBorder(1));
        }
        possibleButtons.clear();
        lastPressedPiece.setButton();
        targetSpot.setButton();
        lastPressedPiece = null;
        targetSpot = null;
        lastPressed[0] = -1;
        lastPressed[1] = -1;
        setMoveMade(true);

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
    public void updateBoard(Player pblue, Player pred) {
        this.placePlayer(pblue);
        this.placePlayer(pred);
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

    public class selectedPawn implements ActionListener {
        Board m_board;

        public selectedPawn(Board board) {
            m_board = board;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Iterator<Coordinates> iteratorCoordinates;
            JButton source = (JButton) e.getSource();
            int row = 0, col = 0, index = 0;
            while (true) {
                row = index / 10;
                col = index % 10;
                if (spots[row][col].getButton() == source) {
                    break;
                }
                if (index > 79) {
                    row = col = -1;
                    break;
                }
                index++;
            }
            if (row == -1 && col == -1) {
                return;
            }

//            if (spots[row][col].getPiece()==null ||  spots[row][col].getPiece() instanceof ImmovablePiece) {
            if (lastPressedPiece == null && spots[row][col].getPiece() instanceof ImmovablePiece) {
                return;
            }
            if (lastPressed[0] == -1 && lastPressed[1] == -1) {
                if ((spots[row][col].getPiece() != null)) {
                    lastPressed[0] = row;
                    lastPressed[1] = col;
                    if (spots[row][col].getPiece() != null) {
                        lastPressedPiece = (spots[row][col]);
                        possibleCoordinates = (lastPressedPiece.getPiece().getPossibleMoves(m_board));
                    }
                    if (possibleCoordinates.isEmpty()) {
                        return;
                    }
                    iteratorCoordinates = possibleCoordinates.iterator();
                    Coordinates temp;
                    while (iteratorCoordinates.hasNext()) {
                        temp = iteratorCoordinates.next();
                        spots[temp.getY()][temp.getX()].getButton().setBorder(new LineBorder(Color.BLACK, 5));
                        possibleButtons.add(spots[temp.getY()][temp.getX()].getButton());
                    }
                }
            } else if (lastPressed[0] == row && lastPressed[1] == col) {

                lastPressed[0] = -1;
                lastPressed[1] = -1;

                Coordinates temp;

//                possibleCoordinates = (lastPressedPiece.getPossibleMoves(m_board));
                iteratorCoordinates = possibleCoordinates.iterator();
                while (iteratorCoordinates.hasNext()) {
                    temp = iteratorCoordinates.next();
                    spots[temp.getY()][temp.getX()].getButton().setBorder(BorderFactory.createBevelBorder(1));
                }
                lastPressedPiece = null;
                possibleCoordinates.clear();
            } else {
                iteratorCoordinates = possibleCoordinates.iterator();
                Coordinates temp;
                while (iteratorCoordinates.hasNext()) {
                    temp = iteratorCoordinates.next();
                    System.out.println(temp);
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
}
