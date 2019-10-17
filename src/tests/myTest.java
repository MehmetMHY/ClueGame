package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class myTest {

	public static final int LEGEND_SIZE = 11; // number of entries in Legend
	public static final int NUM_ROWS = 22; // number of rows in gameBoard
	public static final int NUM_COLUMNS = 23; // number of cols in gameBoard

	private static Board board; // Board object
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void testLegandSize() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
	}
	
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Dining room", legend.get('D'));
		assertEquals("Library", legend.get('L'));
		assertEquals("Lounge", legend.get('O'));
		assertEquals("Bathroom", legend.get('B'));
		assertEquals("Master Bedroom", legend.get('M'));
		assertEquals("Theater", legend.get('T'));
		assertEquals("Office", legend.get('C'));
		assertEquals("Game Hour", legend.get('G'));
		assertEquals("Walkway", legend.get('W'));
		assertEquals("Closest", legend.get('X'));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus 
	// two cells that are not a doorway.
	// These cells are white on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		int[] r = new int[] {21, 0};
		int[] c = new int[] {22, 0};
		BoardCell room = null;
		
		for(int i = 0; i < r.length; i++) {
			// Test that room pieces that aren't doors know it
			room = board.getCellAt(r[i], c[i]);
			assertFalse(room.isDoorway());	
			
			// Test that walkways are not doors
			BoardCell cell = board.getCellAt(r[i], c[i]);
			assertFalse(cell.isDoorway());	
		}

		room = board.getCellAt(12, 4);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		
		room = board.getCellAt(4, 11);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		
		room = board.getCellAt(11, 16);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		
		room = board.getCellAt(14, 10);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

	}
	
	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() {
		int totalDoors = 14;
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		
		Assert.assertEquals(totalDoors, numDoors);
	}

	@Test
	public void testRoomInitials() {
		// tests cell (0,0) to make sure the room initial is C
		assertEquals('K', board.getCellAt(21, 0).getInitial());
		assertEquals('D', board.getCellAt(21, 16).getInitial());
		assertEquals('L', board.getCellAt(4, 22).getInitial());
		assertEquals('O', board.getCellAt(11, 18).getInitial());
		assertEquals('B', board.getCellAt(2, 11).getInitial());
		assertEquals('M', board.getCellAt(0, 0).getInitial());
		assertEquals('T', board.getCellAt(20, 20).getInitial());
		assertEquals('C', board.getCellAt(0, 21).getInitial());
		assertEquals('G', board.getCellAt(14, 0).getInitial());
		assertEquals('W', board.getCellAt(21, 18).getInitial());
		assertEquals('X', board.getCellAt(10, 10).getInitial());
	}
}
