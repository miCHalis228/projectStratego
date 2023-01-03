package View;

import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

public class winningFrame extends JFrame {
    private Player m_player;
    private JButton exit;

    public winningFrame(Player player) {
        this.m_player = player;
        init();
    }

    public void init() {
        this.setLayout(new CardLayout());
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\dragon_background_cropped169.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image), JLabel.CENTER);
        this.setContentPane(label);
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.add(WinningPlayer());
        this.add(exitButton());
        this.pack();
    }

    public JPanel WinningPlayer() {
        JLabel label;
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 50, 400, 100);
        label = new JLabel(m_player.toString().toUpperCase() + " WINS!!");
        label.setFont(new Font("Didot", Font.ITALIC, 60));
        panel.add(label);
        return panel;
    }

    public JPanel exitButton() {
        this.exit = new JButton("EXIT");
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 40, Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 100, 80, 50);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.DARK_GRAY);
        exit.setOpaque(false);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        exit.setFont(new Font("Garamond", Font.BOLD, 30));
        exit.setBorder(BorderFactory.createRaisedBevelBorder());
        exit.addActionListener(e -> System.exit(0));
        panel.add(exit);
        return panel;
    }

}