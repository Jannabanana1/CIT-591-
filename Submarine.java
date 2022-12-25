package battleship;

public class Submarine extends Ship {
	
	/**
	 * sets the length of the ship to 1;
	 */
	static final int LENGTH = 1;

	/**
	 * sets the type of ship to submarine
	 */
	static final String SHIP = "submarine";

	/**
	 * constructs the submarine constructor and sets length
	 */
	public Submarine() {
		// calls super class constructor and sets length to hard-coded value of 1
		super(Submarine.LENGTH);
	}

	@Override
	/**
	 * Allows the string representation of submarine 
	 * This may be "s" if sunk or "x" if hit
	 */
	public String getShipType() {
		// returns the ship type
		return Submarine.SHIP;
	}

}
