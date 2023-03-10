package View;

import Model.Pieces.Piece;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class reviveSelectionFrame extends JFrame{
    String s ;
    String imageURL;
    private int rank;
    private Player m_player;

    /**
     * <b>Constructor: </b> Creates an InputDialogBox to Select the rank of the player which has the ability to rescue
     * @param player with the ability to rescue
     */
    public reviveSelectionFrame(Player player){
        m_player=player;
        Object[] objects = new String[10];
        ArrayList<String> ranksToRevive = new ArrayList<>();
        Iterator<Piece> iterator;
        Piece p;
        iterator = m_player.getDeadPieces().iterator();
        while(iterator.hasNext()){
            p = iterator.next();
            if(p.getRank()!=0 && p.getRank()!=11) {
                objects[p.getRank()-1]=((Integer)p.getRank()).toString();
            }
        }
        for(int i=0;i<10;i++){
            if(objects[i]!=null){
                ranksToRevive.add((String) objects[i]);
            }
        }
        if(m_player.isBlue()){
            imageURL = "C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\blue_checked.jpg";
        } else {
            imageURL = "C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\red_checked.jpg";
        }
        Image img = new ImageIcon(imageURL).getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        assert imageURL != null;
        s = (String)JOptionPane.showInputDialog(
                this,
                "Select a Rank to revive:\n"
                ,
                "Revive a Pawn",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(img),
                ranksToRevive.toArray(),
                "2");

    }

    /**
     * <b>Accessor:</b> Calculates if the choice string is null and returns -1 or the rank selected as a String
     * @return the string selected
     */
    private String choice(){
        if (s==null){
            s="-1";
        }
        return s;
    }

    /**
     * <b>Accessor:</b> Calculates the rank the player selected for rescuing
     * @return The rank selected to be Rescued
     */
    public int getRank(){
        rank = Integer.parseInt(choice());
        return this.rank;
    }
}
