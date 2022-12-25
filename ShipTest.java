package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());

		// TODO
		// More tests
		ship = new EmptySea();
		assertEquals(1, ship.getLength());

		ship = new Submarine();
		assertEquals(1, ship.getLength());

		ship = new Destroyer();
		assertEquals(2, ship.getLength());

		ship = new Cruiser();
		assertEquals(3, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());

		// TODO
		// More tests
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(1, submarine.getBowRow());

		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(9, destroyer.getBowRow());

		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(5, cruiser.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		// TODO
		// More tests
		Ship submarine = new Submarine();
		row = 0;
		column = 1;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(1, submarine.getBowColumn());

		Ship destroyer = new Destroyer();
		row = 9;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(9, destroyer.getBowColumn());
		assertEquals(8, destroyer.getBowColumn());

		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(6, cruiser.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		// TODO
		// More tests
		ship = new Submarine();
		hits = new boolean[ship.getLength()];
		assertArrayEquals(hits, ship.getHit());
		ship.placeShipAt(1, 1, false, ocean);
		ship.shootAt(1, 1);
		assertTrue(ship.getHit()[0]);

		ship = new EmptySea();
		hits = new boolean[ship.getLength()];
		assertArrayEquals(hits, ship.getHit());
		ship.placeShipAt(3, 3, false, ocean);
		ship.shootAt(3, 3);
		assertFalse(ship.getHit()[0]);

		ship = new Cruiser();
		hits = new boolean[ship.getLength()];
		assertArrayEquals(hits, ship.getHit());
		ship.placeShipAt(6, 6, false, ocean);
		ship.shootAt(5, 6);
		assertFalse(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);

	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());

		// TODO
		// More tests
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());

		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());

		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());

		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
	}

	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		row = 4;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.isHorizontal());

		Ship emptysea = new EmptySea();
		row = 0;
		column = 0;
		horizontal = false;
		emptysea.placeShipAt(row, column, horizontal, ocean);
		assertFalse(emptysea.isHorizontal());

	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 9;
		column = 4;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());

		Ship submarine = new Submarine();
		row = 0;
		column = 4;
		horizontal = false;
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());

		Ship destroyer = new Destroyer();
		row = 0;
		column = 0;
		horizontal = false;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 2;
		column = 2;
		horizontal = false;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());

		Ship destroyer = new Destroyer();
		row = 0;
		column = 4;
		horizontal = true;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());

		Ship submarine = new Submarine();
		row = 3;
		column = 3;
		horizontal = false;
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		row = 0;
		column = 4;
		horizontal = false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());

		Ship submarine = new Submarine();
		row = 2;
		column = 2;
		horizontal = true;
		submarine.setHorizontal(horizontal);
		assertTrue(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {

		// test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		// TODO
		// More tests

		// tests that it is okay to place the cruiser since no other ships were placed
		// at 0,4
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		// tests destroyer cannot be placed at 10,10 due to being out of bounds of the
		// array
		Ship destroyer = new Destroyer();
		row = 10;
		column = 10;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");

		// tests cannot place submarine in a place in ocean that does not exist
		Ship submarine = new Submarine();
		row = -1;
		column = -1;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "No OK to place ship here.");

	}

	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

		// test when other ships are in the ocean

		// place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		// test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

		// TODO
		// More tests

		// tests that once battleship is placed, cruiser cannot be placed in the same
		// spots.
		Ship cruiser = new Cruiser();
		int row2 = 0;
		int column2 = 4;
		horizontal = true;
		boolean ok3 = cruiser.okToPlaceShipAt(row2, column2, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship here.");

		// tests that destroyer cannot be placed since it touches the battleship
		// diagonally
		Ship destroyer = new Destroyer();
		int row3 = 2;
		int column3 = 5;
		horizontal = false;
		boolean ok4 = destroyer.okToPlaceShipAt(row3, column3, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship here.");

		Ship submarine = new Submarine();
		int row4 = 0;
		int column4 = 6;
		horizontal = true;
		boolean ok5 = submarine.okToPlaceShipAt(row4, column4, horizontal, ocean);
		assertTrue(ok5, "OK to place ship here.");

		// tests that two submarines cannot be placed right next to each other
		submarine.placeShipAt(0, 6, horizontal, ocean);
		Ship submarine2 = new Submarine();
		int row5 = 0;
		int column5 = 4;
		horizontal = false;
		boolean ok6 = submarine2.okToPlaceShipAt(row5, column5, horizontal, ocean);
		assertFalse(ok6, "Not OK to place ship here.");

		Ship submarine3 = new Submarine();
		int row6 = 1;
		int column6 = 6;
		horizontal = false;
		boolean ok7 = submarine3.okToPlaceShipAt(row6, column6, horizontal, ocean);
		assertFalse(ok7, "Not OK to place ship here.");
	}

	@Test
	void testPlaceShipAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);

		// TODO
		// More tests
		Ship cruiser = new Cruiser();
		row = 4;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());

		assertEquals(cruiser, ocean.getShipArray()[3][5]);

		// tests destroyer does not actually equal the given row,column since it cannot
		// be placed there
		Ship destroyer = new Destroyer();
		row = 2;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertNotEquals(destroyer, ocean.getShipArray()[2][5]);

		// tests that destroyer is not placed at 1,0 due to length of destroyer and
		// horizontal placement
		// leading to out of bounds for 10 x 10 ocean
		Ship destroyer2 = new Destroyer();
		row = 1;
		column = 0;
		horizontal = true;
		destroyer2.placeShipAt(row, column, horizontal, ocean);

		assertNotEquals(destroyer2, ocean.getShipArray()[0][5]);

		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());

		assertEquals(submarine, ocean.getShipArray()[9][9]);
	}

	@Test
	void testShootAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = { false, false, false, false };
		assertArrayEquals(hitArray0, battleship.getHit());

		// TODO
		// More tests
		Destroyer destroyer = new Destroyer();
		row = 8;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.shootAt(8, 8));

		// tests that hitting same place twice while destroyer is not sunk returns true
		assertTrue(destroyer.shootAt(8, 8));
		assertTrue(destroyer.shootAt(8, 7));
		boolean[] hitArray1 = { true, true };
		assertArrayEquals(hitArray1, destroyer.getHit());

		// tests false shooting at destroyer since destroyer has sunk
		assertFalse(destroyer.shootAt(8, 8));

		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.shootAt(3, 5));
		boolean[] hitArray2 = { false, false, true };
		assertArrayEquals(hitArray2, cruiser.getHit());

		//
		Ship submarine = new Submarine();
		row = 9;
		column = 0;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		// tests array is false since submarine not hit yet
		boolean[] hitArray3 = { false };
		assertArrayEquals(hitArray3, submarine.getHit());
		assertTrue(submarine.shootAt(9, 0));

		// tests array is true since submarine is shot at
		boolean[] hitArray4 = { true };
		assertArrayEquals(hitArray4, submarine.getHit());

		// tests shoot at is false since submarine is sunk
		assertFalse(submarine.shootAt(9, 0));

	}

	@Test
	void testIsSunk() {

		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());

		// tests that it was sunk after shooting proper location
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());

		// TODO
		// More tests
		Ship battleship = new Battleship();
		row = 6;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertFalse(battleship.isSunk());
		assertFalse(battleship.shootAt(9, 9));
		assertFalse(battleship.isSunk());

		// tests that it was sunk after shooting proper location
		assertTrue(battleship.shootAt(6, 5));
		assertTrue(battleship.shootAt(5, 5));
		assertTrue(battleship.shootAt(4, 5));
		assertTrue(battleship.shootAt(3, 5));
		assertTrue(battleship.isSunk());

		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		// tests not sunk yet
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(9, 9));
		assertTrue(destroyer.shootAt(9, 8));
		// finally tests actually sunk after shooting both areas ship occupies
		assertTrue(destroyer.isSunk());
	}

	@Test
	void testToString() {

		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());

		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());

		// TODO
		// More tests
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());

		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(0, 0);
		// tests submarine prints "s" after being sunk
		assertEquals("s", submarine.toString());

		// tests the empty sea prints only "-" since it is not an actual ship
		EmptySea emptysea = new EmptySea();
		assertEquals("-", emptysea.toString());

		// tests "x" print for destroyer ship
		Ship destroyer = new Destroyer();
		assertEquals("x", destroyer.toString());
		row = 7;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.shootAt(9, 1);
		assertEquals("x", destroyer.toString());
	}

	@Test
	// self-made method tested to check if locations where ship is placed are
	// detected as occupied
	void testCheckShipOccupied() {
		Battleship battleship = new Battleship();
		int row = 0;
		int column = 3;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		// checks horizontal battleship is occupied at 4 locations from bow to end of
		// ship
		assertTrue(battleship.checkShipOccupied(0, 3));
		assertTrue(battleship.checkShipOccupied(0, 2));
		assertTrue(battleship.checkShipOccupied(0, 1));
		assertTrue(battleship.checkShipOccupied(0, 0));
		// tests battleship is not occupied in location not placed (0,4)
		assertFalse(battleship.checkShipOccupied(0, 4));

		// check submarine occupied given row, column
		Submarine submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(submarine.checkShipOccupied(5, 5));

		// checks destroyer occupies given row,column
		Destroyer destroyer = new Destroyer();
		row = 5;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(destroyer.checkShipOccupied(5, 8));
		assertTrue(destroyer.checkShipOccupied(5, 7));
	}

	@Test
	// self-made method tested to check if surrounding area of vertical ship
	// placement is empty as well as area of ship
	void checkAvailabilityVerticalShip() {
		Submarine submarine = new Submarine();
		int row = 5;
		int column = 5;
		boolean horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		// checks place where submarine is placed is unavailable
		assertFalse(submarine.checkAvailabilityVerticalShip(5, 5, ocean));
		// checks surrounding area unavailable
		assertFalse(submarine.checkAvailabilityVerticalShip(4, 5, ocean));
		// checks open area not touching any other ships is available
		assertTrue(submarine.checkAvailabilityVerticalShip(7, 5, ocean));

		Destroyer destroyer = new Destroyer();
		row = 3;
		column = 3;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.checkAvailabilityVerticalShip(1, 2, ocean));
		assertFalse(destroyer.checkAvailabilityVerticalShip(2, 4, ocean));
		assertTrue(destroyer.checkAvailabilityVerticalShip(1, 1, ocean));

		Cruiser cruiser = new Cruiser();
		row = 8;
		column = 2;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.checkAvailabilityVerticalShip(9, 1, ocean));
		assertFalse(cruiser.checkAvailabilityVerticalShip(8, 1, ocean));
		assertTrue(cruiser.checkAvailabilityVerticalShip(8, 0, ocean));
	}

	@Test
	// checks surrounding area around horizontal ship is free
	void checkAvailabilityHorizontalShip() {
		Cruiser cruiser = new Cruiser();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		// checks surrounding area is empty or not
		assertFalse(cruiser.checkAvailabilityVerticalShip(2, 2, ocean));
		assertFalse(cruiser.checkAvailabilityVerticalShip(2, 4, ocean));
		// checks if area where ship is placed is empty
		assertFalse(cruiser.checkAvailabilityVerticalShip(3, 3, ocean));
		// checks free space is empty
		assertTrue(cruiser.checkAvailabilityVerticalShip(0, 0, ocean));

		Battleship battleship = new Battleship();
		row = 5;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		// checks surrounding area is empty or not
		assertFalse(battleship.checkAvailabilityVerticalShip(4, 7, ocean));
		assertFalse(battleship.checkAvailabilityVerticalShip(6, 4, ocean));
		// checks if area where ship is placed is empty
		assertFalse(battleship.checkAvailabilityVerticalShip(5, 3, ocean));
		// checks free space is empty
		assertTrue(battleship.checkAvailabilityVerticalShip(9, 9, ocean));

		Submarine submarine = new Submarine();
		row = 7;
		column = 3;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		// checks surrounding area is empty or not
		assertFalse(submarine.checkAvailabilityVerticalShip(6, 3, ocean));
		assertFalse(submarine.checkAvailabilityVerticalShip(6, 4, ocean));
		// checks if area where ship is placed is empty
		assertFalse(submarine.checkAvailabilityVerticalShip(7, 3, ocean));
		// checks free space is empty
		assertTrue(submarine.checkAvailabilityVerticalShip(9, 8, ocean));
	}

	// Test Subclasses of Ship Class. Testing is done by testing methods/constructor
	// in subclasses as well as calling methods from parent Ship class

	// Battleship Tests
	@Test
	void testBattleshipClass() {
		Battleship battleship = new Battleship();

		// tests length of ship based on constructor
		assertEquals(4, Battleship.LENGTH);
		// tests the ship type
		assertEquals("battleship", battleship.getShipType());

		int row = 5;
		int column = 5;
		boolean horizontal = true;
		// tests battleship can be placed in certain row and column in ocean
		battleship.placeShipAt(row, column, horizontal, ocean);
		// tests battleship is shot at and the toString method returns an "x"
		battleship.shootAt(5, 5);
		assertEquals("x", battleship.toString());
	}

	// Cruiser Tests
	@Test
	void testCruiserClass() {
		Cruiser cruiser = new Cruiser();

		// tests length of ship based on constructor
		assertEquals(3, Cruiser.LENGTH);
		// tests the ship type
		assertEquals("cruiser", cruiser.getShipType());

		int row = 3;
		int column = 3;
		boolean horizontal = true;
		// tests cruiser can be placed in certain row and column in ocean
		cruiser.placeShipAt(row, column, horizontal, ocean);
		// tests cruiser is shot at and the toString method returns an "x"
		cruiser.shootAt(3, 3);
		assertEquals("x", cruiser.toString());
		// tests isHorizontal method works for cruiser from Ship class
		assertTrue(cruiser.isHorizontal());
		// tests correct bow and column
		assertEquals(column, cruiser.getBowColumn());
		assertEquals(row, cruiser.getBowRow());

	}

	// Destroyer Tests
	@Test
	void testDestroyerClass() {
		Destroyer destroyer = new Destroyer();

		// tests length of ship based on constructor
		assertEquals(2, Destroyer.LENGTH);
		// tests the ship type
		assertEquals("destroyer", destroyer.getShipType());

		int row = 8;
		int column = 0;
		boolean horizontal = false;
		// tests destroyer can be placed in certain row and column in ocean
		destroyer.placeShipAt(row, column, horizontal, ocean);
		// tests destroyer is shot at and the toString method returns an "x"
		destroyer.shootAt(7, 0);
		assertEquals("x", destroyer.toString());
		// tests isHorizontal method works for destroyer from Ship class
		assertFalse(destroyer.isHorizontal());
		// tests correct bow and column
		assertEquals(column, destroyer.getBowColumn());
		assertEquals(row, destroyer.getBowRow());

	}

	// Submarine Tests
	@Test
	void testSubmarineTest() {
		Submarine submarine = new Submarine();

		// tests length of ship based on constructor
		assertEquals(1, Submarine.LENGTH);
		// tests the ship type
		assertEquals("submarine", submarine.getShipType());

		int row = 0;
		int column = 0;
		boolean horizontal = false;
		// tests submarine can be placed in certain row and column in ocean
		submarine.placeShipAt(row, column, horizontal, ocean);
		// tests submarine is shot at and the toString method returns an "x"
		submarine.shootAt(0, 0);
		assertEquals("s", submarine.toString());
		// tests isHorizontal method works for submarine from Ship class
		assertFalse(submarine.isHorizontal());
		// tests correct bow and column
		assertEquals(column, submarine.getBowColumn());
		assertEquals(row, submarine.getBowRow());
		// tests submarine is sunk calling isSunk method from ship class
		assertTrue(submarine.isSunk());

	}

	// Destroyer Tests
	@Test
	void testEmptySeaClass() {
		EmptySea emptysea = new EmptySea();

		// tests length of emptysea based on constructor
		assertEquals(1, EmptySea.LENGTH);
		// tests the emptysea type
		assertEquals("empty", emptysea.getShipType());

		// tests shoot at returns false since calling method should always return false
		// at empty sea locations
		assertFalse(emptysea.shootAt(0, 2));
		// tests isSunk method returns false due to method always returning false
		assertFalse(emptysea.isSunk());
		// tests empty sea prints "-"
		assertEquals("-", emptysea.toString());

	}

}