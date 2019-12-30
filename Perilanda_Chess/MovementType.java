//Chan Hao Kang
//To swap to different movement base on the pieces.
//This class along with the Advancer and the Tercel class are an implementation of State Design Pattern.
//In Advancer and Tercel class, swapMovementType() is used to swap the type of movement a Tercel or a Advancer piece would possess.
//When the movementType of an Advancer or a Tercel object changes, the way the piece move by calling move() of MovementType class also changes.

public interface MovementType
{
	// pass to all movement class
	public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate);
}