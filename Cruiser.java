package battleship;

public class Cruiser extends Ship {
	/**
	 * length of cruiser set to 3;
	 */
	static final int LENGTH = 3;

	/**
	 * type of ship is set to cruiser
	 */
	static final String SHIP = "cruiser";

	/**
	 * creates cruiser constructor by calling super class and sets cruiser to
	 * specific length
	 */
	public Cruiser() {
		// calls the superclass and sets the length
		super(Cruiser.LENGTH);
	}

	@Override
	/**
	 * returns the ship type
	 */
	public String getShipType() {
		// returns the ship type
		return Cruiser.SHIP;
	}

}
