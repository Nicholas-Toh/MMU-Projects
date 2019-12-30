//Chan Hao Kang
//run excel movement

class ExcelMovement implements MovementType
{
	
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate)
	{
		boolean moveFlag = true;

		int movementDirection;

		if (destCoorX > sourceCoorX && destCoorY > sourceCoorY)
		{
			movementDirection = 1;
		}

		else if (destCoorX > sourceCoorX && destCoorY < sourceCoorY)
		{
			movementDirection = 2;
		}

		else if (destCoorX < sourceCoorX && destCoorY > sourceCoorY)
		{
			movementDirection = 3;
		}

		else if (destCoorX < sourceCoorX && destCoorY < sourceCoorY)
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
				for (int i = sourceCoorX + 1 , j = sourceCoorY + 1 ; i <= destCoorX; i++, j++)
				{
					//check not final des
					if (i != destCoorX && j != destCoorY)
					{
						// check in between got obstruct
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						//if it is final des
						if (i == destCoorX && j == destCoorY)
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

						else
						{
							return false;
						}

					}
					
				}
				break;

			case 2:
				for (int i = sourceCoorX + 1 , j = sourceCoorY - 1 ; i <= destCoorX; i++, j--)
				{
					if (i != destCoorX && j != destCoorY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						if (i == destCoorX && j == destCoorY)
						{
							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
								{
									return false;
								}
							}
						}

						else
						{
							return false;
						}

					}
					
				}
				break;

			case 3:
				for (int i = sourceCoorX - 1 , j = sourceCoorY + 1 ; i >= destCoorX; i--, j++)
				{
					if (i != destCoorX && j != destCoorY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{	
							return false;
						}

						
					}

					else
					{
						if (i == destCoorX && j == destCoorY)
						{

							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
								{
									return false;
								}

							}
							
						}

						else
						{
							return false;
						}

					}
					
				}
				break;

			case 4:
				for (int i = sourceCoorX - 1 , j = sourceCoorY - 1 ; i >= destCoorX; i--, j--)
				{
					if (i != destCoorX && j != destCoorY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						if (i == destCoorX && j == destCoorY)
						{
							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[i][j].getChessPiece().getColor()))
								{
									
									return false;
								}
							}
						}

						else
						{
							return false;
						}

					}
					
				}
				break;
		}

		return moveFlag;
	}
}