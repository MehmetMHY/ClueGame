package tests;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class myTest {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Library", legend.get('L'));
		assertEquals("Lounge", legend.get('O'));
		assertEquals("Cloest", legend.get('X'));
	}
	
}
