/**. 
 * myTest is the class that does the JUnit testing for,
 * the over all setup and positioning of the board.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class myTest {

	public static final int LEGEND_SIZE = 11; // number of entries in Legend
	public static final int NUM_ROWS = 21; // number of rows in gameBoard
	public static final int NUM_COLUMNS = 22; // number of cols in gameBoard
	public static final int NUM_DOORWAYS = 14; // number of doorways on the gameBoard
	private static Board board; // static Board object
	
	@BeforeClass
	public static void setUp() throws BadConfigFormatException {
		board = Board.getInstance(); // set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()	
		board.initialize(); // load both config files 
	}
	
	@Test
	public void testLegandSize() {
		Map<Character, String> legend = board.getLegend(); // Get the map of initial rooms 
		assertEquals(LEGEND_SIZE, legend.size()); // check to make sure the number of rooms read is the correct amount
	}
	
	@Test
	public void testRooms() {
		Map<Character, String> legend = board.getLegend(); // Get the map of initial rooms
		
		// make sure room names corresponds to their correct room symbol(s)
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
		// check to make sure there is the correct number of rows and columns on the board
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	// test doorway for each direction (Right, Left, Up, and Down).
	// check to make sure two cells that are not doorways, are indeed not doorways.
	@Test
	public void FourDoorDirections() {
		int[] r = new int[] {21, 0};
		int[] c = new int[] {22, 0};
		BoardCell room = null;
		
		// check cells (21, 22) and (0, 0)
		for(int i = 0; i < r.length; i++) {
			// Test that room pieces that aren't doors know it
			room = board.getCellAt(r[i], c[i]);
			assertFalse(room.isDoorway());	
			
			// Test that walkways are not doors
			BoardCell cell = board.getCellAt(r[i], c[i]);
			assertFalse(cell.isDoorway());	
		}
		
		room = board.getCellAt(12, 4); // check cell (12,4)
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		
		room = board.getCellAt(4, 11); // check cell (12,4) 
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		
		room = board.getCellAt(11, 16); // check cell (12,4)
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		
		room = board.getCellAt(14, 10); // check cell (12,4)
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

	}
	
	// test to see if the number of doorways on the board is the true amount
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++) {
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}	
		}
		Assert.assertEquals(NUM_DOORWAYS, numDoors);
	}

	// test to see if the RoomInitial for certain cells are correct
	@Test
	public void testRoomInitials() {
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
