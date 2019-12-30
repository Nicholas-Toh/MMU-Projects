//edited by Justin Tey
// This is an abstract class for chess piece to inherit from as there are 5 types of different chess piece.

import java.awt.image.BufferedImage;

abstract public class Chess 
{
	//to describe the color of the chess
	private String pieceColor;
	//to determine whether is the chess is flipped
	private boolean flippedState;

	//default constructor to assign piece color and state
	Chess(String pieceColor, boolean flippedState)
	{
		this.pieceColor = pieceColor;
		this.flippedState = flippedState;
	}

	public String getColor()
	{
		return pieceColor;
	}

	public boolean getFlippedState()
	{
		return flippedState;
	}

	public void flipState()
	{
		flippedState = !flippedState;
	}

	abstract public BufferedImage getIcon();

	abstract public MovementType getMovementType();

	//swap the movement type when certain condition achieve
	abstract public void swapMovementType();

	abstract public void setIcon();

	abstract public boolean isMovable(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate);

	abstract public String toString();

	abstract public String movementTypeToString();

}

