//Chan Hao Kang
//run advancer movement

class AdvancerForwardMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		// passed from movement type
		boolean moveFlag = true;


		if (destCoorX == sourceCoorX && destCoorY == sourceCoorY + 1)
		{
			// 1 means moving 1 forward move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null)
			{
				// check
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		// 2 means moving 2 forward move
		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY + 2)
		{
			// check in between space got obstruction
			if (coordinate[sourceCoorX][sourceCoorY + 1].getChessPiece() == null)
			{
				// check availability
				if (coordinate[sourceCoorX][sourceCoorY + 2].getChessPiece() != null)
				{
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
		
		// movement failed
		else
		{
			return false;
		}

		return moveFlag;
	}
}