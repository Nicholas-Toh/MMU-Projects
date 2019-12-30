//Chan Hao Kang
//run advancer movement

class AdvancerBackwardMovement implements MovementType
{
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		boolean moveFlag = true;

		// moving 1 space backwards check
		if (destCoorX == sourceCoorX && destCoorY == sourceCoorY - 1)
		{
			// destination not empty
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				//check same colour or not
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))	
				{
					return false;
				}
			}
		}

		// moving 2 space backwards check
		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY - 2)
		{
			// if between source and desti no obstruction
			if (coordinate[sourceCoorX][sourceCoorY - 1].getChessPiece() == null)
			{
				// if desti got pieces
				if (coordinate[sourceCoorX][sourceCoorY - 2].getChessPiece() != null)
				{
					//check colour
					if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))	
					{
						return false;
					}
				}
			}

			// if the between got obstruction return false
			else
			{
				return false;
			}
		}
		
		else
		{
			return false;
		}

		
		return moveFlag;
	}
}