//edited by Justin Tey
//This class is the model class for the game to let the controller class to modify the game value and playingframe to project it. The class will include the initialization
// of the board, keeping track on the number of move maked by each user, the player to move, to change the state of some of the pieces like excel , tercel , advancer , Trident
// when it achieve certain point, this class also include the method to save and load the game and flipping the board when its needed.

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Game
{
	private Coordinate coordinate[][] = new Coordinate[7][7];
	private ArrayList<Chess> greenGraveyard = new ArrayList<Chess>();
	private ArrayList<Chess> orangeGraveyard = new ArrayList<Chess>();
	private String playerToMove = "Green";
	private int moveCount = 0;
	private boolean gameStatus = true;

	// when initialize game it will go through the 2 dimension array of coordinate and initialize them with the beginning board position
	public Game()
	{
		coordinate[0][0] = new Coordinate(0,0, new Tercel("Green",false));
		coordinate[0][1] = new Coordinate(0,1, new Advancer("Green",false));
		coordinate[0][2] = new Coordinate(0,2);
		coordinate[0][3] = new Coordinate(0,3);
		coordinate[0][4] = new Coordinate(0,4);
		coordinate[0][5] = new Coordinate(0,5, new Advancer("Orange",true));
		coordinate[0][6] = new Coordinate(0,6, new Tercel("Orange",true));

		coordinate[1][0] = new Coordinate(1,0, new Excel("Green",false));
		coordinate[1][1] = new Coordinate(1,1, new Advancer("Green",false));
		coordinate[1][2] = new Coordinate(1,2);
		coordinate[1][3] = new Coordinate(1,3);
		coordinate[1][4] = new Coordinate(1,4);
		coordinate[1][5] = new Coordinate(1,5, new Advancer("Orange",true));
		coordinate[1][6] = new Coordinate(1,6, new Excel("Orange",true));

		coordinate[2][0] = new Coordinate(2,0, new Trident("Green",false));
		coordinate[2][1] = new Coordinate(2,1, new Advancer("Green",false));
		coordinate[2][2] = new Coordinate(2,2);
		coordinate[2][3] = new Coordinate(2,3);
		coordinate[2][4] = new Coordinate(2,4);
		coordinate[2][5] = new Coordinate(2,5, new Advancer("Orange",true));
		coordinate[2][6] = new Coordinate(2,6, new Trident("Orange",true));

		coordinate[3][0] = new Coordinate(3,0, new Chief("Green",false));
		coordinate[3][1] = new Coordinate(3,1, new Advancer("Green",false));
		coordinate[3][2] = new Coordinate(3,2);
		coordinate[3][3] = new Coordinate(3,3);
		coordinate[3][4] = new Coordinate(3,4);
		coordinate[3][5] = new Coordinate(3,5, new Advancer("Orange",true));
		coordinate[3][6] = new Coordinate(3,6, new Chief("Orange",true));

		coordinate[4][0] = new Coordinate(4,0, new Trident("Green",false));
		coordinate[4][1] = new Coordinate(4,1, new Advancer("Green",false));
		coordinate[4][2] = new Coordinate(4,2);
		coordinate[4][3] = new Coordinate(4,3);
		coordinate[4][4] = new Coordinate(4,4);
		coordinate[4][5] = new Coordinate(4,5, new Advancer("Orange",true));
		coordinate[4][6] = new Coordinate(4,6, new Trident("Orange",true));

		coordinate[5][0] = new Coordinate(5,0, new Excel("Green",false));
		coordinate[5][1] = new Coordinate(5,1, new Advancer("Green",false));
		coordinate[5][2] = new Coordinate(5,2);
		coordinate[5][3] = new Coordinate(5,3);
		coordinate[5][4] = new Coordinate(5,4);
		coordinate[5][5] = new Coordinate(5,5, new Advancer("Orange",true));
		coordinate[5][6] = new Coordinate(5,6, new Excel("Orange",true));

		coordinate[6][0] = new Coordinate(6,0, new Tercel("Green",false));
		coordinate[6][1] = new Coordinate(6,1, new Advancer("Green",false));
		coordinate[6][2] = new Coordinate(6,2);
		coordinate[6][3] = new Coordinate(6,3);
		coordinate[6][4] = new Coordinate(6,4);
		coordinate[6][5] = new Coordinate(6,5, new Advancer("Orange",true));
		coordinate[6][6] = new Coordinate(6,6, new Tercel("Orange",true));
	}

	//this function will initialize the game chess board by reading in the file that will contain the move count of the orange and green player, 
	//the who to move, the game state, all the pieces information for the board and graveyard.
	//The information extracted is their color, the piece name , the coordinates , the flipped state.
	public Game(File savedGameDir)
	{
		Scanner scanner = null;

		try 
		{
			String strCurrentLine;
			String pieceName = null;
			String pieceColor;
			String chessCoordinate;
			String movementType = null;	
			String gameState = null;
			int coordinateX;
			int coordinateY;
			boolean flippedState = true;

			scanner = new Scanner(savedGameDir);

			strCurrentLine = scanner.nextLine();
			strCurrentLine = scanner.next();
			moveCount = Integer.parseInt(scanner.next());

			strCurrentLine = scanner.next();
			playerToMove = scanner.next();

			strCurrentLine = scanner.next();
			strCurrentLine = scanner.next();
			gameState = scanner.next();

			if (gameState.equals("Finished"))
			{
				gameStatus = false;
			}

			strCurrentLine = scanner.nextLine();
			strCurrentLine = scanner.nextLine();

			pieceName = scanner.next();
			pieceColor = scanner.next();
			coordinateX = Integer.parseInt(scanner.next());
			coordinateY = Integer.parseInt(scanner.next());

			if (pieceName.equals("Trident") || pieceName.equals("Advancer"))
			{
				movementType = scanner.next();
				if (movementType == "BackwardMovement")
				{
					flippedState = !flippedState;
				}
			}

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					if (i == coordinateX && j == coordinateY)
					{
						if (pieceName.equals("Trident"))
						{
							coordinate[i][j] = new Coordinate(i, j, new Trident(pieceColor, flippedState, movementType));
						}
						else if (pieceName.equals("Advancer"))
						{
							coordinate[i][j] = new Coordinate(i, j, new Advancer(pieceColor, flippedState, movementType));
						}
						else if (pieceName.equals("Excel"))
						{
							coordinate[i][j] = new Coordinate(i, j, new Excel(pieceColor, flippedState));
						}
						else if (pieceName.equals("Tercel"))	
						{
							coordinate[i][j] = new Coordinate(i, j, new Tercel(pieceColor, flippedState));
						}
						else if (pieceName.equals("Chief"))
						{
							coordinate[i][j] = new Coordinate(i, j, new Chief(pieceColor, flippedState));
						}

						if (!(pieceName.equals("********************Graveyard********************")))
						{
							pieceName = scanner.next();

							if (!(pieceName.equals("********************Graveyard********************")))
							{			
								pieceColor = scanner.next();
								flippedState = !playerToMove.equals(pieceColor);

								coordinateX = Integer.parseInt(scanner.next());
								coordinateY = Integer.parseInt(scanner.next());

								if (pieceName.equals("Trident" )|| pieceName.equals("Advancer"))
								{
									movementType = scanner.next();

									if (movementType.equals("BackwardMovement"))
									{
										flippedState = !flippedState;
									}
								}
							}

						}
					}

					else
					{
						coordinate[i][j] = new Coordinate(i, j);
					}
				}
			}

			while(!(pieceName.equals("***********************End***********************")))
			{
				pieceName = scanner.next();

				if (!pieceName.equals("***********************End***********************"))
				{
					pieceColor = scanner.next();

					if (pieceName.equals("Tercel"))
					{
						getGraveyard(pieceColor).add(new Tercel(pieceColor, false));
					}

					else if (pieceName.equals("Excel"))
					{
						getGraveyard(pieceColor).add(new Excel(pieceColor, false));
					}	

					else if (pieceName.equals("Trident"))
					{
						getGraveyard(pieceColor).add(new Trident(pieceColor, false));
					}

					else if (pieceName.equals("Advancer"))
					{
						getGraveyard(pieceColor).add(new Advancer(pieceColor, false));
					}

					else if (pieceName.equals("Chief"))
					{
						getGraveyard(pieceColor).add(new Chief(pieceColor, false));
					}
				}
			}
		}


		catch (IOException e) 
		{
			e.printStackTrace();
		} 	
  	}

	//save the game by saving the move count of the orange and green, whos turn to move , 
	//and all the pieces information which contain color ,coordination, and movement type and also pieces in the graveyard 
	public void saveGame(File dirToSaveGame)
	{
		BufferedWriter bufferedWriter = null;

		String gameState = null;

		if (gameStatus == false)
		{
			gameState = "Finished";
		}

		else
		{
			gameState = "Ongoing";
		}

		try 
		{
			if (!dirToSaveGame.exists()) 
			{
				dirToSaveGame.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(dirToSaveGame);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("*****************Board Information****************");
			bufferedWriter.newLine();
			bufferedWriter.write("moveCount: " + moveCount);
			bufferedWriter.newLine();
			bufferedWriter.write("whoToMove: " + playerToMove);
			bufferedWriter.newLine();
			bufferedWriter.write("Game status: " + gameState);
			bufferedWriter.newLine();
			bufferedWriter.write("****************Piece Information*****************");
			bufferedWriter.newLine();

			for (int i = 0; i < coordinate.length; i++)
			{
				for (int j = 0; j < coordinate[i].length; j++)
				{
					if (coordinate[i][j].getChessPiece() != null)
					{

						if (coordinate[i][j].getChessPiece() instanceof Advancer || coordinate[i][j].getChessPiece() instanceof Trident )
						{
							bufferedWriter.write(("" + coordinate[i][j].getChessPiece().toString()) + " " + coordinate[i][j].getChessPiece().getColor() + " " + i + " " + j + " " + coordinate[i][j].getChessPiece().movementTypeToString());
							bufferedWriter.newLine();
						}
						else
						{
							bufferedWriter.write(("" + coordinate[i][j].getChessPiece().toString()) + " " + coordinate[i][j].getChessPiece().getColor() + " "  + i + " " + j);
							bufferedWriter.newLine();
						}

					}
				}
			}

			bufferedWriter.write("********************Graveyard********************");
			bufferedWriter.newLine();

			for (int i = 0; i < greenGraveyard.size(); i++)
			{
				bufferedWriter.write("" + greenGraveyard.get(i).toString() + " " + greenGraveyard.get(i).getColor());
				bufferedWriter.newLine();
			}

			for (int i = 0; i < orangeGraveyard.size(); i++)
			{
				bufferedWriter.write("" + orangeGraveyard.get(i).toString() + " " + orangeGraveyard.get(i).getColor());
				bufferedWriter.newLine();
			}

			bufferedWriter.write("***********************End***********************");
		}

		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		
		finally
		{ 
			try
			{
				if(bufferedWriter!=null)
				{
					bufferedWriter.close();
				}
			}
			catch(Exception ex)
			{
			   System.out.println("Error in closing the BufferedWriter"+ex);
			}
		}
	}

	public Coordinate[][] getCoordinate()
	{
		return coordinate;
	}

	public String getPlayerToMove()
	{
		return playerToMove;
	}

	public int getMoveCount()
	{
		return moveCount;
	}

	public ArrayList<Chess> getGraveyard(String graveColor)
	{
		if (graveColor.equals("Green"))
		{
			return greenGraveyard;
		}
		else
		{
			return orangeGraveyard;
		}
	}

	//increase the move count to keep track when to switch the position of excel and tercel
	private void incrementMoveCount()
	{
		if (playerToMove.equals("Green"))
		{
			moveCount++;
		}
	}

	// switch the player to move
	private void switchPlayer()
	{
		if (playerToMove.equals("Green"))
		{
			playerToMove = "Orange";
		}

		else 
		{
			playerToMove = "Green";
		}
	}

	//flip the board by swapping the pieces throughout the board when the turn change from a colr to another
	private void flipBoard()
	{
		Chess placeholderPiece;

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				placeholderPiece = coordinate[i][j].getChessPiece();
				coordinate[i][j].setChessPiece(coordinate[6-i][6-j].getChessPiece());
				coordinate[6-i][6-j].setChessPiece(placeholderPiece);

				if (coordinate[i][j].getChessPiece() != null)
				{
					coordinate[i][j].getChessPiece().flipState();
				}

				if (i == 3 && j == 3)
				{
					break;
				}

				if (coordinate[6-i][6-j].getChessPiece() != null)
				{
					coordinate[6-i][6-j].getChessPiece().flipState();
				}
				
			}
		}
	}

	// add pieces to graveyard if the piece is a chief then declare a winner , or if the board only left chief then draw will declare
	private void addToGraveyard(Chess chessPiece)
	{
		
		if (chessPiece != null)
		{
			if (chessPiece.getFlippedState() == true)
			{
				chessPiece.flipState();
			}

			if (chessPiece.getColor().equals("Green"))
			{
				greenGraveyard.add(chessPiece);
				if (chessPiece.getMovementType() instanceof ChiefMovement)
				{
					new Result("Orange");
					gameStatus = false;
				}
			}

			else
			{
				orangeGraveyard.add(chessPiece);
				if (chessPiece.getMovementType() instanceof ChiefMovement)
				{
					new Result("Green");
					gameStatus = false;
				}
			}
		}

		if (greenGraveyard.size()==13 && orangeGraveyard.size()==13)
		{
			new Result("Draw");
			gameStatus = false;			
		}

	}

	//transform the state of the excel to tercel, and tercel to excel when the turn is 3 and the direction when the advancer or trident reach the end of the board
	private void transformPiece(String pieceColor)
	{
		for (int i = 0; i < coordinate.length; i++)
		{
			for (int j = 0; j < coordinate[i].length; j++)
			{
				if (coordinate[i][j].getChessPiece() != null)
				{

					if (coordinate[i][j].getChessPiece().getColor() == pieceColor)
					{
						if (coordinate[i][j].getChessPiece() instanceof Advancer && j == 6 && coordinate[i][j].getChessPiece().getMovementType() instanceof AdvancerForwardMovement)
						{
							coordinate[i][j].getChessPiece().swapMovementType();
							coordinate[i][j].getChessPiece().flipState();
						}

						else if (coordinate[i][j].getChessPiece() instanceof Advancer && j == 0 && coordinate[i][j].getChessPiece().getMovementType() instanceof AdvancerBackwardMovement)
						{
							coordinate[i][j].getChessPiece().swapMovementType();
							coordinate[i][j].getChessPiece().flipState();
						}

						else if (coordinate[i][j].getChessPiece() instanceof Trident && j == 6 && coordinate[i][j].getChessPiece().getMovementType() instanceof TridentForwardMovement)
						{
							coordinate[i][j].getChessPiece().swapMovementType();
							coordinate[i][j].getChessPiece().flipState();
						}

						else if (coordinate[i][j].getChessPiece() instanceof Trident && j == 0 && coordinate[i][j].getChessPiece().getMovementType() instanceof TridentBackwardMovement)
						{
							coordinate[i][j].getChessPiece().swapMovementType();
							coordinate[i][j].getChessPiece().flipState();
						}

						else if (coordinate[i][j].getChessPiece() instanceof Excel && moveCount >= 3 && moveCount % 3 == 0)
						{
							coordinate[i][j].setChessPiece(new Tercel(pieceColor,false));
						}

						else if (coordinate[i][j].getChessPiece() instanceof Tercel && moveCount >= 3 && moveCount % 3 == 0)
						{
							coordinate[i][j].setChessPiece(new Excel(pieceColor,false));
						}
					}
				}
			}
		}
	}

	//check is a valid move by inputing the initial location of the chess and the destination to the movement type
	public boolean isValidMove(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY)
	{
		if (!coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(playerToMove))
		{
			return false;
		}

		boolean moveFlag = coordinate[sourceCoorX][sourceCoorY].getChessPiece().isMovable(sourceCoorX, sourceCoorY, destCoorX, destCoorY, coordinate) && gameStatus;

		if (moveFlag)
		{
			addToGraveyard(coordinate[destCoorX][destCoorY].getChessPiece());
			coordinate[destCoorX][destCoorY].setChessPiece(coordinate[sourceCoorX][sourceCoorY].getChessPiece());
			coordinate[sourceCoorX][sourceCoorY].setChessPiece(null);

			incrementMoveCount();
			transformPiece(coordinate[destCoorX][destCoorY].getChessPiece().getColor());
			flipBoard();
			switchPlayer();
		}

		return moveFlag;
	}
}