package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;

import clueGame.Board;
import clueGame.BoardCell;
import java.util.Set;

import clueGame.Board;

class MyAdjacencyTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
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
	public void testAdjacentLocationsWalways(){
		// test walkway point (7,4)
		Set<BoardCell> testPoint = board.getAdjList(7,4);
		assertTrue(testPoint.contains(board.getCellAt(6, 4)));
		assertTrue(testPoint.contains(board.getCellAt(8, 4)));
		assertTrue(testPoint.contains(board.getCellAt(7, 5)));
		assertTrue(testPoint.contains(board.getCellAt(7, 3)));
		assertEquals(4, testPoint.size());
		
		// test walkway point (8,22)
		testPoint = board.getAdjList(8, 22);
		assertTrue(testPoint.contains(board.getCellAt(8, 21)));
		assertEquals(1, testPoint.size());
		
		// test walkway point (14,15)
		testPoint = board.getAdjList(14,15);
		assertTrue(testPoint.contains(board.getCellAt(14, 16)));
		assertTrue(testPoint.contains(board.getCellAt(14, 14)));
		assertTrue(testPoint.contains(board.getCellAt(13, 15)));
		assertTrue(testPoint.contains(board.getCellAt(15, 15)));
		assertEquals(4, testPoint.size());
	}
	
	@Test
	public void testAdjacentLocationsInRooms(){
		Set<BoardCell> testPoint = board.getAdjList(2,2);
		assertEquals(0, testPoint.size());
		
		testPoint = board.getAdjList(18,11);
		assertEquals(0, testPoint.size());
		
		testPoint = board.getAdjList(1,13);
		assertEquals(0, testPoint.size());
	}

}
