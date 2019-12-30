//Chan Hao Kang
//run tercel movement

class TercelMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		boolean moveFlag = true;

		int movementDirection;

		// move upwards
		if (destCoorY > sourceCoorY && destCoorX == sourceCoorX)
		{
			movementDirection = 1;
		}

		// moving right
		else if (destCoorX > sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 2;
		}

		// moving left
		else if (destCoorY < sourceCoorY && destCoorX == sourceCoorX)
		{
			movementDirection = 3;
		}

		//moving downwards
		else if (destCoorX < sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 4;
		}

		else
		{
			return false;
		}

		switch(movementDirection)
		{
			case 1: 
				for (int i = sourceCoorX, j = sourceCoorY + 1 ; j <= destCoorY; j++)
				{
					if (j != destCoorY)
					{
						// check in between got obstruct
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						//check desti got obstruct 
						if (coordinate[i][j].getChessPiece() != null )
						{
							if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
							{
								//if got pieces then check colour
								return false;
							}
						}

					}
				}
				break;

			case 2:
				for (int i = sourceCoorX + 1 , j = sourceCoorY ; i <= destCoorX; i++)
				{
					if (i != destCoorX)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{

						if (coordinate[i][j].getChessPiece() != null )
						{
							if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
							{
								return false;
							}
						}

					}
				}
				break;

			case 3:
				for (int i = sourceCoorX, j = sourceCoorY - 1 ; j >= destCoorY; j--)
				{
					if (j != destCoorY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{

						if (coordinate[i][j].getChessPiece() != null )
						{
							if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
							{
								return false;
							}
						}

					}
				}
				break;

			case 4:
				for (int i = sourceCoorX - 1 , j = sourceCoorY; i >= destCoorX; i--)
				{
					if (i != destCoorX)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{

						if (coordinate[i][j].getChessPiece() != null )
						{
							if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
							{
								return false;
							}
						}

					}
				}
				break;
		}

		return moveFlag;
	}
}