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
	
	@BeforeClass
	public static void setup() {
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
		
		// initialize the player objects and locations:
//		p1 = new HumanPlayer("Colonel Mustard", 21, 8, Color.yellow); // human player initialization
//		c1 = new ComputerPlayer("Mrs. White", 18, 0, Color.white); // computer player 1 initialization
//		c2 = new ComputerPlayer("Mr. Boddy", 8, 0, Color.black); // computer player 2 initialization
//		c3 = new ComputerPlayer("Mr. Green", 3, 22, Color.green); // computer player 3 initialization
//		c4 = new ComputerPlayer("Professor Plum", 8, 22, Color.cyan); // computer player 4 initialization
//		c5 = new ComputerPlayer("Mrs. Peacock", 14, 22, Color.blue); // computer player 5 initialization
	}
	
	@Test
	public void testPlayerData() { 	// Test whether the AIs and human player objects are correctly loaded.
		testComputers = board.getPlayers();
		c2 = new ComputerPlayer("Mr. Boddy", 8, 0, Color.black);
		assertTrue(testComputers.get("Mr. Boddy") == c2);
		c4 = new ComputerPlayer("Professor Plum", 8, 22, Color.cyan);
		assertTrue(testComputers.get("Professor Plum") == c4);
		// TODO: ADD A BIT MORE COMPUTERS
		
		
		p1 = new HumanPlayer("Colonel Mustard", 21, 8, Color.yellow);
		assertTrue(board.getP1() == p1);
	}
	
	@Test
	public void testRoomCards(){	// test if every room cards are loaded correctly
		testDeck = board.getRoomDeck();
		Card kitchen = new Card("Kitchen");
		assertTrue(testDeck.contains(kitchen));
		Card dining = new Card("Dining room");
		assertTrue(testDeck.contains(dining));
		Card library = new Card("Library");
		assertTrue(testDeck.contains(library));
		Card lounge = new Card("Lounge");
		assertTrue(testDeck.contains(lounge));
		Card bathroom = new Card("Bathroom");
		assertTrue(testDeck.contains(bathroom));
		Card master = new Card("Master Bedroom");
		assertTrue(testDeck.contains(master));
		Card theater = new Card("Theater");
		assertTrue(testDeck.contains(theater));
		Card office = new Card("Office");
		assertTrue(testDeck.contains(office));
		Card game = new Card("Game Hour");
		assertTrue(testDeck.contains(game));
	}
	
	@Test
	public void testWeaponCards(){		// test if every weapon cards are loaded correctly
		Card temp = new Card("Real Knife");
		testDeck = board.getWeaponDeck();
		//System.out.println(testDeck);
		//Card knife = new Card("Real Knife");
		//System.out.println(knife);
		//System.out.println(temp.toString() + " " + testDeck.get(0).toString());
		assertTrue(testDeck.get(0).equals(temp));
		System.out.println("FUCK");
		
		
		
		Card rifle = new Card("Binary Rifle");
		assertTrue(testDeck.contains(rifle));
		Card katana = new Card("Zangetsu");
		assertTrue(testDeck.contains(katana));
		Card dominator = new Card("Dominator");
		assertTrue(testDeck.contains(dominator));
		Card cross = new Card("Cross Punisher");
		assertTrue(testDeck.contains(cross));
		Card gun = new Card("Gravity Gun");
		assertTrue(testDeck.contains(gun));
	}
	
	@Test
	public void testPeopleCards(){ // test if every name cards are loaded correctly
		testDeck = board.getPlayerDeck();
		Card c1 = new Card("Mrs. Peacock");
		assertTrue(testDeck.contains(c1));
		Card c2 = new Card("Professor Plum");
		assertTrue(testDeck.contains(c2));
		Card c3 = new Card("Mrs. White");
		assertTrue(testDeck.contains(c3));
		Card c4 = new Card("Mr. Boddy");
		assertTrue(testDeck.contains(c4));
		Card c5 = new Card("Mr Green");
		assertTrue(testDeck.contains(c5));
		Card human = new Card("Colonel Mustard");
		assertTrue(testDeck.contains(human));
//		Card c6 = new Card("Miss Scarlet");
//		assertTrue(testDeck.contains(c6));
	}

	@Test
	public void testDealCards() {	// test if the cards are dealt correctly
		dealtDeck = board.getCardDealt();
		
		ArrayList<Card> testWeaponDeck = board.getWeaponDeck();
		ArrayList<Card> testPlayerDeck = board.getPlayerDeck();
		ArrayList<Card> testRoomDeck = board.getRoomDeck();
		Card[] testSolutionDeck = board.getSolutionDeck();
		
		int totalSize = testWeaponDeck.size() + testPlayerDeck.size() + testRoomDeck.size(); 
		totalSize = totalSize - testSolutionDeck.length;
		assertTrue(testDeck.size() == totalSize);	// make sure each card is only dealt once

		testComputers = board.getPlayers();
		assertTrue(testComputers.get("Mr. Boddy").getMyCards().size() == 3);	// Test if there are 3 cards assigned to each player
		assertTrue(testComputers.get("Mr. Green").getMyCards().size() == 3);
		assertTrue(testComputers.get("Mrs. White").getMyCards().size() == 3);
		assertTrue(testComputers.get("Professor Plum").getMyCards().size() == 3);
	}
	
}
