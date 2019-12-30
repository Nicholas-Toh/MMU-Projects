//LOW CHI HAN
//This class is used to facillitate the communication between Game object, PlayingFrame object, and MainFrame object

import javax.swing.*;  
import java.io.File;  
import java.awt.event.*;
import javax.swing.TransferHandler;
import java.awt.datatransfer.*;

public class Controller {

	private Game game;
	private PlayingFrame playingFrame;
	private MainFrame mainFrame;


	//Drag and drop listener for moving the chess pieces.
	//When a ChessTile is pressed on, the TransferHandler of that ChessTile is acquired, and when exportAsDrag(...) is invoked using said handler,
	//a chain of function defined the TransferHandler class is called automatically which allows for the Drag and Drop operation of the Chess pieces to be done.
	//As the movement pattern of the pieces have to be taken into account for, when moving a Chess piece from a place to another, the relevant functions are overriden 
	//so that the outcome of Drag and Drop operations comply with the game logic(such as not being able to skip over other pieces, moving to the same square, taking piece of same color).
	MouseListener dndListener = new MouseAdapter()
	{
	    @Override
	    public void mousePressed(MouseEvent e)
	    {
	    	ChessTiles c = (ChessTiles) e.getSource();
	        TransferHandler dndHandler = c.getTransferHandler();
	        dndHandler.exportAsDrag(c, e, TransferHandler.MOVE);
	    }
	};

	//MouseListener for "Start Game" button.
	//When the player press "Start Game", the game is created with the default board state.
	MouseListener startGameListener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			game = new Game();
			playingFrame.clearGraveyardIcon();
			setChessPieceIcon();
            setPlayerMoveCount();
            setPlayerToMove();

			playingFrame.setVisible(true);
			mainFrame.setVisible(false);
		}
	};

	//MouseListener for "Load Game" button.
	//When the player press "Load Game", the user can specify the file to load the game state from, into the game attribute of Controller.
	MouseListener loadGameListener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			String fileDir;
			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
			int result = file.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) 
			{
				fileDir = (("" + file.getCurrentDirectory().toString()) + "\\" + file.getSelectedFile().getName());
				game = new Game(new File(fileDir));
				setChessPieceIcon();
	            setPlayerMoveCount();
	            setPlayerToMove();
	            setGraveyardIcon();

				playingFrame.setVisible(true);
				mainFrame.setVisible(false);

			}
		}
	};

	//MouseListener for "Back" button.
	//When the player press "Back", the game is set to null and only redefined later when the user press "Start Game" or "Load Game" button.
	MouseListener backToMenuListener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			playingFrame.setVisible(false);
			mainFrame.setVisible(true);
			game = null;
		}
	};

	//MouseListener for "Save" button.
	//When the player press "Save", the user can dpecific the file to save the game state into.
	MouseListener saveGameListener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			String fileDir;
			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
			int result = file.showSaveDialog(null);

			if(result == JFileChooser.APPROVE_OPTION)
			{
				fileDir = (("" + file.getCurrentDirectory().toString()) + "\\" + file.getSelectedFile().getName());
				game.saveGame(new File (fileDir));
			}
		}
	};

	//Responsible for Drag and Drop operation which involves exporting the "transferables" from the source JComponent and importing them into the destination JComponent.
    private TransferHandler dndHandler = new TransferHandler("icon")
    {
    	ChessTiles source = null;
    	ChessTiles target = null;

		//To specific the Drag and Drop operation invoked is of MOVE type
    	@Override
    	public int getSourceActions (JComponent c)
    	{
    		return MOVE;
    	}

        @Override
	    public void exportAsDrag(JComponent comp, InputEvent e, int action) 
	    {
	    	//To keep track of the source Jcomponent that invoked this function
	    	this.source = (ChessTiles) comp;
	        super.exportAsDrag(comp, e, action);
	    }

	    //Called after the transferable is successfully exported from the source JComponent
      	@Override
	    protected void exportDone(JComponent source, Transferable data, int action)
	    {
	    	//Only when the Drag and Drop action is determined successful, the code inside this if statement get run.
	    	//The four functions called are used to update the changes made in the model class objects to the PlayingFrame UI  
	        if (action == MOVE)
	        {
	            ((ChessTiles) source).setIcon(null);
	            setChessPieceIcon();
	            setGraveyardIcon();
	            setPlayerMoveCount();
	            setPlayerToMove();
	        }
	    }

	    //If super.importData(...) is run, the transferable of the source JComponent is imported into the destination Jcomponent.
	    //Hence, it is necessary to override the function and check if the Drag and Drop action should be permitted based on conditions
	    //such as whether there are pieces in the way of the movement, whether the destination JComponent contains Chess piece of the same color, whether the game has already ended and so on..
	    //Only after the preconditions are met, then only is super.importData(...) run to make the move.
	    //If the preconditions are not met, the operation is deemed a failed one, and as a result nothing is transferred.
        @Override
        public boolean importData(JComponent c, Transferable t) 
        {
        	//At this time of execution, the JComponent c in the parameter has become the destination JComponent where the transferable should be imported into.
        	target = (ChessTiles) c;

        	//Returns the validity of the move based on specific chess movement
        	//When isValidMove(...) returns true, it means the move is deemed valid and the move has already taken place in the model class object, however the Drag and Drop operation has not been completed yet at this time.
        	boolean moveFlag = game.isValidMove(source.getCoorX(), source.getCoorY(), target.getCoorX(), target.getCoorY()); 
			
			//Decide whether to allow the operation based on the boolean value returned to the moveFlag above
        	if (moveFlag)
        	{
        		return super.importData(c, t);
        		
        	}

        	else
        	{
        		return false;
        	}
        }
    };


    //In the driver program, the mainFrame and playingFrame object are constructed and passed into the Controller constructor.
    //Invoking this constructor will lead the user to the Main Menu UI as only the mainFrame is visible at this time.
    //This also set all the listeners to their corresponding components in the UI.
	public Controller(MainFrame mainFrame, PlayingFrame playingFrame)
	{
		this.mainFrame = mainFrame;
		this.playingFrame = playingFrame;
		setListeners();
	}
	
	//To set the current location of the pieces on the ChessBoard
	private void setChessPieceIcon()
	{
		for (int i = 0; i < game.getCoordinate().length; i++)
		{
			for (int j = 0; j < game.getCoordinate()[i].length; j++)
			{
				if (game.getCoordinate()[i][j].getChessPiece() != null)
				{
					playingFrame.setChessPieceIcon(i, j, game.getCoordinate()[i][j].getChessPiece().getIcon());
				}		
				else
				{
					playingFrame.clearChessPieceIcon(i, j);
				}		
			}
		}
	}

	//To set the pieces that are killed in the corresponding GUI component.
	private void setGraveyardIcon()
	{
		playingFrame.setGraveyardIcon("Green", game.getGraveyard("Green"));

		playingFrame.setGraveyardIcon("Orange", game.getGraveyard("Orange"));
	}

	//To add all the listeners defined using various anonymous classes above to the corresponding GUI components(Buttons, labels, ..) 
	private void setListeners()
	{
		playingFrame.addBacktoMenuListener(backToMenuListener);
		playingFrame.addSaveGameListener(saveGameListener);

		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				playingFrame.addChessTileListener(i, j, dndListener, dndHandler);	
			}
		}

		mainFrame.addStartGameListener(startGameListener);
		mainFrame.addLoadGameListener(loadGameListener);
	}

	//To set the number of moves a particular player has made so far in the corresponding GUI component.
	private void setPlayerMoveCount()
	{
		playingFrame.setPlayerMoveCount(game.getPlayerToMove(), game.getMoveCount());
	}

	//To set the player to make a move in the corresponding GUI component.
	private void setPlayerToMove()
	{
		playingFrame.setPlayerToMove(game.getPlayerToMove());
	}

}