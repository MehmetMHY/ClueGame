package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
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

public class gameSetupTests {
	private static Board board;
	private static HumanPlayer p1;
	private static ComputerPlayer c1;
	private static ComputerPlayer c2;
	private static ComputerPlayer c3;
	private static ComputerPlayer c4;
	private static ComputerPlayer c5;
	
	private ArrayList<Card> testDeck;
	private Set<Card> dealtDeck;
	private Map<String, ComputerPlayer> testComputers;
	
	// set up everything for the tests as well as initialize everything
	@BeforeClass
	public static void setup() {
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
	}
	
	// Test whether the AIs and human player objects are correctly loaded.
	@Test
	public void testPlayerData() {
		testComputers = board.getPlayers();
		
		// create a new ComputerPlayer object which should be the same as the one read from the Player.txt
		c2 = new ComputerPlayer("Mr. Boddy", 8, 0, Color.black);
		// make sure that the c2 object is the same as the object created from loading the Player.txt
		assertTrue(testComputers.get("Mr. Boddy").equals(c2));
		
		// create a new ComputerPlayer object which should be the same as the one read from the Player.txt
		c4 = new ComputerPlayer("Professor Plum", 8, 22, Color.cyan);
		// make sure that the c2 object is the same as the object created from loading the Player.txt
		assertTrue(testComputers.get("Professor Plum").equals(c4));
		
		// create a new ComputerPlayer object which should be the same as the one read from the Player.txt
		p1 = new HumanPlayer("Colonel Mustard", 21, 8, Color.yellow);
		// make sure that the c2 object is the same as the object created from loading the Player.txt
		assertTrue(board.getP1().equals(p1));
	}
	
	// the following test it to make sure that the data loaded from the Room.txt file are correct
	@Test
	public void testRoomCards(){	
		testDeck = board.getRoomDeck();
		
		Card kitchen = new Card("Kitchen");
		assertTrue(testDeck.get(0).equals(kitchen));
		
		Card dining = new Card("Dining room");
		assertTrue(testDeck.get(1).equals(dining));
		
		Card library = new Card("Library");
		assertTrue(testDeck.get(2).equals(library));
		
		Card lounge = new Card("Lounge");
		assertTrue(testDeck.get(3).equals(lounge));
		
		Card bathroom = new Card("Bathroom");
		assertTrue(testDeck.get(4).equals(bathroom));
		
		Card master = new Card("Master Bedroom");
		assertTrue(testDeck.get(5).equals(master));
		
		Card theater = new Card("Theater");
		assertTrue(testDeck.get(6).equals(theater));
		
		Card office = new Card("Office");
		assertTrue(testDeck.get(7).equals(office));
		
		Card game = new Card("Game Hour");
		assertTrue(testDeck.get(8).equals(game));
	}
	
	// the following test it to make sure that the data loaded from the Weapon.txt file are correct
	@Test
	public void testWeaponCards(){
		testDeck = board.getWeaponDeck();
		
		Card knife = new Card("Real Knife");
		assertTrue(testDeck.get(0).equals(knife));
		
		Card rifle = new Card("Binary Rifle");
		assertTrue(testDeck.get(1).equals(rifle));
		
		Card katana = new Card("Zangetsu");
		assertTrue(testDeck.get(2).equals(katana));
		
		Card dominator = new Card("Dominator");
		assertTrue(testDeck.get(3).equals(dominator));
		
		Card cross = new Card("Cross Punisher");
		assertTrue(testDeck.get(4).equals(cross));
		
		Card gun = new Card("Gravity Gun");
		assertTrue(testDeck.get(5).equals(gun));
	}
	
	// the following test it to make sure that the data loaded from a part of OurRoom.txt file is correct
	@Test
	public void testPeopleCards(){
		testDeck = board.getPlayerDeck();

		Card human = new Card("Colonel Mustard");
		assertTrue(testDeck.get(0).equals(human));
		
		Card c1 = new Card("Mrs. White");
		assertTrue(testDeck.get(1).equals(c1));
		
		Card c2 = new Card("Mr. Boddy");
		assertTrue(testDeck.get(2).equals(c2));
		
		Card c3 = new Card("Mr. Green");
		assertTrue(testDeck.get(3).equals(c3));
		
		Card c4 = new Card("Professor Plum");
		assertTrue(testDeck.get(4).equals(c4));
		
		Card c5 = new Card("Mrs. Peacock");
		assertTrue(testDeck.get(5).equals(c5));
	}

	// test if the cards are dealt correctly
	@Test
	public void testDealCards() {
		dealtDeck = board.getCardDealt();
		
		ArrayList<Card> testWeaponDeck = board.getWeaponDeck();
		ArrayList<Card> testPlayerDeck = board.getPlayerDeck();
		ArrayList<Card> testRoomDeck = board.getRoomDeck();
		ArrayList<Card> testSolutionDeck = board.getSolutionDeck();
		
		// totalSize holds how many cards there are total
		int totalSize = testWeaponDeck.size() + testPlayerDeck.size() + testRoomDeck.size();
		// update totalSize by deducing the amount lost from picking the 3 solution cards at the start of the game
		totalSize = totalSize - testSolutionDeck.size();

		assertTrue(dealtDeck.size() == totalSize);	// make sure each card is only dealt once

		// Test if there are 3 cards assigned to each player
		testComputers = board.getPlayers();
		assertTrue(testComputers.get("Mr. Boddy").getMyCards().size() == 3);
		assertTrue(testComputers.get("Mr. Green").getMyCards().size() == 3);
		assertTrue(testComputers.get("Mrs. White").getMyCards().size() == 3);
		assertTrue(testComputers.get("Professor Plum").getMyCards().size() == 3);
	}
	
}
