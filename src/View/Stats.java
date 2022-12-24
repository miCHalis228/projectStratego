package View;

import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

public class Stats extends JPanel {
    private Player m_player;
    private JCheckBox l_mod1;
    private JCheckBox l_mod2;
    private boolean mod1;
    private boolean mod2;
    private int win_rate;
    private int revives;
    private int round;
    private int capture1;
    private int capture2;
    private int capture3;
    private int capture4;
    private int capture5;
    private int capture6;
    private int capture7;
    private int capture8;
    private int capture9;
    private int capture10;
    private int totalCaptures;

    /**
     * <b>Constructor</b> Constructs a new Stat screen
     * @param player
     */
    public Stats(Player player, int mode){
        this.m_player=player;
        this.l_mod1 = new JCheckBox();
        this.l_mod2 = new JCheckBox();
        this.add(activeMods(mode));
    }

    /**
     * <b>Transformer:</b> Generates the ActiveMods JPanel to go into the final JFrame
     * @param mode int between 0-3
     * @return the JPanel generated for the active mods
     */
    JPanel activeMods(int mode){
        switch (mode){
            case 0:
                l_mod1.setSelected(false);
                l_mod2.setSelected(false);
                break;
            case 1:
                l_mod1.setSelected(true);
                l_mod2.setSelected(false);
                break;
            case 2:
                l_mod1.setSelected(false);
                l_mod2.setSelected(true);
                break;
            case 3:
                l_mod1.setSelected(true);
                l_mod2.setSelected(true);
                break;
            default:
                break;
        }

        JPanel am = new JPanel(new GridLayout(2,1));
        am.add(l_mod1);
        am.add(l_mod2);
        am.setBounds(0,0,500,250);
    return am;
    }

    /**
     * <b>Transformer:</b> Generates the Statistics JPanel to go into the final JFrame
     * @return the JPanel generated for the Stats of the player
     */
    JPanel Statistics(){
        JPanel m_Statistics = new JPanel(new FlowLayout());
        JPanel tempo = new JPanel(new GridLayout(1,1));
        tempo.setBackground((new Color(128,128,128)));//.setOpaque(false);
        m_Statistics.add((new JLabel("ΣΤΑΤΙΣΤΙΚΑ"))).setFont(new Font("Arial",Font.BOLD,25));
//        m_Statistics
        return m_Statistics;
    }

    /**
     * <b>Transformer:</b> Generates the Captures JPanel to go into the final JFrame
     * @param player the player to get the list of enemy captured pieces
     * @return the JPanel generated for the Captured Units of the enemy Player
     */
    JPanel captures(Player player){
        JPanel toReturn = new JPanel(new GridLayout(3,1,0,0));
        toReturn.add((new JLabel("ΑΙΧΜΑΛΩΤΙΣΕΙΣ"))).setFont(new Font("Arial",Font.BOLD,25));

        JPanel m_captures = new JPanel();
        m_captures.setBackground((new Color(128,128,128)));
//        m_captures.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        m_captures.setSize(new Dimension(400,500));
        m_captures.setLayout(new GridLayout(3,6));
        Image img = new ImageIcon(player.getImagePath() + "\\slayer.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture1)));

        img = new ImageIcon(player.getImagePath() + "\\scout.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture2)));

        img = new ImageIcon(player.getImagePath() + "\\dwarf.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture3)));

        img = new ImageIcon(player.getImagePath() + "\\elf.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture4)));

        img = new ImageIcon(player.getImagePath() + "\\rank5.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture5)));

        img = new ImageIcon(player.getImagePath() + "\\sorceress.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture6)));

        img = new ImageIcon(player.getImagePath() + "\\beastRider.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture7)));

        img = new ImageIcon(player.getImagePath() + "\\knight.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture8)));

        img = new ImageIcon(player.getImagePath() + "\\mage.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        m_captures.add(new JLabel(new ImageIcon(img)));
        m_captures.add(new JLabel(String.valueOf(capture9)));

        toReturn.add(m_captures);
        img = new ImageIcon(player.getImagePath() + "\\dragon.png").getImage();
        img=img.getScaledInstance(35,35, Image.SCALE_SMOOTH);
        JLabel jLabel= new JLabel(new ImageIcon(img));

        toReturn.add(jLabel);

        return toReturn;
    }

    /**
     * <b>Accessor</b> Updates and returns the Stat JFrame
     * @param player the players stat frame
     * @return the updated JFrame
     */
    public JFrame update(Player player){
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Stats s = new Stats(new Player("Blue"),0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(s);
        frame.pack();
        frame.setVisible(true);


    }
}
