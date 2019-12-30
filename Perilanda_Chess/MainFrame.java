//LOW CHI HAN
//This class is used for displaying the Main Menu that the user can interact with through the "Start Game", "Load Game", and "Exit" buttons.

import javax.swing.*;
import java.awt.event.*;  
import javax.swing.border.EmptyBorder;
import java.awt.*; 

public class MainFrame extends JFrame
{  
    private JButton startButton = new JButton("Start Game");
    private JButton loadButton = new JButton("Load Game");
    private JButton exitButton = new JButton("Exit");


    //MouseListener for "Exit" button.
    MouseListener exitGameListener = new MouseAdapter()
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            System.exit(0);
        }
    };

    public MainFrame()
    {  
        super("Perilanda Chess Offline");  

        getRootPane().setBorder(new EmptyBorder(20, 50, 30, 50));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>Main menu</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5,0,5,0);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(startButton, gbc);
        buttons.add(loadButton, gbc);
        buttons.add(exitButton, gbc);


        exitButton.addMouseListener(exitGameListener);
        add(buttons, gbc);
        setSize(544,480);    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true);   
    }

    //To add the MouseListener to "Start Game" button.
    public void addStartGameListener(MouseListener startGamelistener)
    {
       startButton.addMouseListener(startGamelistener);
    }

    //To add the MouseListener to "Load Game" button.
    public void addLoadGameListener(MouseListener loadGameListener)
    {
        loadButton.addMouseListener(loadGameListener);
    }

}  