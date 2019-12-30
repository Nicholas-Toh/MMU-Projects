//Chan Hao Kang
//run trident movement

class TridentForwardMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{

		boolean moveFlag = true;

		int movementDirection;

		//move right
		if (destCoorX > sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 1;
		}

		// move left
		else if (destCoorX < sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 2;
		}

		// move upwards
		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY + 1)
		{
			movementDirection = 3;
		}

		else
		{
			return false;
		}

		switch(movementDirection)
		{
			case 1: 
				for (int i = sourceCoorX + 1, j = sourceCoorY; i <= destCoorX; i++)
				{
					if (i != destCoorX)
					{
						// check in between got obstruct
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						// check desti got obstruct or not
						if (coordinate[i][j].getChessPiece() != null )
						{
							//if got pieces then check colour
							if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
							{
								return false;
							}
						}

					}
				}
				break;

			case 2:
				for (int i = sourceCoorX - 1, j = sourceCoorY; i >= destCoorX; i--)
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
				if (coordinate[destCoorX][destCoorY].getChessPiece() != null)
				{
					if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
					{
						//if yes check for colour if same colour return false
						return false;
					}
				}
				break;
		}

		return moveFlag;
	}
}