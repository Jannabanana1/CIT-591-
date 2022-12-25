package battleship;

public class Destroyer extends Ship {
	/**
	 * sets the destroyer length to 2
	 */
	static final int LENGTH = 2;

	/**
	 * sets the ship type to destroyer
	 */
	static final String SHIP = "destroyer";

	/**
	 * sets up constructor by calling superclass constructor and setting length for
	 * ship
	 */
	public Destroyer() {
		// sets length by calling superclass
		super(Destroyer.LENGTH);
	}

	/**
	 * method to returns the ship type
	 */
	@Override
	public String getShipType() {
		// returns type of ship
		return Destroyer.SHIP;
	}

}
