package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ocean {

	/**
	 * number of total shots fired
	 */
	private int shotsFired;

	/**
	 * number of times ships were hit that were not sunk already
	 */
	private int hitCount;

	/**
	 * the number of ships sunk
	 */
	private int shipsSunk;

	/**
	 * creates a 10 x 10 array of ships
	 */
	private Ship[][] ships = new Ship[10][10];

	/**
	 * creates a 10 x 10 array with boolean values checking to see if it was fired
	 * and ship was shot at
	 */
	private boolean[][] firedAndHit = new boolean[10][10];

	/**
	 * creates a 10 x 10 array with boolean values checking to see if it was an
	 * empty hit or not
	 */
	private boolean[][] emptyHit = new boolean[10][10];

	/**
	 * creates a list of ships with a total of 10 distinct ships.
	 */
	Ship[] shipList = { new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), new Destroyer(),
			new Destroyer(), new Submarine(), new Submarine(), new Submarine(), new Submarine() };

	/**
	 * initializes an ocean with each area of the ocean having an empty sea
	 */
	public Ocean() {
		// initializes the number of shots, hit count, and ships sunk to zero
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		// iterates through the 10 x 10 array for the ocean
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				// sets each individual row and column in array to empty sea value
				ships[row][column] = new EmptySea();
				// places ship at proper row, column
				ships[row][column].placeShipAt(row, column, true, this);

			}
		}

	}

	/**
	 * places all 10 ships randomly
	 */
	void placeAllShipsRandomly() {
		// creates a boolean array with a true and a false value
		boolean[] s = { true, false };
		// creates a new random variable
		Random rand = new Random();
		// iterate through the shipList
		for (int i = 0; i < shipList.length; i++) {
			// store each ship in a Ship variable as iterating through ship list
			Ship shipItem = shipList[i];
			// set run variable to true
			boolean run = true;
			// run while loop while run = true
			while (run) {
				// set boolean horizontal to true or false selecting one of those options from s
				// array randomly
				boolean horizontal = s[rand.nextInt(s.length)];
				// set row randomly by picking a number 0-9
				int randRow = rand.nextInt(9);
				// set column value randomly with numbers 0-9
				int randCol = rand.nextInt(9);
				// check if it is okay to place ship at random column and row chosen
				if (shipItem.okToPlaceShipAt(randRow, randCol, horizontal, this)) {
					// if okay to place, places ship at selected row, column
					shipItem.placeShipAt(randRow, randCol, horizontal, this);
					// run is set to false and breaks out of while loop
					// random row,column and horizontal boolean value no longer chosen for
					// particular ship
					run = false;
					// if ship not okay to place, run is set to true
					// random rows,columns, and horizontal values continue to be chosen till ship
					// may be placed acceptably
				} else {
					run = true;
				}
			}

		}

	}

	/**
	 * provides access to 10 x 10 array of ship placement values
	 * 
	 * @return ships
	 */
	public Ship[][] getShipArray() {
		// TODO Auto-generated method stub
		return ships;
	}

	/**
	 * checks if row, column value is occupied by a ship or is empty
	 * 
	 * @param row
	 * @param column
	 * @return boolean occupation of ship
	 */
	boolean isOccupied(int row, int column) {
		// checks if row, column value is empty type or actual ship
		if (ships[row][column].getShipType().equals("empty")) {
			// returns false if empty
			return false;
			// checks if row, column occupied with actual ship
		} else {
			// returns true if so
			return true;
		}
	}

	/**
	 * provides access to number of times actual ship is hit
	 * 
	 * @return
	 */
	int getHitCount() {
		return hitCount;
	}

	/**
	 * Checks if a ship (not sunk) was successfully shot at. Updates the number of
	 * shots fired. Updates the number of hits made to ships and sets fired and hit
	 * array to true. Updates the number of ships sunk.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		// increments the number of total shots fired during game
		shotsFired++;
		// accesses ship array and stores it in ship array variable
		Ship[][] ship = this.getShipArray();
		// checks if ship was actually shot at with given row, column value
		if (ship[row][column].shootAt(row, column)) {
			// sets firedAndHit array to true at specific row, column
			firedAndHit[row][column] = true;
			// increments hit count
			hitCount++;
			// checks if ship is sunk after being hit
			if (ship[row][column].isSunk()) {
				// increments number of ships sunk
				shipsSunk++;
			}
			// returns true
			return true;
			// checks if ship is not occupied
		} else if (!isOccupied(row, column)) {
			// updates the row, column value to true in emptyhit array if ship does not
			// occupy area
			emptyHit[row][column] = true;
			// returns false if area is empty
			return false;
			// if neither shot at or empty, returns false
			// may return false here due to ship being sunk
		} else {
			return false;
		}
	}

	/**
	 * prints out the 10 x 10 ocean array as well as s when a ship is sunk, x when
	 * hit, and "." if not hit.
	 */
	void print() {
		System.out.print(" ");
		// prints out numbers 0-9 across top row of ocean array to indicate row number
		for (int i = 0; i < 10; i++) {
			System.out.print(" " + i);
		}
		System.out.println("");
		// iterates through for loop and prints the column number
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
			// iterates through for loop
			for (int j = 0; j < 10; j++) {
				// checks if ship is sunk or not
				if (ships[i][j].isSunk()) {
					// prints out s for sunk if ship is sunk
					System.out.print("s ");
					// checks if the firedAndHit array is true to indicate hitting ship
				} else if (firedAndHit[i][j]) {
					// prints out 'x'
					System.out.print("x ");
					// checks if empty space on array and it is hit
				} else if (emptyHit[i][j]) {
					// prints out the "-" character if it hits an empty space on the ocean
					System.out.print(ships[i][j] + " ");
					// if none of the conditions are met, "." is printed
				} else {
					System.out.print(". ");
				}

			}
			System.out.println(" ");
		}
	}

	//	void printWithShips() {
	//		System.out.print(" ");
	//		for (int i = 0; i < 10; i++) {
	//			System.out.print(" " + i);
	//		}
	//		System.out.println("");
	//
	//		for (int i = 0; i < 10; i++) {
	//			System.out.print(i + " ");
	//			for (int j = 0; j < 10; j++) {
	//				if (ships[i][j].getShipType().equals("battleship")) {
	//					System.out.print("b ");
	//				} else if (ships[i][j].getShipType().equals("destroyer")) {
	//					System.out.print("d ");
	//				} else if (ships[i][j].getShipType().equals("cruiser")) {
	//					System.out.print("c ");
	//				} else if (ships[i][j].getShipType().equals("submarine")) {
	//					System.out.print("s ");
	//				} else {
	//					System.out.print("  ");
	//				}
	//			}
	//			System.out.println(" ");
	//		}
	//	}

	/**
	 * allows access to the private variable shotsFired to indicate total shots
	 * fired during game
	 * 
	 * @return total shotsFired
	 */
	int getShotsFired() {
		// returns total shots fired
		return shotsFired;
	}

	/**
	 * allows access to the ships sunk variable to get total ships sunk
	 * 
	 * @return number of ships sunk
	 */
	int getShipsSunk() {
		// returns total ships sunk
		return shipsSunk;
	}

	/**
	 * checks if the game is over by checking if all 10 ships were sunk
	 * 
	 * @return boolean
	 */
	boolean isGameOver() {
		// checks if ships sunk is equal to 10 and returns true
		if (getShipsSunk() == 10) {
			return true;
			// if less than 10 ships are sunk, returns false
		} else {
			return false;
		}
	}

}
