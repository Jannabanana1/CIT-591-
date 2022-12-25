package battleship;

import java.util.Scanner;

/**
 * 
 * @author jannatulferdaus 
 * Penn ID: 45739693 
 * Statement of work: I worked by myself and used all the resources from 
 * within coursera including recitation and OOH sessions
 */
public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create new instance of ocean
		Ocean ocean = new Ocean();
		// places all 10 ships randomly
		ocean.placeAllShipsRandomly();
		System.out.println("Welcome to the game of Battleship!");
		// prints out the ocean with all dots since no ships were hit in the beginning
		// of game
		ocean.print();
		// game continues to run while run variable is set to true
		boolean run = true;

		while (run) {
			// convert string to character array
			char[] ch = createCharArray();
			// stores row variable with the integer value of first item in character array
			int row = Integer.parseInt(String.valueOf(ch[0]));
			// stores column variable with the integer value of second item in character
			// array
			int column = Integer.parseInt(String.valueOf(ch[2]));
			// stores false in variable to indicate if shot missed ship or not
			boolean miss = false;
			// stores ocean array in ship array variable
			Ship[][] ship = ocean.getShipArray();
			// prints hit if shot at actual ship
			if (ocean.shootAt(row, column)) {
				System.out.println("hit!");
				// prints miss if the ocean does not shoot at ship
			} else if (!ocean.shootAt(row, column)) {
				System.out.println("miss!");
				// miss is set to true since ship is not shot at
				miss = true;
			}
			// if miss is true, nothing happens
			if (miss) {
				;
				// if miss is false, checks if the ship is sunk. Prints out which ship type is
				// sunk
			} else if (ship[row][column].isSunk()) {
				System.out.println("You sunk a " + ship[row][column].getShipType() + "!");
			}
			// prints out the hit count for the amount of times ships hit
			System.out.println("Hit count: " + ocean.getHitCount());
			System.out.println("");

			// prints out ocean array with ships hit or empty ocean
			ocean.print();
			// checks if game is over
			if (ocean.isGameOver()) {
				// prints out the end results of the game with score
				printEndGameResults(ocean);
				// checks if user wants to play again
				if (playAgain()) {
					// creates a new instance of ocean with empty array and places ships randomly
					ocean = new Ocean();
					ocean.placeAllShipsRandomly();
					ocean.print();
					// if user does not want to play again, goodbye is printed and game is exited
				} else {
					System.out.println("Goodbye!");
					break;
				}
			}
		}
	}

	/**
	 * Asks if user would like to play again and repeats question if invalid answer.
	 * Repeats game if user answers yes or exits if no. @ return replay boolean
	 * value
	 */
	private static boolean playAgain() {
		Scanner scanner = new Scanner(System.in);
		boolean ask = true;
		boolean replay = true;

		// while ask is set to true, repeatedly asks user if they would like to play
		// again.
		while (ask) {
			System.out.println("Would you like to play again? Enter (Y/N)");
			String answer = scanner.nextLine();
			// checks if user answers yes/Y/y/Yes
			if (answer.equals("Y") || answer.equals("y") || answer.equals("yes") || answer.equals("Yes")) {
				// sets replay to true to reset game and play again
				replay = true;
				// sets ask to false to not ask again
				ask = false;
				// checks if answer is no/No/n/N
			} else if (answer.equals("n") || answer.equals("N") || answer.equals("No") || answer.equals("no")) {
				// sets replay to false and exits game
				replay = false;
				ask = false;
				break;
				// invalid inputs prompt question again by going through while loop
			} else {
				ask = true;
			}
		}
		// returns if user would like to play again or not (true/false)
		return replay;
	}

	/**
	 * Prints the end results of the game, including the best score and total shots
	 * 
	 * @param ocean
	 */
	static void printEndGameResults(Ocean ocean) {
		System.out.println("Game Over!");
		// prints how many shots the user had to sink all ships
		System.out.println("You won with " + ocean.getShotsFired() + " shots!");
		// bests bestScore to very large number
		int bestScore = 1000;
		// checks if user's score is less than bestScore so far
		if (ocean.getShotsFired() < bestScore) {
			// if so, sets best score to user's number of shots fired
			bestScore = ocean.getShotsFired();
			// prints best score
			System.out.println("Your best score is " + bestScore + ".");
		}

	}

	/**
	 * 
	 * @return character array
	 */
	private static char[] createCharArray() {
		Scanner scanner = new Scanner(System.in);
		// store boolean value to true;
		boolean incorrectInput = true;
		String str = "";
		char[] ch = null;
		// checks if incorrectInput is true and keeps running program
		while (incorrectInput) {
			// tries to check if error will occur in following code
			try {
				System.out.println(
						"Please enter the row and column with the inputs separated by a comma as such: row,column. Only input row and column values 0-9.");
				str = scanner.nextLine();
				// creates a new character array with length of string
				ch = new char[str.length()];
				// copy character by character into character array from each string index
				for (int i = 0; i < str.length(); i++) {
					ch[i] = str.charAt(i);
				}
				// checks if index 1 of character array is a comma and checks ch array is length
				// of 3
				if (ch[1] != ',' || ch.length != 3) {
					// sets incorrect input to true
					incorrectInput = true;
					// checks if incorrect input is false
				} else {
					incorrectInput = false;
				}
				// catches exception
			} catch (Exception e) {
				// sets incorrect input to true and continues prompting for row,column values
				incorrectInput = true;
				continue;
			}
		}
		// returns character array
		return ch;
	}
}
