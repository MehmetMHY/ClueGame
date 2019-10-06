/**. 
 * IntBoardTests is the class that does the JUnit testing for,
 * IntBoard's adjacency and target set based on determined,
 * grid cells.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package tests;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	private IntBoard board; // IntBoardTests object for board

	// before test method which sets board as a new IntBoard() object
	@Before
	public void beforeAll() {
		board = new IntBoard();  // IntBoard constructor calls calcAdjacencies() to do the desired JUnit tests
	}

	/*
	 * Test adjacencies for the following coordinates:
	 * 		(0,0), (3,3), (1,3), (3,0), (1,1), & (2,2)
	 */

	// tests the adjacency set for grid cell (0,0), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency0_0(){
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

	// tests the adjacency set for grid cell (3,3), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency3_3(){
		BoardCell cell = board.getCell(3, 3);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}

	// tests the adjacency set for grid cell (1,3), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency1_3(){
		BoardCell cell = board.getCell(1, 3);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(3, testList.size());
	}

	// tests the adjacency set for grid cell (3,0), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency3_0(){
		BoardCell cell = board.getCell(3, 0);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}

	// tests the adjacency set for grid cell (1,1), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency1_1(){
		BoardCell cell = board.getCell(1, 1);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertEquals(4, testList.size());
	}

	// tests the adjacency set for grid cell (2,2), by making sure that the AdjList has the correct board cells for the stated cell
	@Test
	public void testAdjacency2_2(){
		BoardCell cell = board.getCell(2, 2);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(4, testList.size());
	}

	/*
	 * Test target for the following coordinates and path lengths:
	 * 		((0,0), 1 to 6) & ((1,2), 1 to 6)
	 */
	
	// tests target set for: Starting_Point: (0, 0) & PathLength: 1 to 6
	@Test
	public void testTargets0_0(){
		BoardCell cell = board.getCell(0, 0);

		/**
		 * runs though pathLength 1-6 and tests if the target sets for that pathLength is the,
		 * correct set length and contains the correct cells based on the startCell of (1,2)
		 */
		for(int i = 1; i <= 6; i++) {
			board.calcTargets(cell, i);
			Set targets = board.getTargets();

			if(i == 1) {
				assertEquals(2, targets.size());
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(1, 0)));
			}
			if(i == 2) {
				assertEquals(3, targets.size());
				assertTrue(targets.contains(board.getCell(2, 0)));
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(1, 1)));
			}
			if(i == 3) {
				assertEquals(6, targets.size());
				assertTrue(targets.contains(board.getCell(3, 0)));
				assertTrue(targets.contains(board.getCell(2, 1)));
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(1, 2)));
				assertTrue(targets.contains(board.getCell(0, 3)));
				assertTrue(targets.contains(board.getCell(1, 0)));
			}
			if(i == 4) {
				assertEquals(6, targets.size());
				assertTrue(targets.contains(board.getCell(2, 0)));
				assertTrue(targets.contains(board.getCell(3, 1)));
				assertTrue(targets.contains(board.getCell(2, 2)));
				assertTrue(targets.contains(board.getCell(1, 3)));
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(1, 1)));
			}
			if(i == 5) {
				assertEquals(8, targets.size());
				assertTrue(targets.contains(board.getCell(3, 2)));
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(3, 0)));
				assertTrue(targets.contains(board.getCell(2, 1)));
				assertTrue(targets.contains(board.getCell(0, 3)));
				assertTrue(targets.contains(board.getCell(1, 0)));
				assertTrue(targets.contains(board.getCell(2, 3)));
				assertTrue(targets.contains(board.getCell(1, 2)));
			}
			if(i == 6) {
				assertEquals(7, targets.size());
				assertTrue(targets.contains(board.getCell(2, 0)));
				assertTrue(targets.contains(board.getCell(3, 3)));
				assertTrue(targets.contains(board.getCell(3, 1)));
				assertTrue(targets.contains(board.getCell(2, 2)));
				assertTrue(targets.contains(board.getCell(1, 3)));
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(1, 1)));
			}
			targets.clear();
		}
	}
	
	// tests target set for: Starting_Point: (1, 2) & PathLength: 1 to 6
	@Test
	public void testTargets1_2(){
		BoardCell cell = board.getCell(1, 2);

		/**
		 * runs though pathLength 1-6 and tests if the target sets for that pathLength is the,
		 * correct set length and contains the correct cells based on the startCell of (1,2)
		 */
		for(int i = 1; i <= 6; i++) {
			board.calcTargets(cell, i);
			Set targets = board.getTargets();

			if(i == 1) {
				assertEquals(4, targets.size());
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(1, 1)));
				assertTrue(targets.contains(board.getCell(2, 2)));
				assertTrue(targets.contains(board.getCell(1, 3)));
			}
			if(i == 2) {
				assertEquals(6, targets.size());
				assertTrue(targets.contains(board.getCell(2, 3)));
				assertTrue(targets.contains(board.getCell(3, 2)));
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(2, 1)));
				assertTrue(targets.contains(board.getCell(0, 3)));
				assertTrue(targets.contains(board.getCell(1, 0)));
			}
			if(i == 3) {
				assertEquals(8, targets.size());
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(0, 0)));
				assertTrue(targets.contains(board.getCell(1, 1)));
				assertTrue(targets.contains(board.getCell(2, 0)));
				assertTrue(targets.contains(board.getCell(3, 3)));
				assertTrue(targets.contains(board.getCell(2, 2)));
				assertTrue(targets.contains(board.getCell(3, 1)));
				assertTrue(targets.contains(board.getCell(1, 3)));
			}
			if(i == 4) {
				assertEquals(7, targets.size());
				assertTrue(targets.contains(board.getCell(2, 3)));
				assertTrue(targets.contains(board.getCell(3, 2)));
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(2, 1)));
				assertTrue(targets.contains(board.getCell(3, 0)));
				assertTrue(targets.contains(board.getCell(0, 3)));
				assertTrue(targets.contains(board.getCell(1, 0)));
			}
			if(i == 5) {
				assertEquals(8, targets.size());
				assertTrue(targets.contains(board.getCell(0, 2)));
				assertTrue(targets.contains(board.getCell(0, 0)));
				assertTrue(targets.contains(board.getCell(1, 1)));
				assertTrue(targets.contains(board.getCell(2, 0)));
				assertTrue(targets.contains(board.getCell(3, 3)));
				assertTrue(targets.contains(board.getCell(2, 2)));
				assertTrue(targets.contains(board.getCell(3, 1)));
				assertTrue(targets.contains(board.getCell(1, 3)));
			}
			if(i == 6) {
				assertEquals(7, targets.size());
				assertTrue(targets.contains(board.getCell(2,3)));
				assertTrue(targets.contains(board.getCell(3, 2)));
				assertTrue(targets.contains(board.getCell(0, 1)));
				assertTrue(targets.contains(board.getCell(2, 1)));
				assertTrue(targets.contains(board.getCell(3, 0)));
				assertTrue(targets.contains(board.getCell(0, 3)));
				assertTrue(targets.contains(board.getCell(1, 0)));
			}
			targets.clear();
		}
	}
}
