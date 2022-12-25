package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
	
class BattleshipGameTest {
	Ocean ocean;
	
	BattleshipGame battleshipGame;
	
	@BeforeEach
	void setUp() throws Exception {
		battleshipGame = new BattleshipGame(); 
		ocean = new  Ocean();
	}
	
	// other methods take in user input and cannot be tested
	
	// method prints the best score after shooting and game ending
	@Test
	void printEndGameResults() {
		Destroyer destroyer = new Destroyer();
		int row = 5;
		int column = 5;
		boolean horizontal = true;
		// 
		destroyer.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(4,5);
		ocean.shootAt(5, 5);
		// prints out end game results with best score so far
		BattleshipGame.printEndGameResults(ocean);
		
		// tests the amount of shots fired is 2
		assertEquals(2, ocean.getShotsFired());
		
		// new ships are placed and shot at to get best score update printed
		System.out.println("");
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(0,0);
		BattleshipGame.printEndGameResults(ocean);
		
		assertEquals(3, ocean.getShotsFired());
		
		System.out.println("");
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(9,9);
		ocean.shootAt(9,8);
		ocean.shootAt(9,7);
		BattleshipGame.printEndGameResults(ocean);
		
		assertEquals(6, ocean.getShotsFired());
	}

}
