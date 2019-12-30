//LOW CHI HAN
//This class is used by Chessboard class to create chesstiles that would be packed together to form a chessboard.

import java.awt.*;  
import javax.swing.*;  
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;  
import java.util.*;
import javax.swing.border.*;

public class Graveyard extends JPanel 
{  
    private JLabel[] graveyardPieces = new JLabel[14];

    public Graveyard(String graveyardName)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        Border innerBorder = BorderFactory.createTitledBorder(graveyardName);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder (BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        
        for (int i = 0; i < 14; i++)
        {
            if (gbc.gridx == 7)
            {
                gbc.gridx = 0;
                gbc.gridy = 1;
            }

            graveyardPieces[i] = new JLabel(); 
            graveyardPieces[i].setOpaque(true);
            add(graveyardPieces[i],gbc);

            gbc.gridx = gbc.gridx + 1;
        }
    }

    //To set graveyard in the GUI according to the graveyard passed from Controller class.
    public void setIcon(ArrayList<Chess> graveyard)
    {
        for (int i = 0; i < graveyard.size(); i++)
        {
            BufferedImage pieceImage = graveyard.get(i).getIcon();
            graveyardPieces[i].setIcon(new ImageIcon(new ImageIcon(pieceImage).getImage().getScaledInstance(33,33 , Image.SCALE_DEFAULT)));
        }
    }

    //To clear all the pieces in the graveyard.
    public void clearIcon()
    {
        for (int i = 0; i < graveyardPieces.length; i++)
        {
            graveyardPieces[i].setIcon(null);
        }
    }

}


