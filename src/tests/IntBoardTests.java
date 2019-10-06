package tests;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	private IntBoard board;

	@Before
	public void beforeAll() {
		board = new IntBoard();  // IntBoard constructor calls calcAdjacencies() to do the desired JUnit tests
	}
	
	/*
	 * Test adjacencies for the following coordinates:
	 * 		(0,0), (3,3), (1,3), (3,0), (1,1), & (2,2)
	 */
	
	@Test // (0,0)
	public void testAdjacency0_0(){
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test // (3,3)
	public void testAdjacency3_3(){
		BoardCell cell = board.getCell(3, 3);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}
	
	@Test // (1,3)
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
	
	@Test // (3,0)
	public void testAdjacency3_0(){
		BoardCell cell = board.getCell(3, 0);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList.add(cell);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test // (1, 1)
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
	
	@Test // (2, 2)
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
	 * Test target for the following coordinates:
	 * 		(0,0), (3,3), (1,3), (3,0), (1,1), & (2,2)
	 */
	
	@Test // (0, 0)
	public void testTargets0_0(){
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test // (3, 3)
	public void testTargets3_3(){
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(2, 3)));
	}
	
	@Test // (0, 3)
	public void testTargets0_3(){
		BoardCell cell = board.getCell(0, 3);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();

		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1 ,3)));
		assertTrue(targets.contains(board.getCell(0, 2)));
	}
	
	@Test // (3, 0)
	public void testTargets3_0(){
		BoardCell cell = board.getCell(3, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();

		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(3, 1)));
	}
	
	@Test // (1, 1)
	public void testTargets1_1(){
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));

	}
	
	@Test // (1, 2)
	public void testTargets1_2(){
		BoardCell cell = board.getCell(1, 2);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));

	}
}
