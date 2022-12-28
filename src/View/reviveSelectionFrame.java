package View;

import Model.Pieces.Piece;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class reviveSelectionFrame extends JFrame{
    String s ;
    String imageURL;
    private int rank;
    private Player m_player;
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
                "Revive a Paen",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(img),
                ranksToRevive.toArray(),
                "2");

    }
    private String choice(){
        if (s==null){
            s="-1";
        }
        return s;
    }
    public void setRank(int rank){
        this.rank=rank;
    }
    public int getRank(){
        rank = Integer.parseInt(choice());
        return this.rank;
    }
}
