package View;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {
    private JLabel loading;
    private JLabel background;
    private int counter;
    public LoadingScreen(){
        loading = new JLabel("Loading");
        loading.setFont(new Font("Garamond", Font.BOLD, 100));
        loading.setForeground(Color.white);

//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        panel.add(loading);
        ImageIcon imageIcon = new ImageIcon("src/images/blue_red_wallpaper.jpg"); // load the image to a imageIcon
        background = new JLabel(imageIcon, JLabel.CENTER);
        this.setContentPane(background);
//        this.setLayout(new FlowLayout());
        loading.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-200, Toolkit.getDefaultToolkit().getScreenSize().height/2-150 ,500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(this.getMaximumSize().width, this.getMaximumSize().height);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
//        this.add(panel);
        this.add(loading);
//        panel.setBounds(200,200,500,500);
        this.pack();
    }
    public void count(){
        String fullstops;

        loading.setText(loading.getText() + ".");
    }
}
