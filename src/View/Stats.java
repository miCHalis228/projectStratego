package View;


import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

public class Stats extends JPanel {

    private Player m_player;

    private JPanel labelMods;
    private JPanel labelStats;
    private JPanel labelCaptures;
    
    private JPanel modPanel;
    private JPanel statsPanel;
    private JPanel capturesPanel;
    private JPanel totalCapturesPanel;
    private JPanel revive;

    private JCheckBox l_mod1;
    private JCheckBox l_mod2;
    private  int[] playerCaptures;
    private ImageIcon[] pieceImages;
    private int totalCaptures = 0;

    /**
     * <b>Constructor</b> Constructs a new Stat screen
     * @param player
     */
    public Stats(Player player, int mode){
        this.m_player=player;
        this.l_mod1 = new JCheckBox("Μειωμένος Στρατος", false);
        this.l_mod2 = new JCheckBox("Καμία Υποχώρηση", false);
        this.labelMods = new JPanel();
        this.labelStats = new JPanel();
        this.labelCaptures = new JPanel();
        this.modPanel = new JPanel();
        this.statsPanel = new JPanel();
        this.capturesPanel = new JPanel();
        this.totalCapturesPanel = new JPanel();
        this.revive = new JPanel();
        this.pieceImages = new ImageIcon[12];
        this.playerCaptures = player.getCaptures();
        activeMods(mode);
        Statistics();
        captures();
        setRevivePanel();
    }

    public void addComponents(JFrame frame){
        frame.add(labelMods);
        frame.add(modPanel);
        frame.add(labelStats);
        frame.add(statsPanel);
        frame.add(labelCaptures);
        frame.add(capturesPanel);
        frame.add(totalCapturesPanel);
        frame.add(revive);
    }
    /**
     * <b>Transformer:</b> Generates the ActiveMods JPanel to go into the final JFrame
     * @param mode int between 0-3
     * @return the JPanel generated for the active mods
     */
    private void activeMods(int mode){
        JLabel label = new JLabel("ΕΝΕΡΓΟΙ ΚΑΝΟΝΕΣ");
        label.setFont(new Font("Didot", Font.BOLD, 25));
        labelMods.setLayout(new FlowLayout());
        labelMods.setOpaque(false);
        labelMods.add(label);
        labelMods.setBounds(1150,30,350,50);

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
        l_mod1.setOpaque(false);
        l_mod2.setOpaque(false);
        l_mod1.setEnabled(false);
        l_mod2.setEnabled(false);
        l_mod1.setFont(new Font("Didot", Font.TYPE1_FONT, 18));
        l_mod2.setFont(new Font("Didot", Font.TYPE1_FONT, 18));
        modPanel.setOpaque(false);
        modPanel.setLayout(new GridLayout(2,1,20,0));
        modPanel.setBounds(1220,80,200,100);
        modPanel.add(l_mod1);
        modPanel.add(l_mod2);
    }

    /**
     * <b>Transformer:</b> Generates the Statistics JPanel to go into the final JFrame
     * @return the JPanel generated for the Stats of the m_player
     */
    private  void Statistics(){
        JLabel label = new JLabel("ΣΤΑΤΙΣΤΙΚΑ");
        label.setFont(new Font("Didot", Font.BOLD, 25));
        labelStats.setLayout(new FlowLayout());
        labelStats.setOpaque(false);
        labelStats.add(label);
        labelStats.setBounds(1150,180,350,35);

        statsPanel.setOpaque(false);
        statsPanel.setLayout(new GridLayout(3,1));
        statsPanel.setBounds(1180, 215, 300, 165);
        if(m_player.isBlue()){
            label = new JLabel("Player RED");
            label.setForeground(Color.RED);
        }
        else{
            label = new JLabel("Player BLUE");
            label.setForeground(Color.BLUE);
        }
        label.setFont(new Font("Didot", Font.CENTER_BASELINE, 22));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        statsPanel.add(label);
        label = new JLabel("Ποσοστό επιτ. επίθεσης: " + String.format("%.02f", m_player.winRate()) + "%");
        label.setFont(new Font("Didot", Font.BOLD, 18));
        statsPanel.add(label);
        label = new JLabel("Διασώσεις: " + m_player.getRevival_counter() + "    Γύρος: 0");
        label.setFont(new Font("Didot", Font.BOLD, 18));
        statsPanel.add(label);
    }

    /**
     * <b>Transformer:</b> Generates the Captures JPanel to go into the final JFrame
     * @return the JPanel generated for the Captured Units of the enemy m_player
     */
    private void captures(){
        JLabel label = new JLabel("ΑΙΧΜΑΛΩΤΙΣΕΙΣ");
        label.setFont(new Font("Didot", Font.BOLD, 25));
        labelCaptures.setLayout(new FlowLayout());
        labelCaptures.setOpaque(false);
        labelCaptures.add(label);
        labelCaptures.setBounds(1150,380,350,50);

        capturesPanel.setOpaque(false);
        capturesPanel.setLayout(new GridLayout(4,6));
        capturesPanel.setBounds(1170, 430, 350, 380);

        Image img = new ImageIcon(m_player.getImagePath() + "\\flag.png").getImage();
        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
        pieceImages[0]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[0]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[0]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));


        img = new ImageIcon(m_player.getImagePath() + "\\slayer.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[1]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[1]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[1]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\scout.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[2]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[2]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[2]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\dwarf.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[3]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[3]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[3]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\elf.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[4]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[4]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[4]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\rank5.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[5]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[5]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[5]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\sorceress.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[6]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[6]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[6]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\beastRider.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[7]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[7]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[7]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\knight.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[8]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[8]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[8]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\mage.png").getImage();
        img=img.getScaledInstance(70,85, Image.SCALE_SMOOTH);
        pieceImages[9]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[9]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[9]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\dragon.png").getImage();
        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
        pieceImages[10]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[10]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[10]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        img = new ImageIcon(m_player.getImagePath() + "\\trap.png").getImage();
        img = img.getScaledInstance(70, 85, Image.SCALE_SMOOTH);
        pieceImages[11]=new ImageIcon(img);
        capturesPanel.add(new JLabel(pieceImages[11]));
        capturesPanel.add(new JLabel(" " + (playerCaptures[11]))).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));

        for(int i=0;i<playerCaptures.length;i++){
            totalCaptures+=playerCaptures[i];
        }
        label = new JLabel("Συνολικές Αιχμαλωτίσεις: " + (totalCaptures));
        label.setFont(new Font("Didot", Font.CENTER_BASELINE, 20));
        label.setForeground(Color.white);
        totalCapturesPanel.setLayout(new FlowLayout());
        totalCapturesPanel.setOpaque(false);
        totalCapturesPanel.add(label);
        totalCapturesPanel.setBounds(1150,810,350,50);


    }

    private void setRevivePanel(){
        revive.setOpaque(false);
    }

    /**
     * <b>Accessor</b> Updates and returns the Stat JFrame
     */
    public void update(){
        for(int i=1;i<24;i+=2){
            capturesPanel.remove(i);
            capturesPanel.add(new JLabel(" " + (playerCaptures[(i-1)/2])),i).setFont(new Font("Didot", Font.CENTER_BASELINE, 20));
        }

        statsPanel.remove(1);
        statsPanel.add(new JLabel("Ποσοστό επιτ. επίθεσης: " + String.format("%.02f", m_player.winRate()) + "%"),1).setFont(new Font("Didot", Font.BOLD, 18));
        totalCaptures=0;
        for(int i=0;i<playerCaptures.length;i++){
            totalCaptures+=playerCaptures[i];
        }
        JLabel label = new JLabel("Συνολικές Αιχμαλωτίσεις: " + (totalCaptures));
        label.setFont(new Font("Didot", Font.CENTER_BASELINE, 20));
        label.setForeground(Color.white);
        totalCapturesPanel.remove(0);
        totalCapturesPanel.add(label,0);
    }

    public void nextTurn(int turn){
        statsPanel.remove(2);
        statsPanel.add(new JLabel("Διασώσεις: " + m_player.getRevival_counter() + "    Γύρος: " + turn),2).setFont(new Font("Didot", Font.BOLD, 18));
    }
    public void hideAll(){
        labelMods.setVisible(false);
        labelStats.setVisible(false);
        labelCaptures.setVisible(false);
        modPanel.setVisible(false);
        statsPanel.setVisible(false);
        capturesPanel.setVisible(false);
        totalCapturesPanel.setVisible(false);
        totalCapturesPanel.setVisible(false);
        revive.setVisible(false);
    }
    public void showAll(){
        labelMods.setVisible(true);
        labelStats.setVisible(true);
        labelCaptures.setVisible(true);
        modPanel.setVisible(true);
        statsPanel.setVisible(true);
        capturesPanel.setVisible(true);
        totalCapturesPanel.setVisible(true);
        revive.setVisible(true);
    }

}
