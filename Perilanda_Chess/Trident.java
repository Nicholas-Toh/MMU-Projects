//edited by Justin Tey
//This is a class for a chess piece name Trident that inherit from chess 

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Trident extends Chess 
{
	private BufferedImage forwardPieceImage;
	private BufferedImage backwardPieceImage;
	private MovementType movementType;

	Trident(String pieceColor, boolean flippedState)
	{
		super(pieceColor,flippedState);

		//set the icon of the object
		setIcon();

		//assign the movement to the object
		movementType = new TridentForwardMovement();
	}

	// this constructor will be called when we initialize Trident from a saved file
	Trident (String pieceColor, boolean flippedState, String movementType)
	{
		super(pieceColor,flippedState);

		setIcon();

		if (movementType.equals("ForwardMovement"))
		{
			this.movementType = new TridentForwardMovement();
		}

		else
		{
			this.movementType = new TridentBackwardMovement();
		}
	}

	public BufferedImage getIcon()
	{
		if (getFlippedState() == true)
		{
			return backwardPieceImage;
		}
		else
		{
			return forwardPieceImage;	
		}
	}

	public MovementType getMovementType()
	{
		return movementType;
	}

	//for advancer and trident they will turn around when they reach the end this part will set their movement type for that occasion
	public void swapMovementType()
	{
		if (movementType instanceof TridentForwardMovement)
		{
			movementType = new TridentBackwardMovement();	
		} 
		else
		{
			movementType = new TridentForwardMovement();
		}
	}

	public void setIcon()
	{
		try
		{
			File forwardPieceImageFile;
			File backwardPieceImageFile;
			File imageFile;
			int width = 20;
			int height = 20;

			if (getColor().equals("Green"))
			{
				forwardPieceImageFile = new File("src/piece_img/trident_forward_green.png");
				backwardPieceImageFile = new File("src/piece_img/trident_backward_green.png");
			}

			else 
			{
				forwardPieceImageFile = new File("src/piece_img/trident_forward_orange.png");
				backwardPieceImageFile = new File("src/piece_img/trident_backward_orange.png");
			}

			forwardPieceImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
			forwardPieceImage = ImageIO.read(forwardPieceImageFile);
			backwardPieceImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
			backwardPieceImage = ImageIO.read(backwardPieceImageFile);
		}

		catch(IOException e)
		{
			System.out.println("Error: "+e);
		}
	}

	public boolean isMovable(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		return movementType.move(sourceCoorX, sourceCoorY, destCoorX, destCoorY, coordinate);
	}

	public String toString() 
	{
		return "Trident";
	}

	public String movementTypeToString()
	{
		if (movementType instanceof TridentForwardMovement)
		{
			return "ForwardMovement";	
		}

		else
		{
			return "BackwardMovement";
		}
		
	}

}