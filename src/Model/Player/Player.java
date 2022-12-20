package Model.Player;

import Model.Coordinates.Coordinates;
import Model.Pieces.ImmovablePiece;
import Model.Pieces.MovablePiece;
import Model.Pieces.Piece;
import Model.Pieces.SpecialMovablePiece;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private String m_name;
    private boolean isBlue;
    private int revival_counter;
    private String imagePath;

    private int m_mode;
    private String m_HiddenImagePath;
    private List<Piece> Pieces;
    private List<Piece> DeadPieces;
    private List<Piece> CapturedPieces;

    public Player(String name, int mode) throws IllegalArgumentException {
        switch (name) {
            case "Red":
                isBlue = false;
                imagePath = "src\\images\\RedPieces";
                break;
            case "Blue":
                isBlue = true;
                imagePath = "src\\images\\BluePieces";
                break;
            default:
                throw new IllegalArgumentException("Incorrect Name for Player");

        }
        this.m_mode=mode;
        this.m_name = name;
        Pieces = new ArrayList<Piece>();
        revival_counter = 2;
        DeadPieces = new ArrayList<Piece>();
        CapturedPieces = new ArrayList<Piece>();
        this.m_HiddenImagePath = imagePath + "Hidden.png";
        initCards(m_mode);
    }

    /**
     * IF MODE IS NOT KNOWN DONT INIT CARDS
     * @param name
     * @throws IllegalArgumentException
     */
    public Player(String name) throws IllegalArgumentException {
        switch (name) {
            case "Red":
                isBlue = false;
                imagePath = "src\\images\\RedPieces";
                break;
            case "Blue":
                isBlue = true;
                imagePath = "src\\images\\BluePieces";
                break;
            default:
                throw new IllegalArgumentException("Incorrect Name for Player");

        }
        this.m_name = name;
        Pieces = new ArrayList<Piece>();
        revival_counter = 2;
        DeadPieces = new ArrayList<Piece>();
        CapturedPieces = new ArrayList<Piece>();
        this.m_HiddenImagePath = imagePath + "Hidden.png";
    }

    /**
     * <b>Transformer</b> Initializes the Piece List of the player depending on the mod chosen by the player at the start of the Game
     * <b>pre-condition</b> Cards are not initialized
     * <b>post-condition</b> Cards are initialized all with coordinates (0,0) to later be randomized
     * @param mode
     */
    public void initCards(int mode) {
        int i;

        switch (mode) {
            /*code for 30 pieces*/
            case 0:
                Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\flag.png", isBlue ,true ));
                for (i = 0; i < 6; i++)
                    Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\trap.png" , isBlue , false));
                Pieces.add(new SpecialMovablePiece(-1, -1, imagePath + "\\slayer.png", 1, isBlue));
                for (i = 0; i < 4; i++)
                    Pieces.add(new SpecialMovablePiece(-1, -1, imagePath + "\\scout.png", 2, isBlue));
                for (i = 0; i < 5; i++)
                    Pieces.add(new SpecialMovablePiece(-1, -1, imagePath + "\\dwarf.png", 3, isBlue));
                for (i = 0; i < 2; i++)
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\elf.png", 4, isBlue));
                for (i = 0; i < 2; i++)
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\rank5.png", 5, isBlue));
                for (i = 0; i < 2; i++)
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\sorceress.png", 6, isBlue));
                for (i = 0; i < 3; i++)
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\beastRider.png", 7, isBlue));
                for (i = 0; i < 2; i++)
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\knight.png", 8, isBlue));
                Pieces.add(new MovablePiece(-1, -1, imagePath + "\\mage.png", 9, isBlue));
                Pieces.add(new MovablePiece(-1, -1, imagePath + "\\dragon.png", 10, isBlue));
                break;
            case 1:/*Code for 25 pieces*/
                break;
            default:
                break;
        }

    }

    /**
     * <b>Transformer</b>: Randomizes the position of the player's pieces depending on if they are blue(top) or red (bottom)
     * <b>pre-conditions</b> All pieces positions are set to (0,0)
     * <b>pre-conditions</b> All pieces positions are set only ONCE at the begining of the round to random coordinates on the player's side
     */
    public void randomizePositions() {
        UniqueRng rng = new UniqueRng(30,isBlue);
        Iterator<Piece> iterator = Pieces.iterator();
        Piece p;
        int next;
        while (rng.hasNext()) {
            next = rng.next();
            p = iterator.next();
            p.setX(next%10);
            p.setY(next/10);
        }
    }

    /**
     * <b>Accessor</b>: Returns the path for the players hidden image
     * @return image path for when it is not his turn
     */
    public String getM_HiddenImagePath() {
        return m_HiddenImagePath;
    }

    /**
     * <b>Accessor</b> Calculates and returns if the player is defeated or not
     * @return true if the player can continue playing
     */
    public boolean isDefeated() {
        if(Pieces.get(0).isDead()){
            return true;
        }
        /*if it has movable pieces and flag is not captured return false*/
        return false;
    }

    /**
     * <b>Transformer</b> Changes the coordinates and state of one of its Pieces
     * called from controller on action performed by the buttons
     *
     * @param newPos is the coordinate given to move by the player
     */
    public void makeMove(Coordinates newPos) {
        //TODO
    }

    /**
     * <b>Transformer</b> Used to call the attack method from its pieces
     * <b>pre-condition</b> Both the attacking and defending piece are not dead
     * <b>post-condition</b> One or Both of these Pieces is Defeated
     * @param enemyPiece
     */
    public void attacks(Piece enemyPiece) {

    }

    /**
     * <b>Accessor</b> Returns the imagePath for this players folder
     * @return the String containing the path for the images
     */
    public String getImagePath(){
        return imagePath;
    }

    public void setDeadPieces(){
        DeadPieces = Pieces.stream()
                .filter(piece -> piece.isDead() == true)
                .collect(Collectors.toList());
        System.out.println("Dead Pieces" + DeadPieces);
    }

    public class UniqueRng implements Iterator<Integer> {
        private List<Integer> numbers = new ArrayList<>();

        public UniqueRng(int n,boolean isBlue) {
            if(isBlue){
                for (int i = 0; i < n; i++) {
                    numbers.add(i);
                }
            }else{
                int k = (n==30)?50:55;
                for (int i=k; i <k + n; i++) {
                    numbers.add(i);
                }
            }

            Collections.shuffle(numbers);
        }
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return numbers.remove(0);
        }

        @Override
        public boolean hasNext() {
            return !numbers.isEmpty();
        }
    }

    public List<Piece> getPieces() {
        return Pieces;
    }
}
