//LOW CHI HAN
//This class is used by PlayingFrame class to display the chessboard and the pieces on the chessboard.

import java.awt.*;  
import javax.swing.*;  
import java.awt.image.BufferedImage;  
import java.awt.event.*;
import java.awt.datatransfer.*;

public class Chessboard extends JPanel 
{  
	private ChessTiles[][] tileArray = new ChessTiles[7][7];

    public Chessboard()
    {  
        for (int j = 6; j >= 0; j--)
        {
            for (int i = 0; i < 7; i++)
            {
                tileArray[i][j] = new ChessTiles(i,j);
                tileArray[i][j].setOpaque(true);

                if ( ((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 == 1) && (j % 2 == 1)) )
                {
                    tileArray[i][j].setBackground(Color.BLACK);
                }
                
                add(tileArray[i][j]);
            }
        }
        setLayout(new GridLayout(7,7));
     } 

     //To set chesspiece icon on a particular ChessTile.
     public void setIcon(int x, int y, BufferedImage pieceImage)
     {
        tileArray[x][y].setIcon(new ImageIcon(new ImageIcon(pieceImage).getImage().getScaledInstance(77, 77 , Image.SCALE_DEFAULT)));
     }

     //To clear chesspiece icon on a particular ChessTile.
     public void clearIcon(int x, int y)
     {
        tileArray[x][y].setIcon(null);
     }

     //To add MouseListener to ChessTile.
     public void addChessTileListener(int x, int y, MouseListener dndListener, TransferHandler dndHandler)
     {
        tileArray[x][y].addMouseListener(dndListener);
        tileArray[x][y].setTransferHandler(dndHandler);    
     }
}


