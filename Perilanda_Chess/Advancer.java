//edited by Justin Tey
//This is a class for a chess piece name Advancer that inherit from chess 

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Advancer extends Chess
{
	private BufferedImage forwardPieceImage;
	private BufferedImage backwardPieceImage;
	private MovementType movementType;

	Advancer(String pieceColor, boolean flippedState)
	{
		super(pieceColor,flippedState);

		//set the icon of the object
		setIcon();

		//assign the movement to the object
		movementType = new AdvancerForwardMovement();
	}

	// this constructor will be called when we initialize Trident from a saved file
	Advancer(String pieceColor, boolean flippedState, String movementType)
	{
		super(pieceColor,flippedState);

		setIcon();

		if (movementType.equals("ForwardMovement"))
		{
			this.movementType = new AdvancerForwardMovement();
		}
		else
		{
			this.movementType = new AdvancerBackwardMovement();
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
		//to return the movement type
		return movementType;
	}

	//for advancer and trident they will turn around when they reach the end this part will set their movement type for that occasion
	public void swapMovementType()
	{
		if (movementType instanceof AdvancerForwardMovement)
		{
			movementType = new AdvancerBackwardMovement();	
		} 
		else
		{
			movementType = new AdvancerForwardMovement();
		}
	}

	//set the icon by getting the image from src folder
	public void setIcon()
	{
		try
		{	
			File forwardPieceImageFile;
			File backwardPieceImageFile;
			int width = 20;
			int height = 20;

			if (getColor().equals("Green"))
			{
				forwardPieceImageFile = new File("src/piece_img/advancer_forward_green.png");
				backwardPieceImageFile = new File("src/piece_img/advancer_backward_green.png");
			}
			else 
			{
				forwardPieceImageFile = new File("src/piece_img/advancer_forward_orange.png");
				backwardPieceImageFile = new File("src/piece_img/advancer_backward_orange.png");
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

	//check is a valid move
	public boolean isMovable(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		return movementType.move(sourceCoorX, sourceCoorY, destCoorX, destCoorY, coordinate);
	}

	// make the type of piece to string for saving
	public String toString() 
	{
		return "Advancer";
	}

	// make the movement type to string for saving
	public String movementTypeToString() 
	{
		if (movementType instanceof AdvancerForwardMovement)
		{
			return "ForwardMovement";	
		}

		else
		{
			return "BackwardMovement";
		}
	}
}
	