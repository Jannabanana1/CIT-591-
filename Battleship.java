package battleship;

public class Battleship extends Ship {
	/**
	 * length of battleship is set to 4
	 */
	static final int LENGTH = 4;

	/**
	 * ship type is set to battleship
	 */
	static final String SHIP = "battleship";

	/**
	 * creates battleship constructor by calling superclass constructor
	 */
	public Battleship() {
		// the super class's constructor is called and sets length to hard-coded value
		// of 4
		super(Battleship.LENGTH);
	}

	/**
	 * returns ship type
	 */
	@Override
	public String getShipType() {
		// returns ship type
		return Battleship.SHIP;
	}

}
