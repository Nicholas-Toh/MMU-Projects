//edited by Justin Tey
//This is a class for a chess piece name Tercel that inherit from chess 

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Tercel extends Chess 
{
	private BufferedImage pieceImage;
	private MovementType movementType;

	Tercel(String pieceColor, boolean flippedState)
	{
		super(pieceColor, flippedState);

		//set the icon of the object
		setIcon();

		//assign the movement to the object
		movementType = new TercelMovement();
	}

	public BufferedImage getIcon()
	{
		return pieceImage;
	}

	public MovementType getMovementType()
	{
		return movementType;
	}

	public void swapMovementType()
	{
		
	}

	public void setIcon()
	{
		try
		{	
			File imageFile;
			int width = 20;
			int height = 20;

			if (getColor().equals("Green"))
			{
				imageFile = new File("src/piece_img/tercel_green.png");
			}
			else 
			{
				imageFile = new File("src/piece_img/tercel_orange.png");
			}

			pieceImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
			pieceImage = ImageIO.read(imageFile);
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
		return "Tercel";
	}

	public String movementTypeToString()
	{
		return "TercelMovement";
	}
}