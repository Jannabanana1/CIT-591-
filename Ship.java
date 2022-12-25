package battleship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public abstract class Ship {

	/**
	 * private variable bowRow is declared for front part of ship
	 */
	private int bowRow;

	/**
	 * private integer bowColumn is declared for front part of ship
	 */
	private int bowColumn;

	/**
	 * the length of ship
	 */
	private int length;

	/**
	 * the boolean value for if ship is horizontal
	 */
	private boolean horizontal;

	/**
	 * creates boolean hit array
	 */
	private boolean[] hit;

	/**
	 * creates ship constructor to indicate length and hit array with boolean values
	 * 
	 * @param length
	 */
	public Ship(int length) {
		// the length is set for ship
		this.length = length;
		// the hit array size is set to the length of the ship
		this.hit = new boolean[this.length];
	}

	/**
	 * method allows access to length of ship
	 * 
	 * @return length of ship
	 */
	public int getLength() {
		return length;
	}

	/**
	 * allows access to front of ship's row value
	 * 
	 * @return bowRow value
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * allows access to the front of ship's column value
	 * 
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * allows access to hit array values
	 * 
	 * @return hit array
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * sets ship to horizontal if true or vertical orientation if false
	 * 
	 * @return horizontal boolean value
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * sets the bow row value
	 * 
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * sets the bow column value
	 * 
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * sets the horizontal boolean value
	 * 
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * creates abstract ship type
	 */
	public abstract String getShipType();

	/**
	 * Places ships at appropriate row and column. Ensures the ship does not extend
	 * past the acceptable array values and does not touch other ships
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return boolean
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// checks if horizontal is false
		if (horizontal == false) {
			// checks if the row falls out of the acceptable indeces (less than 0 or greater
			// than 9)
			if (row - (getLength() - 1) < 0 || row > 9) {
				// returns false if out of bounds
				return false;
				// checks if ship is available vertically, such as not touching any other ship
			} else if (checkAvailabilityVerticalShip(row, column, ocean)) {
				// if so returns true
				return true;
			}
			// checks ship is not horizontal and thus vertical by default
		} else {
			// checks if column is greater than 9 or column is falls out of the bounds past
			// 0.
			if (column - (getLength() - 1) < 0 || column > 9) {
				// returns false if so
				return false;
				// checks if row, column value is available horizontally
			} else if (checkAvailabilityHorizontalShip(row, column, ocean)) {
				// returns true if available
				return true;
			}

		}
		// returns false
		return false;
	}

	/**
	 * checks if ship is available vertically and not touching other ships. checks
	 * the vertical area surrounding where the ship will be placed is free
	 * 
	 * @param row
	 * @param column
	 * @param ocean
	 * @return boolean
	 */
	boolean checkAvailabilityVerticalShip(int row, int column, Ocean ocean) {
		// goes through for loop of column values starting at column before ship to be
		// placed at column value
		// ends at one column past ship's column value
		for (int col = -1; col <= 1; col++) {
			// sets the length and decrements the length by 1 each time
			for (int i = this.getLength(); i >= -1; i--) {
				// goes through try catch block to check if error in code
				try {
					// checks if ship is occupied by decrementing the row and checking if any ships
					// are present
					// checks each of three columns ship is about to be placed at is free
					if (ocean.isOccupied(row - i, column + col)) {
						// returns false if space is not free
						return false;
					}
					// catches the error
				} catch (Exception e) {
					// continues with the for loop if there is an error and continues checking for
					// occupation of ships
					continue;
				}
			}
		}
		// returns true if the conditions are not met for ship occupation and indicates
		// free area for vertical ship
		return true;

	}

	/**
	 * checks if the ship is able to be placed horizontally with free space around
	 * it
	 * 
	 * @param row
	 * @param column
	 * @param ocean
	 * @return boolean
	 */
	boolean checkAvailabilityHorizontalShip(int row, int column, Ocean ocean) {
		// iterates through for loop to check row for horizontal ship placement
		// checks 1 row before ship placement, the row where ship will be placed, and
		// the row after ship placement
		for (int v = -1; v <= 1; v++) {
			// gets length of ship and iterates through it by decrementing each time
			for (int i = this.getLength(); i >= -1; i--) {
				// goes through try error to check errors
				try {
					// checks if ocean is occupied with each iteration's row and column values
					if (ocean.isOccupied(row + v, column - i)) {
						// returns false if so
						return false;
					}
					// catches the exception and continues through next iteration of for loop if
					// error is detected
				} catch (Exception e) {
					continue;
				}
			}
		}
		// returns true if no indication of ship touching ship to be placed in area
		return true;

	}

	/**
	 * sets the bow row, bow column, and horizontal values places the ship at the
	 * given row and column and given horizontal value
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// sets the row of the ship
		this.setBowRow(row);
		// sets the column of the ship
		this.setBowColumn(column);
		// sets the horizontal value
		this.setHorizontal(horizontal);
		// sets the ship array to shipArray variable
		Ship[][] shipArray = ocean.getShipArray();
		// checks if ship is horizontal or not and if it is okay to place the ship at
		// the specific row and column
		if (horizontal == false && okToPlaceShipAt(row, column, horizontal, ocean)) {
			// iterates through loop and sets given ship type to given row and column
			// accordingly to ships length
			for (int i = 0; i < this.getLength(); i++) {
				shipArray[row - i][column] = this;
			}
			// checks if it is okay to place ship at given row and column if ship is
			// vertical
		} else if (okToPlaceShipAt(row, column, horizontal, ocean)) {
			// goes through loop to get length
			for (int i = 0; i < this.getLength(); i++) {
				// sets row, column values on ship array for ship type
				shipArray[row][column - i] = this;
			}
		}

	}

	/**
	 * checks all spaces where ship is occupied with given row and column
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean checkShipOccupied(int row, int column) {
		// sets the number of equal rows expected for the ship type
		int countEqualRow = 0;
		// sets the number of equal columns expected for the ship type
		int countEqualColumn = 0;
		// checks if ship is horizontal or not
		boolean horizontal = this.isHorizontal();
		// stores length of ship
		int length = this.getLength();
		// stores bow row of ship
		int bowR = this.getBowRow();
		// stores bow column of ship
		int bowC = this.getBowColumn();
		// iterates till length of ship
		for (int i = 0; i < length; i++) {
			// goes through try error block
			try {
				// checks if ship is horizontal
				if (horizontal) {
					// checks if column is equal to bow column decrementing by 1 till length of ship
					// also checks bow row is equal to given row
					if (column == bowC - i && bowR == row) {
						// increments count equal column
						countEqualColumn++;
					}
					// checks if ship is not horizontal
				} else {
					// checks if row is equal to bow row decrementing by 1 till length of ship
					// also checks column is equal to ship's bow column value
					if (row == bowR - i && bowC == column) {
						// increments the count equal row
						countEqualRow++;
					}
				}
				// catches the error and continues with the for loop if error detected
			} catch (Exception e) {
				continue;
			}
		}
		// checks if either countEqualRow or countEqualColumn are greater than 0 and
		// returns true
		// indicating ship is occupied at given row, column
		if (countEqualRow > 0 || countEqualColumn > 0) {
			return true;
			// checks if countEqualRow and countEqualColumn are equal to 0 and returns false
		} else {
			// checks ship is not occupied there
			return false;
		}
	}

	/**
	 * checks if we can shoot at certain row and column of ship array by checking
	 * occupation of actual ship and if ship is not sunk
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		// checks if ship is occupied with given row, column and if it is not sunk
		if (this.checkShipOccupied(row, column) == true && isSunk() == false) {
			// sets the horizontal value to whether ship is horizontal or not
			boolean horizontal = this.isHorizontal();
			// gets the bow row and sets to bowR variable
			int bowR = this.getBowRow();
			// gets the column variable for ship array and sets to integer variable
			int bowC = this.getBowColumn();
			// checks if ship is horizontal or not
			if (horizontal) {
				// sets index to ship's bow column and subtracts the given column value to get
				// index of hit array
				int index = bowC - column;
				// sets index of hit array to true
				this.hit[index] = true;
				// condition if ship is not horizontal
			} else {
				// sets index to ship's bow row and subtracts the given row value to get index
				// of hit array
				int index = bowR - row;
				// sets particular index to true
				this.hit[index] = true;
			}
			// returns true since ship can be shot at after meeting conditions
			return true;
		}
		// returns false if ship cannot be shot
		return false;

	}

	/**
	 * checks hit array to see if all values are true for array or if some are false
	 * 
	 * @return boolean
	 */
	boolean isSunk() {
		// iterates through values in hit array
		for (boolean value : hit) {
			// checks if any of the values in the hit array are true or false
			// also checks if the ship type is empty or not
			if (value == false || this.getShipType().equals("empty")) {
				// returns false if any value in hit array has a value of false
				return false;
			}
		}
		// returns true if every value in hit array is true, indicating ship is sunk
		return true;
	}

	@Override
	/**
	 * prints either s for sunk or x for hit when ship is hit and array is printed
	 * out
	 */
	public String toString() {
		// checks if ship is sunk
		if (isSunk()) {
			// returns s when a ship is sunk
			return "s";
			// checks if ship is not sunk
		} else {
			// returns x when ship is hit but not sunk
			return "x";
		}
	}

}
