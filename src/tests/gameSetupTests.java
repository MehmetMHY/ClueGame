package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class gameSetupTests {
	private static Board board;
	private static HumanPlayer p1;
	private static ComputerPlayer c1;
	private static ComputerPlayer c2;
	private static ComputerPlayer c3;
	private static ComputerPlayer c4;
	private static ComputerPlayer c5;
	
	private Set<Card> testDeck;
	
	@BeforeClass
	public static void setup() {
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.initialize(); // load both config files for tests
	}
	
	@BeforeClass
	public static void LoadPeople() {
		// initialize the player objects and locations:
		p1 = new HumanPlayer("Colonel Mustard", 21, 8, Color.yellow); // human player initialization
		c1 = new ComputerPlayer("Mrs. White", 18, 0, Color.white); // computer player 1 initialization
		c2 = new ComputerPlayer("Mr. Boddy", 8, 0, Color.black); // computer player 2 initialization
		c3 = new ComputerPlayer("Mr. Green", 3, 22, Color.green); // computer player 3 initialization
		c4 = new ComputerPlayer("Professor Plum", 8, 22, Color.cyan); // computer player 4 initialization
		c5 = new ComputerPlayer("Mrs. Peacock", 14, 22, Color.blue); // computer player 5 initialization
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testRoomCards(){
		testDeck = board.getDeck();
		assertTrue(testDeck.contains("Kitchen"));
		assertTrue(testDeck.contains("Dining room"));
		assertTrue(testDeck.contains("Library"));
		assertTrue(testDeck.contains("Lounge"));
		assertTrue(testDeck.contains("Bathroom"));
		assertTrue(testDeck.contains("Master Bedroom"));
		assertTrue(testDeck.contains("Theater"));
		assertTrue(testDeck.contains("Office"));
		assertTrue(testDeck.contains("Game Hour"));
	}
	
	@Test
	public void testWeaponCards(){
		testDeck = board.getDeck();
		assertTrue(testDeck.contains("Real Knife"));
		assertTrue(testDeck.contains("Binary Rifle"));
		assertTrue(testDeck.contains("Zangetsu"));
		assertTrue(testDeck.contains("Dominator"));
		assertTrue(testDeck.contains("Cross Punisher"));
		assertTrue(testDeck.contains("Gravity Gun"));
	}
	
	@Test
	public void testPeopleCards(){
		testDeck = board.getDeck();
		assertTrue(testDeck.contains("Mrs. Peacocks"));
		assertTrue(testDeck.contains("Miss Scarlet"));
		assertTrue(testDeck.contains("Professor Plum"));
		assertTrue(testDeck.contains("Katana"));
		assertTrue(testDeck.contains("Dominator"));
		assertTrue(testDeck.contains("Death Note"));
		assertTrue(testDeck.contains("Cross Punisher"));
		assertTrue(testDeck.contains("Office"));
		assertTrue(testDeck.contains("Game Hour"));
	}
}
