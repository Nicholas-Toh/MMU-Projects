//Chao Hao Kang
class TridentBackwardMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{

		boolean moveFlag = true;

		int movementDirection;

		if (destCoorX > sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 1;
		}

		else if (destCoorX < sourceCoorX && destCoorY == sourceCoorY)
		{
			movementDirection = 2;
		}

		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY - 1)
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
						return false;
					}
				}
				break;
		}

		return moveFlag;
	}
}