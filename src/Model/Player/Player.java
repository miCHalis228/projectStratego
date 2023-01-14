package Model.Player;

import Model.Coordinates.Coordinates;
import Model.Exceptions.PathNotFoundException;
import Model.Pieces.*;
import View.reviveSelectionFrame;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    boolean isDefeated = false;
    private String m_name;
    private boolean isBlue;
    private int revival_counter;
    private String imagePath;

    private int m_mode;
    private List<Piece> Pieces;
    private List<Piece> DeadPieces;

    private int attackCount;
    private int succesfulAttacks;

    private int[] captures;
    public Player(String name, int mode) throws IllegalArgumentException {
        attackCount=0;
        succesfulAttacks=0;
        revival_counter=0;
        switch (name) {
            case "Red":
                isBlue = false;
                imagePath = "src\\images\\RedPieces";
                m_name = "Volcandria";
                break;
            case "Blue":
                isBlue = true;
                imagePath = "src\\images\\BluePieces";
                m_name = "Everwinter";
                break;
            default:
                throw new IllegalArgumentException("Incorrect Name for Player");

        }
        this.m_mode=mode;

        Pieces = new ArrayList<Piece>();
        DeadPieces = new ArrayList<Piece>();
        captures = new int[12];

        Image img = new ImageIcon(new StringBuilder().append(imagePath).append("\\Hidden.png").toString()).getImage();
        img = img.getScaledInstance(105, 95, Image.SCALE_SMOOTH);
        initCards(m_mode);
    }

    /**
     * IF MODE IS NOT KNOWN DON'T INIT CARDS
     * @param name of the player
     * @throws IllegalArgumentException when a name other than the given two
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
        DeadPieces = new ArrayList<Piece>();
        captures = new int[12];

        Image img = new ImageIcon(imagePath + "\\Hidden.png").getImage();
        img = img.getScaledInstance(105, 95, Image.SCALE_SMOOTH);
    }

    /**
     * <b>Transformer</b> Initializes the Piece List of the player depending on the mod chosen by the player at the start of the Game
     * <b>pre-condition</b> Cards are not initialized
     * <b>post-condition</b> Cards are initialized all with coordinates (0,0) to later be randomized
     * @param mode selected by the player
     */
    public void initCards(int mode) {
        int i;
        try{
            switch (mode) {
                /*code for 30 pieces*/
                case 0, 2:
                    Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\flag.png", isBlue, true));
                    for (i = 0; i < 6; i++)
                        Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\trap.png", isBlue, false));
                    Pieces.add(new Slayer(-1, -1, imagePath + "\\slayer.png", 1, isBlue));
                    for (i = 0; i < 4; i++)
                        Pieces.add(new Scout(-1, -1, imagePath + "\\scout.png", 2, isBlue));
                    for (i = 0; i < 5; i++)
                        Pieces.add(new Dwarf(-1, -1, imagePath + "\\dwarf.png", 3, isBlue));
                    for (i = 0; i < 2; i++) {
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\elf.png", 4, isBlue));
                    }
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
                case 1 , 3:/*Code for half pieces*/
                    Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\flag.png", isBlue ,true ));
                    for (i = 0; i < 3; i++)
                        Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\trap.png" , isBlue , false));
                    Pieces.add(new Slayer(-1, -1, imagePath + "\\slayer.png", 1, isBlue));
                    for (i = 0; i < 2; i++)
                        Pieces.add(new Scout(-1, -1, imagePath + "\\scout.png", 2, isBlue));
                    for (i = 0; i < 2; i++)
                        Pieces.add(new Dwarf(-1, -1, imagePath + "\\dwarf.png", 3, isBlue));
                    for (i = 0; i < 1; i++)
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\elf.png", 4, isBlue));
                    for (i = 0; i < 1; i++)
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\rank5.png", 5, isBlue));
                    for (i = 0; i < 1; i++)
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\sorceress.png", 6, isBlue));
                    for (i = 0; i < 2; i++)
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\beastRider.png", 7, isBlue));
                    for (i = 0; i < 1; i++)
                        Pieces.add(new MovablePiece(-1, -1, imagePath + "\\knight.png", 8, isBlue));
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\mage.png", 9, isBlue));
                    Pieces.add(new MovablePiece(-1, -1, imagePath + "\\dragon.png", 10, isBlue));
                    break;
                default:
                    break;
            }

                for (Piece piece : Pieces) {
                    piece.setHiddenImage(imagePath + "\\Hidden.png");
                }
            } catch (PathNotFoundException e){
                System.out.println(e);
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
        while (iterator.hasNext()) {
            next = rng.next();
            p = iterator.next();
            p.setX(next%10);
            p.setY(next/10);
        }
    }

    /**
     * <b>Accessor</b> Calculates and returns if the player's flag is captured or not
     * @return true if the player can continue playing
     */
    public boolean flagCaptured() {
        return Pieces.get(0).isDead();
    }

    /**
     * <b>Accessor</b> Calculates and returns if the player is defeated or not
     * @return true if the player can continue playing
     */
    public boolean isDefeated() {
        return this.isDefeated;
    }

    /**
     * <b>Transformer:</b> Sets this player as defeated
     */
    public void setDefeated(){
        this.isDefeated = true;
    }


    /**
     * <b>Transformer</b> Used to call the attack method from its pieces
     * <b>pre-condition</b> Both the attacking and defending piece are not dead
     * <b>post-condition</b> One or Both of these Pieces is Defeated
     * @param index d
     */
    public void attacks(int index) {
        captures[index]++;
        succesfulAttacks++;
        attackCount++;
    }


    /**
     * <b>Transformer:</b> Increases counter for which unit was captuerd
     * @param index the rank of captured piece
     */
    public void defends(int index){
        captures[index]++;
    }

    /**
     * <b>Transformer:</b> Increases counter for attacks made
     */
    public void doesAttack(){
        attackCount++;
    }

    /**
     * <b>Accessor:</b> Calculates and returns successful attack ratio
     * @return successful attack ratio
     */
    public float winRate(){
        if (attackCount==0)
            return 0.0f;
        return(succesfulAttacks/(float)attackCount*100);
    }

    /**
     * <b>Transformer:</b> Sets each piece as flipped
     */
    public void flipCards(){
        Iterator<Piece> iterator = Pieces.iterator();
        Piece p;
        while (iterator.hasNext()) {
            p = iterator.next();
            p.setFlipped(true);
        }
    }

    /**
     * <b>Transformer:</b> Sets each piece as not flipped
     */
    public void unflipCards(){
        Iterator<Piece> iterator = Pieces.iterator();
        Piece p;
        while (iterator.hasNext()) {
            p = iterator.next();
            p.setFlipped(false);
        }
    }

    /**
     * <b>Accessor</b> Returns the imagePath for this players folder
     * @return the String containing the path for the images
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * <b>Accessor:</b> Updates Dead Pieces List
     */
    public void setDeadPieces(){
        DeadPieces = Pieces.stream()
                .filter(piece -> piece.isDead() == true)
                .collect(Collectors.toList());
    }

    /**
     * <b>Accessor:</b>
     * @return the List of Dead Pieces this player has
     */
    public List<Piece> getDeadPieces() {
        return DeadPieces;
    }

    /**
     * <b>Accessor:</b>
     * @return the List of Pieces this player has
     */
    public List<Piece> getPieces() {
        return Pieces;
    }

    /**
     * <b>Accessor:</b>
     * @return The array of captures made by this player
     */
    public int[] getCaptures() {
        return captures;
    }

    /**
     * <b>Accessor:</b>
     * @return The name of this player
     */
    public String toString(){
        return  m_name;
    }

    /**
     * <b>Accessor:</b>
     * @return if this the blue player or not
     */
    public boolean isBlue() {
        return isBlue;
    }

    /**
     * <b>Accessor:</b>
     * @return The number of rescues made by this player
     */
    public int getRevival_counter() {
        return revival_counter;
    }

    /**
     * <b>Accessor:</b> Generates a UI for the user to select which available rank to revive (if there are any) and then returns it to the caller
     *
     * @return The piece chosen by the user for revival or null
     */
    public Piece revive(){
        if (revival_counter < 2) {

            Iterator<Piece> iterator = getDeadPieces().iterator();
            if(iterator.hasNext()){
                reviveSelectionFrame rsf = new reviveSelectionFrame(this);
                int selectedRank = rsf.getRank();
                if(selectedRank!=-1){
                    Piece p = null;
                    int i = 0;
                    while (iterator.hasNext()) {
                        p = iterator.next();
                        if (p.getRank() == selectedRank) {
                            p.isRevived();
                            getDeadPieces().remove(i);
                            return p;
                        }
                        i++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * <b>Transformer:</b>Increases rescue counter
     */
    public void increaseRescues(){
        revival_counter++;
    }

    /**
     * <b>Transformer:</b> Decreases captured pieces for given rank when a rescue happens
     * @param index rank of rescued piece
     */
    public void removeCapture(int index){
        captures[index]--;
    }

    /**
     * An inner class responsible for generating a sequence of randomly generated numbers between 1-29 or 50-79 depending on player's side
     */
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

}
