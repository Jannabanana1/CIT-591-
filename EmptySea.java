package battleship;

public class EmptySea extends Ship {

	/**
	 * the variable is set to a length of 1
	 */
	static final int LENGTH = 1;

	/**
	 * constructor is set with length for ship
	 */
	public EmptySea() {
		// super class is called with length input
		super(EmptySea.LENGTH);
	}

	@Override
	/**
	 * method returns if ship was shot at
	 * 
	 * @param row    of ship
	 * @param column of ship
	 */
	boolean shootAt(int row, int column) {
		// always returns false since empty sea areas are not ships
		return false;
	}

	@Override
	/**
	 * returns true or false for if ship was sunk
	 */
	boolean isSunk() {
		// always returns false since empty sea cannot be sunk
		return false;
	}

	@Override
	/**
	 * returns string to print out for empty sea
	 */
	public String toString() {
		// returns "-" when empty sea locations are shot
		return "-";
	}

	@Override
	/**
	 * returns type of ship
	 */
	public String getShipType() {
		// returns "empty" when trying to get ship type
		return "empty";
	}

}
