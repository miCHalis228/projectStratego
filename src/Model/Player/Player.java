package Model.Player;

import Model.Coordinates.Coordinates;
import Model.Pieces.*;
import View.reviveSelectionFrame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String m_name;
    private boolean isBlue;
    private int revival_counter;
    private String imagePath;

    private int m_mode;
    private ImageIcon m_HiddenImage;
    private List<Piece> Pieces;
    private List<Piece> DeadPieces;

    private int attackCount;
    private int succesfulAttacks;
    private int turn;

    private int[] captures;
    public Player(String name, int mode) throws IllegalArgumentException {
        attackCount=0;
        succesfulAttacks=0;
        revival_counter=0;
        turn=0;
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
        DeadPieces = new ArrayList<Piece>();
        captures = new int[12];

        Image img = new ImageIcon(new StringBuilder().append(imagePath).append("\\Hidden.png").toString()).getImage();
        img = img.getScaledInstance(105, 95, Image.SCALE_SMOOTH);
        this.m_HiddenImage = new ImageIcon(img);
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
        DeadPieces = new ArrayList<Piece>();
        captures = new int[12];

        Image img = new ImageIcon(imagePath + "\\Hidden.png").getImage();
        img = img.getScaledInstance(105, 95, Image.SCALE_SMOOTH);
        this.m_HiddenImage = new ImageIcon(img);
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
            case 0 , 2:
                Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\flag.png", isBlue ,true ));
                for (i = 0; i < 6; i++)
                    Pieces.add(new ImmovablePiece(-1, -1, imagePath + "\\trap.png" , isBlue , false));
                Pieces.add(new Slayer(-1, -1, imagePath + "\\slayer.png", 1, isBlue));
                for (i = 0; i < 4; i++)
                    Pieces.add(new Scout(-1, -1, imagePath + "\\scout.png", 2, isBlue));
                for (i = 0; i < 5; i++)
                    Pieces.add(new Dwarf(-1, -1, imagePath + "\\dwarf.png", 3, isBlue));
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


                Pieces.stream()
                        .forEach(piece -> piece.setHiddenImage(imagePath  + "\\Hidden.png"));

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
                Pieces.stream()
                        .forEach(piece -> piece.setHiddenImage(imagePath  + "\\Hidden.png"));
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
        while (iterator.hasNext()) {
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
    public ImageIcon getM_HiddenImage() {
        return m_HiddenImage;
    }

    /**
     * <b>Accessor</b> Calculates and returns if the player is defeated or not
     * @return true if the player can continue playing
     */
    public boolean isDefeated() {
        return Pieces.get(0).isDead();
        /*if it has movable pieces and flag is not captured return false*/
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
     * @param index d
     */
    public void attacks(int index) {
        captures[index]++;
        succesfulAttacks++;
        attackCount++;
    }

    public void defends(int index){
        captures[index]++;
    }
    public void doesAttack(){
        attackCount++;
    }

    public float winRate(){
        if (attackCount==0)
            return 0.0f;
        return(succesfulAttacks/(float)attackCount*100);
    }

    public void flipCards(){
        Iterator<Piece> iterator = Pieces.iterator();
        Piece p;
        while (iterator.hasNext()) {
            p = iterator.next();
            p.setFlipped(true);
        }
    }

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

    public void setDeadPieces(){
        DeadPieces = Pieces.stream()
                .filter(piece -> piece.isDead() == true)
                .collect(Collectors.toList());
    }

    public List<Piece> getDeadPieces() {
        return DeadPieces;
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

    public void nextTurn(){
        turn++;
    }

    public int getTurn(){
        return turn;
    }
    public int[] getCaptures() {
        return captures;
    }
    public String toString(){
        return  m_name;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public int getRevival_counter() {
        return revival_counter;
    }
    public Piece revive(){
        if (revival_counter < 2) {
            System.out.println(getDeadPieces());
            System.out.println("select which rank you want to revive");
            getDeadPieces().stream()
                    .forEach(System.out::println);
            Iterator<Piece> iterator = getDeadPieces().iterator();
            if(iterator.hasNext()){
                reviveSelectionFrame rsf = new reviveSelectionFrame(this);
//                rsf.setVisible(true);
//                rsf.toFront();
//                while(rsf.getRank()==-2){
//                    try {
////                        Thread.currentThread().wait(100);
//                        Thread.sleep(100);
////                        rsf.setVisible(true);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                rsf.setVisible(false);
                int selectedRank = rsf.getRank();
//                rsf.dispose();
//                Scanner scanner = new Scanner(System.in);
//                int selectRank = scanner.nextInt();
//                System.out.println("selected: " + selectRank);
                if(selectedRank!=-1){
                    System.out.println(selectedRank);

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
        System.out.println("not possbile");
        return null;
    }

    public void increaseRescues(){
        revival_counter++;
    }
}
