package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Solution;

public class gameActionTests {
	private static Board board;
	private static HumanPlayer p1;
	private static ComputerPlayer cTest;
	
	private ArrayList<Card> testDeck;
	private Set<Card> dealtDeck;
	private Map<String, ComputerPlayer> testComputers;
	Set<BoardCell> testList;
	private BoardCell temp;
	private Solution answers;
	
	// set up everything for the tests as well as initialize everything
	@BeforeClass
	public static void setup() {
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
	}
	
	// Select Target test for no rooms, rooms that are not visited, and rooms that have been visited
	@Test
	public void testSelectedTargets() {
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(19, 0, 3);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.isDoorway());
		
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(8, 0, 4);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.isDoorway());
		
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(3, 22, 4);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.isDoorway());
		
		// test cell by a door and make sure that if it goes again, it will not go to the door it already visited
		board.calcTargets(11, 15, 1);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(temp.getRoomType().equals("OL"));
		
		// if room just visited is in list, each target (including room) selected randomly
		board.calcTargets(11, 15, 1);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.getRoomType().equals("OL"));
		
		// test cell by a door and make sure that if it goes again, it will not go to the door it already visited
		board.calcTargets(6, 15, 2);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(temp.getRoomType().equals("LL"));
		
		// if room just visited is in list, each target (including room) selected randomly
		board.calcTargets(6, 15, 2);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.getRoomType().equals("LL"));
	}
	
	// make an accusation tests
	@Test
	public void accusationTests() {
		testDeck = board.getSolutionDeck();
		Solution answers = new Solution(testDeck.get(0), testDeck.get(1),testDeck.get(2));
		
		// accusation solution that is correct:
		cTest.makeAccusation(answers);
		assertTrue(cTest.correctAccusation(answers));
		
		// accusation solution with wrong person:
		Solution wrongPerson = new Solution(testDeck.get(1), testDeck.get(1),testDeck.get(2));
		cTest.makeAccusation(wrongPerson);
		assertTrue(!cTest.correctAccusation(answers));
		
		// accusation solution with wrong room:
		Solution wrongRoom = new Solution(testDeck.get(0), testDeck.get(0),testDeck.get(2));
		cTest.makeAccusation(wrongRoom);
		assertTrue(!cTest.correctAccusation(answers));
		
		// accusation solution with wrong weapon:
		Solution wrongWeapon = new Solution(testDeck.get(0), testDeck.get(1),testDeck.get(0));
		cTest.makeAccusation(wrongWeapon);
		assertTrue(!cTest.correctAccusation(answers));
	}
	
	// make an accusation tests
}




