//LOW CHI HAN
//This class is used by PlayingFrame class as a toolbar component that the user can interact with, through the "Back", "Save", "Instruction" buttons defined here.

import java.awt.*;  
import javax.swing.*;  
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;  

public class PlayingToolbar extends JPanel 
{
    private JButton backButton = new JButton("Back");
    private JButton saveButton = new JButton("Save");
    private JButton instructionButton = new JButton("Instruction");
    private JLabel greenMoves = new JLabel();
    private JLabel orangeMoves = new JLabel();;
    private JLabel playerToMove = new JLabel();;

    //MouseListener for "Instruction" button
    MouseListener instructionListener = new MouseAdapter()
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            JDialog dialog = new JDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
           try
            { dialog.add(new JLabel(new
            ImageIcon(ImageIO.read(getClass().getResourceAsStream("src/help.png")))));
            }
            catch (IOException ex)
            { dialog.add(new JLabel("Couldn't load the image"));
            }
            dialog.pack();
            dialog.setLocationByPlatform(true);
            dialog.setVisible(true);
        }
    };

	public PlayingToolbar()
	{
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1;
            gbc.weightx = 1;
            gbc.insets = new Insets(0,5,0,5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(backButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(saveButton, gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            add(instructionButton, gbc);

            gbc.weightx = 0.1;
            gbc.weighty = 1;
            gbc.gridx = 3;
            gbc.gridy = 0; 
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(0,2,0,2);
            add(greenMoves, gbc);


            gbc.gridx = 8;
            gbc.gridy = 0; 
            add(orangeMoves, gbc);


            gbc.gridx = 10;
            gbc.gridy = 0; 
            add(playerToMove, gbc);

            setOpaque(true);

            instructionButton.addMouseListener(instructionListener);
	}

    //To add MouseListener to "Back" button.
    public void addBacktoMenuListener(MouseListener backtoMenuListener)
    {
        backButton.addMouseListener(backtoMenuListener);    
    }

    //To add MouseListener to "Save" button.
    public void addSaveGameListener(MouseListener saveGameListener)
    {
        saveButton.addMouseListener(saveGameListener);
    }

    //To set the number of moves a particular player has made so far in the corresponding GUI component.
    public void setPlayerMoveCount(String playerToMove, int moveCount)
    {
        if (playerToMove.equals("Orange"))
        {
            greenMoves.setText("Green moves: " + Integer.toString(moveCount));
            orangeMoves.setText("Orange moves: " + Integer.toString(moveCount - 1));
        }

        else
        {
            greenMoves.setText("Green moves: " + Integer.toString(moveCount));
            orangeMoves.setText("Orange moves: " + Integer.toString(moveCount));
        }
    }

    //To set the player to make a move in the corresponding GUI component
    public void setPlayerToMove(String playerToMove)
    {
       this.playerToMove.setText("Player to move: " + playerToMove);
    }
}