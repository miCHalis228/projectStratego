package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModSelectionWindow {
    private static final JPanel startingScreen = new JPanel();
    private static final JPanel radioButtons = new JPanel();
    private static final JPanel confirmCancel = new JPanel();
    static JFrame teliko = new JFrame("MOD SELECTION SCREEN");
    private static JButton confirm;
    private static JButton cancel;
    private static JRadioButton reducedArmy;
    private static JRadioButton onlyForword;
    private int m_mode = -1;


    /**
     * <b>Constructor</b>: Constructs a new ModSelectionWindow which creates a dialog box in which
     * the user selects the mods he wants to activate
     * <b>post-conditions</b> The private field m_mode is set and available for reading by the caller
     */
    public ModSelectionWindow() {
//        init();
    }

    public void init() {
        initTeliko(teliko);
        teliko.add(setStartingScreen());
        teliko.pack();
        teliko.setVisible(true);
    }

    public void initTeliko(JFrame teliko) {
        teliko.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        teliko.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setBackground(teliko);
        teliko.add(setTitle());
        teliko.add(setRadioButtons());
        radioButtons.setVisible(false);
        teliko.add(setConfirmButton());
        confirmCancel.setVisible(false);
        teliko.setSize(teliko.getMaximumSize().width, teliko.getMaximumSize().height);
        teliko.setResizable(false);
        teliko.setLocation(0, 0);
        teliko.setExtendedState(JFrame.MAXIMIZED_BOTH);
        teliko.setUndecorated(true);
    }

    public JPanel setStartingScreen() {
        JButton start = new JButton("START");
        JButton exit = new JButton("EXIT");

        startingScreen.setOpaque(false);
        startingScreen.setLayout(new FlowLayout());
        startingScreen.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 70, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 70, 140, 100);


        start.setBackground(Color.BLACK);
        start.setForeground(Color.white);
        start.setOpaque(false);
        start.setFocusable(false);
        start.setFont(new Font("Garamond", Font.BOLD, 40));
        start.setBorder(BorderFactory.createRaisedBevelBorder());

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startingScreen.setVisible(false);
                confirmCancel.setVisible(true);
                radioButtons.setVisible(true);
                teliko.setVisible(true);
            }
        });


        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.setOpaque(false);
        exit.setFocusable(false);
        exit.setFont(new Font("Garamond", Font.BOLD, 30));
        exit.setBorder(BorderFactory.createRaisedBevelBorder());

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startingScreen.add(start);
        startingScreen.add(exit);
        return startingScreen;
    }


    public JPanel setRadioButtons() {
        final String reducedArmyCommand = "Reduced Army",
                onlyForwardCommand = "Only Forward";
        radioButtons.setOpaque(false);
        radioButtons.setLayout(new GridLayout(2, 1));
        radioButtons.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 120, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 50, 300, 100);


        reducedArmy = new JRadioButton("Reduced Army");
        reducedArmy.setActionCommand(reducedArmyCommand);
        reducedArmy.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        reducedArmy.setFocusable(false);
        reducedArmy.setOpaque(false);
        reducedArmy.setFont(new Font("Didot", Font.ITALIC, 36));
        reducedArmy.setForeground(Color.white);
        reducedArmy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();
                if (source.isSelected()) {
                    source.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\tick.jpg"));
                } else {
                    source.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\unchecked.jpg"));
                }
            }
        });
        reducedArmy.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\unchecked.jpg"));

        onlyForword = new JRadioButton("Only Forward");
        onlyForword.setActionCommand(onlyForwardCommand);
        onlyForword.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        onlyForword.setFocusable(false);
        onlyForword.setOpaque(false);
        onlyForword.setFont(new Font("Didot", Font.ITALIC, 36));
        onlyForword.setForeground(Color.white);
        onlyForword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();
                if (source.isSelected()) {
                    source.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\tick.jpg"));
                } else {
                    source.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\unchecked.jpg"));
                }
            }
        });
        onlyForword.setIcon(new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\unchecked.jpg"));

        radioButtons.add(reducedArmy);
        radioButtons.add(onlyForword);

        return radioButtons;
    }

    public JPanel setConfirmButton() {
        final String reducedArmyCommand = "Reduced Army",
                onlyForwardCommand = "Only Forward";

        confirmCancel.setLayout(new FlowLayout());
        confirmCancel.setOpaque(false);
        confirmCancel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100, Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 75, 200, 100);

        confirm = new JButton("Confirm Selection");
        confirm.setBorder(BorderFactory.createRaisedBevelBorder());
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mod1 = reducedArmy.isSelected() ? reducedArmy.getActionCommand() : null, mod2 = onlyForword.isSelected() ? onlyForword.getActionCommand() : null;
                if (mod1 == reducedArmyCommand && mod2 == onlyForwardCommand) {
                    m_mode = 3;
                } else if (mod1 == null && mod2 == onlyForwardCommand) {
                    m_mode = 2;
                } else if (mod1 == reducedArmyCommand && mod2 == null) {
                    m_mode = 1;
                } else if (mod1 == null && mod2 == null) {
                    m_mode = 0;
                }

//                System.exit(0);
                teliko.setVisible(false);
            }
        });

        confirm.setBackground(Color.BLACK);
        confirm.setForeground(Color.darkGray);
        confirm.setOpaque(false);
        confirm.setFocusable(false);
        confirm.setFont(new Font("Didot", Font.BOLD, 22));

        cancel = new JButton("Cancel");
        cancel.setBorder(BorderFactory.createRaisedBevelBorder());
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmCancel.setVisible(false);
                radioButtons.setVisible(false);
                startingScreen.setVisible(true);
                teliko.setVisible(true);
            }
        });

        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.darkGray);
        cancel.setOpaque(false);
        cancel.setFocusable(false);
        cancel.setFont(new Font("Didot", Font.BOLD, 22));

        confirmCancel.add(confirm);
        confirmCancel.add(cancel);
        return confirmCancel;
    }

    public JPanel setTitle() {
        JPanel panel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\stratego_logo256.png");
        JLabel title = new JLabel(imageIcon);
        panel.setOpaque(false);
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 128, 50, 256, 256);
        panel.add(title);
        return panel;
    }

    public void setBackground(JFrame teliko) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\StrategoPhase2\\src\\images\\blue_red_wallpaper.jpg"); // load the image to a imageIcon
        JLabel background = new JLabel(imageIcon, JLabel.CENTER);
        teliko.setContentPane(background);
    }

    /**
     * <b>Accessor</b>: Returns the mode of the game 0,1,2 or 3
     * <b>pre-conditions</b> The game mode is set
     * <b>post-conditions</b> The game mode is sent to View and is between 0-3
     *
     * @return This game's Mode
     */
    public int getMode() {
        return m_mode;
    }

    public void clear() {
        teliko.removeAll();
    }


}