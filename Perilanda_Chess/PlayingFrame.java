//LOW CHI HAN
//This class is used to display the board information such as the Chessboard, Chess pieces, Graveyard pieces, as well as letting the user interact with the GUI through the buttons of PlayingToolbar such as "Save", "Back", "Instruction", and others .

import java.awt.*;  
import javax.swing.*;  
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.*;
import java.util.*;

public class PlayingFrame extends JFrame
{  
	private Chessboard chessBoard = new Chessboard();
    private Graveyard greenGraveyard = new Graveyard("Green graveyard");
    private Graveyard orangeGraveyard = new Graveyard("Orange graveyard");
    private PlayingToolbar playingToolbar = new PlayingToolbar();

    public PlayingFrame()
    {  
        super("Perilanda Chess Offline");


        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(playingToolbar,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 10;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        add(chessBoard,gbc);

        gbc.gridx = 11;
        gbc.gridy = 1;
        gbc.gridheight = 10 ;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        greenGraveyard.setPreferredSize(new Dimension (150, 150));
        add(greenGraveyard,gbc);

        gbc.gridx = 11;
        gbc.gridy = 11;
        gbc.gridheight = 10;
        orangeGraveyard.setPreferredSize(new Dimension (150, 150));
        add(orangeGraveyard,gbc);

	    setSize(1152,648);    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(false);    
     }

    //To set chesspiece icon on a particular ChessTile.
    public void setChessPieceIcon(int x, int y, BufferedImage pieceImage)
    {
     	chessBoard.setIcon(x, y, pieceImage);
    }    

    //To clear chesspiece icon on a particular ChessTile.
    public void clearChessPieceIcon(int x, int y)
    {
        chessBoard.clearIcon(x, y);
    }

    //To set the pieces that are killed in the corresponding Graveyard.
    public void setGraveyardIcon(String pieceColor, ArrayList<Chess> graveyard)
    {
        if (pieceColor.equals("Green"))
        {
            greenGraveyard.setIcon(graveyard);
        }
        else
        {
            orangeGraveyard.setIcon(graveyard);
        }
    }

    //To clear the pieces on the graveyard.
    public void clearGraveyardIcon()
    {
        greenGraveyard.clearIcon();

        orangeGraveyard.clearIcon();
    }

    //To set the number of moves a particular player has made so far in the corresponding GUI component.
    public void setPlayerMoveCount(String playerToMove, int moveCount)
    {
        playingToolbar.setPlayerMoveCount(playerToMove, moveCount);
    }

    //To set the player to make a move in the corresponding GUI component.
    public void setPlayerToMove(String playerToMove)
    {
        playingToolbar.setPlayerToMove(playerToMove);
    }

    //To add MouseListener to ChessTile.
    public void addChessTileListener(int x, int y, MouseListener dndListener, TransferHandler dndHandler)
    {
        chessBoard.addChessTileListener(x, y, dndListener, dndHandler);
    }

    //To add MouseListener to "Back" button.
    public void addBacktoMenuListener(MouseListener backtoMenuListener)
    {
        playingToolbar.addBacktoMenuListener(backtoMenuListener);    
    }

    //To add MouseListener to "Save" button.
    public void addSaveGameListener(MouseListener saveGameListener)
    {
        playingToolbar.addSaveGameListener(saveGameListener);
    }
}  