package View;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {
    private JLabel loading;
    private JLabel background;

    /**
     * <b>Constructor:</b> Constructs a LoadingScreen Frame
     */
    public LoadingScreen(){
        /*
            Set Loading Text
         */
        loading = new JLabel("Loading");
        loading.setFont(new Font("Garamond", Font.BOLD, 100));
        loading.setForeground(Color.white);
        loading.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-200, Toolkit.getDefaultToolkit().getScreenSize().height/2-150 ,500,300);
        /*
            Set Background
         */
        ImageIcon imageIcon = new ImageIcon("src/images/blue_red_wallpaper.jpg"); // load the image to a imageIcon
        background = new JLabel(imageIcon, JLabel.CENTER);
        this.setContentPane(background);
        /*
            Set Frame properties
         */
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(this.getMaximumSize().width, this.getMaximumSize().height);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.add(loading);
        this.pack();
    }

    /**
     * Increase Add fullstops at the end for an effect of action
     */
    public void count(){
        loading.setText(loading.getText() + ".");
    }
}
