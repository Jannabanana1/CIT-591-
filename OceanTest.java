package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testEmptyOcean() {

		// tests that all locations in the ocean are "empty"

		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());

		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());

		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}

	@Test
	void testPlaceAllShipsRandomly() {

		// tests that the correct number of each ship type is placed in the ocean

		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}

		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);

		// calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
		int occupiedSpaces = (NUM_BATTLESHIPS * 4) + (NUM_CRUISERS * 3) + (NUM_DESTROYERS * 2) + (NUM_SUBMARINES * 1);

		// test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 5));

		// TODO
		// More tests
		Battleship battleship = new Battleship();
		row = 3;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(3, 4));
		assertTrue(ocean.isOccupied(3, 3));
		assertTrue(ocean.isOccupied(3, 3));
		assertTrue(ocean.isOccupied(3, 1));
		assertFalse(ocean.isOccupied(3, 0));

		Cruiser cruiser = new Cruiser();
		row = 7;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(7, 6));
		assertTrue(ocean.isOccupied(6, 6));
		assertTrue(ocean.isOccupied(5, 6));

		Submarine submarine2 = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine2.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(9, 9));
		assertFalse(ocean.isOccupied(9, 8));

	}

	@Test
	void testShootAt() {

		assertFalse(ocean.shootAt(0, 1));
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		// tests shooting at same area returns true if ship not sunk
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());

		assertTrue(ocean.shootAt(0, 5));

		// TODO
		// More tests
		Submarine submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertFalse(ocean.shootAt(5, 4));
		// tests shootAt method returns false after ship is sunk
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(5, 5));
		// tests shots fired is equal to total shots fired so far
		assertEquals(7, ocean.getShotsFired());
		// tests total hit count
		assertEquals(4, ocean.getHitCount());

		Cruiser cruiser = new Cruiser();
		row = 7;
		column = 2;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(7, 2));
		assertTrue(ocean.shootAt(7, 1));
		// hits same area
		assertTrue(ocean.shootAt(7, 2));
		assertTrue(ocean.shootAt(7, 0));
		assertFalse(ocean.shootAt(7, 3));
		// tests shootAt method returns false after ship is sunk
		assertTrue(cruiser.isSunk());
		assertFalse(ocean.shootAt(7, 2));
		// tests shots fired is equal to total shots fired so far
		assertEquals(13, ocean.getShotsFired());
		// tests total hit count
		assertEquals(8, ocean.getHitCount());
	}

	@Test
	void testGetShotsFired() {

		// should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 4));
		assertFalse(ocean.shootAt(0, 1));
		assertEquals(10, ocean.getShotsFired());

		Ship battleship = new Battleship();
		row = 9;
		column = 2;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 2));
		assertTrue(ocean.shootAt(8, 2));
		assertTrue(ocean.shootAt(7, 2));
		assertTrue(ocean.shootAt(6, 2));
		assertFalse(ocean.shootAt(9, 1));
		assertFalse(ocean.shootAt(8, 0));
		assertEquals(16, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		// TODO
		// More tests
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 3));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(9, 2));
		// shoots at same hit area
		assertTrue(ocean.shootAt(9, 2));
		assertTrue(ocean.shootAt(9, 1));
		// shoots in empty area
		assertFalse(ocean.shootAt(9, 0));
		assertFalse(ocean.shootAt(9, 1));
		assertEquals(5, ocean.getHitCount());

		Submarine submarine = new Submarine();
		row = 4;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(4, 5));
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(3, 5));
		assertFalse(ocean.shootAt(4, 5));

		assertEquals(6, ocean.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		// come back to this
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		// TODO
		// More tests
		Battleship battleship = new Battleship();
		row = 4;
		column = 3;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(4, 3));
		assertTrue(ocean.shootAt(4, 2));
		assertTrue(ocean.shootAt(4, 1));
		assertTrue(ocean.shootAt(4, 0));
		assertTrue(battleship.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(6, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		assertFalse(submarine.shootAt(row, column));
		// tests that there are still only two ships sunk despite hitting a sunk ship
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {

		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);

		assertEquals("empty", shipArray[0][0].getShipType());

		// TODO
		// More tests
		Submarine submarine = new Submarine();
		int row = 0;
		int column = 0;
		boolean horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		// tests array by getting ship type from array after placing a ship
		assertEquals("submarine", shipArray[0][0].getShipType());

		Destroyer destroyer = new Destroyer();
		row = 5;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		// tests array by getting ship type from array after placing a ship
		assertEquals("destroyer", shipArray[5][5].getShipType());
		assertEquals("destroyer", shipArray[5][4].getShipType());
	}

}