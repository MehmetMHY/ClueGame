//package tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import clueGame.Board;
//import clueGame.BoardCell;
//import clueGame.DoorDirection;
//
//public class myTest {
//
//	public static final int LEGEND_SIZE = 11;
//	public static final int NUM_ROWS = 22;
//	public static final int NUM_COLUMNS = 23;
//
//	private static Board board;
//	
//	@BeforeClass
//	public static void setUp() {
//		// Board is singleton, get the only instance
//		board = Board.getInstance();
//		// set the file names to use my config files
//		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
//		// Initialize will load BOTH config files 
//		board.initialize();
//	}
//	
//	@Test
//	public void testLegandSize() {
//		// Get the map of initial => room 
//		Map<Character, String> legend = board.getLegend();
//		// Ensure we read the correct number of rooms
//		assertEquals(LEGEND_SIZE, legend.size());
//	}
//	
//	@Test
//	public void testRooms() {
//		// Get the map of initial => room 
//		Map<Character, String> legend = board.getLegend();
//		// To ensure data is correctly loaded, test retrieving a few rooms 
//		// from the hash, including the first and last in the file and a few others
//		assertEquals("Conservatory", legend.get('C'));
//		assertEquals("Kitchen", legend.get('K'));
//		assertEquals("Ballroom", legend.get('B'));
//		assertEquals("Billiard room", legend.get('R'));
//		assertEquals("Study", legend.get('S'));
//		assertEquals("Dining room", legend.get('D'));
//		assertEquals("Library", legend.get('L'));
//		assertEquals("Hall", legend.get('H'));
//		assertEquals("Lounge", legend.get('O'));
//		assertEquals("Cloest", legend.get('X'));
//		assertEquals("Walkway", legend.get('W'));
//	}
//	
//	@Test
//	public void testBoardDimensions() {
//		// Ensure we have the proper number of rows and columns
//		assertEquals(NUM_ROWS, board.getNumRows());
//		assertEquals(NUM_COLUMNS, board.getNumColumns());		
//	}
//	
//	// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus 
//	// two cells that are not a doorway.
//	// These cells are white on the planning spreadsheet
//	@Test
//	public void FourDoorDirections() {
//		int[] r = new int[] {14, 0};
//		int[] c = new int[] {14, 6};
//		BoardCell room = null;
//		
//		for(int i = 0; i < r.length; i++) {
//			// Test that room pieces that aren't doors know it
//			room = board.getCellAt(r[i], c[i]);
//			assertFalse(room.isDoorway());	
//			
//			// Test that walkways are not doors
//			BoardCell cell = board.getCellAt(r[i], c[i]);
//			assertFalse(cell.isDoorway());	
//		}
//
//		room = board.getCellAt(10, 6);
//		assertTrue(room.isDoorway());
//		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
//		
//		room = board.getCellAt(5, 16);
//		assertTrue(room.isDoorway());
//		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
//		
//		room = board.getCellAt(9, 17);
//		assertTrue(room.isDoorway());
//		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
//		
//		room = board.getCellAt(14, 11);
//		assertTrue(room.isDoorway());
//		assertEquals(DoorDirection.UP, room.getDoorDirection());
//
//	}
//	
//	// Test that we have the correct number of doors
//	@Test
//	public void testNumberOfDoorways() {
//		int totalDoors = 16;
//		int totalNonDoors = (board.getNumRows()*board.getNumColumns()-16);
//		int numDoors = 0;
//		for (int row=0; row<board.getNumRows(); row++)
//			for (int col=0; col<board.getNumColumns(); col++) {
//				BoardCell cell = board.getCellAt(row, col);
//				if (cell.isDoorway())
//					numDoors++;
//			}
//		
//		Assert.assertEquals(totalDoors, numDoors);
//		Assert.assertEquals(totalNonDoors, ((board.getNumRows()*board.getNumColumns())-numDoors));
//	}
//
//	@Test
//	public void testRoomInitials() {
//		// tests cell (0,0) to make sure the room initial is C
//		assertEquals('C', board.getCellAt(0, 0).getInitial());
//		
//		// tests cell (17,0) to make sure the room initial is K
//		assertEquals('K', board.getCellAt(17, 0).getInitial());
//		
//		// tests cell (8,1) to make sure the room initial is B
//		assertEquals('B', board.getCellAt(8, 1).getInitial());
//		
//		// tests cell (0,8) to make sure the room initial is R
//		assertEquals('R', board.getCellAt(0, 8).getInitial());
//		
//		// tests cell (0,15) to make sure the room initial is C
//		assertEquals('L', board.getCellAt(0, 15).getInitial());
//		
//		// tests cell (0,21) to make sure the room initial is S
//		assertEquals('S', board.getCellAt(0, 21).getInitial());
//		
//		// tests cell (20,9) to make sure the room initial is D
//		assertEquals('D', board.getCellAt(20, 9).getInitial());
//		
//		// tests cell (20,20) to make sure the room initial is O
//		assertEquals('O', board.getCellAt(20, 20).getInitial());
//		
//		// tests cell (8,20) to make sure the room initial is H
//		assertEquals('H', board.getCellAt(8, 20).getInitial());
//		
//		// tests cell (9,11) to make sure the room initial is C
//		assertEquals('X', board.getCellAt(9, 11).getInitial());
//		
//		// tests cell (0,5) to make sure the room initial is C
//		assertEquals('W', board.getCellAt(0, 5).getInitial());
//	}
//}
