import Model.Pieces.Piece;
import Model.Player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        ImageIcon[] pieceImages = new ImageIcon[10];
        int xpos =500, ypos =500;
        Player m_player = new Player("Blue",0);
        JFrame frame = new JFrame();
        frame.setTitle("Fruits");

        JPanel capturesPanel = new JPanel();
        capturesPanel.setOpaque(false);
        capturesPanel.setLayout(new GridLayout(2,6));

//        Image img = new ImageIcon(m_player.getImagePath() + "\\flag.png").getImage();
//        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
//        capturesPanel.add(new JButton(new ImageIcon(img)));

        Image img = new ImageIcon(m_player.getImagePath() + "\\slayer.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[0]=new ImageIcon(img);


        img = new ImageIcon(m_player.getImagePath() + "\\scout.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[1]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\dwarf.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[2]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\elf.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[3]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\rank5.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[4]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\sorceress.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[5]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\beastRider.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[6]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\knight.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[7]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\mage.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[8]=new ImageIcon(img);

        img = new ImageIcon(m_player.getImagePath() + "\\dragon.png").getImage();
        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
        pieceImages[9]=new ImageIcon(img);

//        img = new ImageIcon(m_player.getImagePath() + "\\trap.png").getImage();
//        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
//        capturesPanel.add(new JButton(new ImageIcon(img)));
//        pieceImages[11]=new ImageIcon(img);

        capturesPanel.setBounds(0,0, 420, 170);
//        capturesPanel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/4-175,Toolkit.getDefaultToolkit().getScreenSize().height/4-85, 350, 170);

        frame.setLayout(new CardLayout());
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\dragon_background_cropped169.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage().getScaledInstance(420,170, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBackground(Color.BLACK);
        label.setForeground(Color.BLACK);
        frame.setContentPane(label);
        frame.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(xpos,ypos);
        m_player.randomizePositions();

        Iterator<Piece> iterator = m_player.getPieces().iterator();
        int i=0;
        Piece p;
        while(iterator.hasNext()){
            p = iterator.next();
            if(i%3==0){
                p.setDead();
            }
        }

        int array[] = new int[12];
        iterator = m_player.getPieces().iterator();
        while(iterator.hasNext()){
            p = iterator.next();
            if(p.getRank()!=0 && p.getRank()!=11)
                array[p.getRank()-1]++;
        }
//        array[4]=0;
        JButton button = null;
        for(i=0;i<10;i++){
            if(array[i]!=0){
                button=new JButton(pieceImages[i]);
                assert button != null;
                button.addActionListener(new myActionListener(i+1));
                capturesPanel.add(button);
            }
        }
        img = new ImageIcon("src\\images\\redX.png.jpg").getImage();
        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
        button=new JButton(new ImageIcon(img));
        assert button != null;
        button.addActionListener(new myActionListener(-1));
        capturesPanel.add(button);
        frame.add(capturesPanel);
        frame.pack();
        frame.setVisible(true);



    }

}
class myActionListener implements ActionListener{

    int Rank;
    myActionListener(int rank){
        Rank=rank;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(Rank);
    }
}
