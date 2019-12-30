//edited by Justin Tey
//This is a class to store the Chess piece and it will also store it position on the board

public class Coordinate
{
	private Chess chessPiece;
	private int coordinateX;
	private int coordinateY;

	//initialize the coordinate with its position on the board and the chess piece it will contain
	Coordinate(int coordinateX, int coordinateY, Chess chessPiece)
	{
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.chessPiece = chessPiece;
	}

	//initialize the coordinate with its position
	Coordinate(int coordinateX, int coordinateY)
	{
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public int getCoorX()
	{
		return coordinateX;
	}

	public int getCoorY()
	{
		return coordinateY;
	}

	public Chess getChessPiece()
	{
		return chessPiece;
	}

	public void setChessPiece(Chess chessPiece)
	{
		this.chessPiece = chessPiece;
	}

}