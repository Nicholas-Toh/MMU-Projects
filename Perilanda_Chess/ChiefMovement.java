//Chan Hao Kang
//run chief movement

class ChiefMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		boolean moveFlag = true;

		//move forward
		if (destCoorX == sourceCoorX && destCoorY == sourceCoorY + 1)
		{	
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}


		//move backward
		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY - 1)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move right
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move left
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move slant upwards left
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY + 1)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move slant upwards right
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY + 1)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move slant downwards right
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY - 1)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		//move slant downwards left
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY - 1)
		{
			//check destination got piecess
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}
		}

		else
		{
			return false;
		}

		return moveFlag;
	}
}