package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
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
	private static Set<BoardCell> testList;
	private static BoardCell temp;
	private Solution answers;
	private ComputerPlayer reed;
	private ComputerPlayer mehmet;
	private ComputerPlayer evan;
	private HumanPlayer hoff;
	
	
	// set up everything for the tests as well as initialize everything
	@BeforeClass
	public static void setup() {
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.setCardConfigFiles("Weapon.txt", "Players.txt");
		board.initialize(); // load both config files for tests
		testList = new HashSet<BoardCell>();
		cTest = new ComputerPlayer("Spongebob", 14, 15, Color.yellow);
	}
	
	// Select Target test for no rooms, rooms that are not visited, and rooms that have been visited
	@Test
	public void testSelectedTargets() {
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(19, 0, 3);
		testList = board.getTargets();
		System.out.println();
		temp = cTest.pickLocation(board.getTargets());
		assertTrue(!temp.isDoorway());
		
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(8, 0, 4);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(!temp.isDoorway());
		
		// make sure that the computer player does not go to a room if it can't reach that room
		board.calcTargets(3, 22, 2);
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
		assertTrue(testList.contains(temp));
		
		// test cell by a door and make sure that if it goes again, it will not go to the door it already visited
		board.calcTargets(6, 15, 2);
		testList = board.getTargets();
		temp = cTest.pickLocation(testList);
		assertTrue(temp.getRoomType().equals("LL"));
	
	}

	//make an accusation tests
	@Test
	public void accusationTests() {
		testDeck = board.getSolutionDeck();
		Solution answers = new Solution(testDeck.get(0), testDeck.get(1),testDeck.get(2));
		
		// accusation solution that is correct:
		cTest.makeAccusation(answers);
		assertTrue(board.checkAccusation(answers));
		
		// accusation solution with wrong person:
		Solution wrongPerson = new Solution(testDeck.get(1), testDeck.get(1),testDeck.get(2));
		cTest.makeAccusation(wrongPerson);
		assertTrue(!board.checkAccusation(wrongPerson));
		
		// accusation solution with wrong room:
		Solution wrongRoom = new Solution(testDeck.get(0), testDeck.get(0),testDeck.get(2));
		cTest.makeAccusation(wrongRoom);
		assertTrue(!board.checkAccusation(wrongRoom ));
		
		// accusation solution with wrong weapon:
		Solution wrongWeapon = new Solution(testDeck.get(0), testDeck.get(1),testDeck.get(0));
		cTest.makeAccusation(wrongWeapon);
		assertTrue(!board.checkAccusation(wrongWeapon));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testSuggestions() {
		reed = new ComputerPlayer("Reed", 3, 11, Color.cyan);
		mehmet = new ComputerPlayer("Mehmet",18, 11, Color.green);
		
		// The suggested room must match the room where the player is at:
		answers = reed.createSuggestion(board);
		assertTrue(answers.room.equals(board.getLegend().get(board.getCellAt(reed.getRow(), reed.getColumn()).getInitial())));
		
		answers = mehmet.createSuggestion(board);
		assertTrue(answers.room.equals(board.getLegend().get(board.getCellAt(mehmet.getRow(), mehmet.getColumn()).getInitial())));
		
		// Test for only not seen weapon and player card
		for (int i = 1; i < board.getWeaponDeck().size(); i++) {
			reed.addSeenWeapons(board.getWeaponDeck().get(i));
		}
		
		for (int j = 0; j < board.getPlayerDeck().size() - 1; j++) {
			reed.addSeenPlayers(board.getPlayerDeck().get(j));
		}
		
		answers = reed.createSuggestion(board);
		//System.out.println(board.getPlayerDeck().get(board.getPlayerDeck().size() - 1));
		assertTrue(answers.weapon.equals(board.getWeaponDeck().get(0).getCardName()));
		assertTrue(answers.person.equals(board.getPlayerDeck().get(board.getPlayerDeck().size() - 1).getCardName()));
		
		// Test for randomly selected weapon suggestion
		reed.getSeenWeapons().clear();
		reed.getSeenPlayers().clear();
		answers = reed.createSuggestion(board);
		assertTrue(!reed.getSeenWeapons().contains(answers.weapon));
		
		// Test for randomly selected player suggestion
		assertTrue(!reed.getSeenPlayers().contains(answers.person));
	}
	
	@Test 
	public void testDisprove() {
		mehmet = new ComputerPlayer("Mehmet",18, 11, Color.green);
		Card location = new Card("Library");
		Card myself = new Card("Reed");
		Card myFriend = new Card("Alex");
		Card weapon = new Card("soda");
		Card betterWeapon = new Card("Nokia");
		
		// Return the one matching card if there is only one matching
		mehmet.addCards(location);
		mehmet.addCards(myFriend);
		mehmet.addCards(betterWeapon);
		answers = new Solution(myself, location, weapon);
		assertTrue(location.equals(mehmet.disproveSuggestion(answers)));
		
		// Return a random matching card
		mehmet.addCards(myself);
		mehmet.addCards(weapon);
		Set<Card> tempDeck = new HashSet<Card>();
		tempDeck.add(location);
		tempDeck.add(myself);
		tempDeck.add(weapon);
		assertTrue(tempDeck.contains(mehmet.disproveSuggestion(answers)));
		
		// Return null when there are no matching cards
		mehmet.getMyCards().clear();
		mehmet.addCards(myFriend);
		mehmet.addCards(betterWeapon);
		assertTrue(mehmet.disproveSuggestion(answers) == null);
	}
		
	@Test
	public void handleSuggestions() {		
		mehmet = new ComputerPlayer("Mehmet",18, 11, Color.green);
		reed = new ComputerPlayer("Reed", 3, 11, Color.cyan);
		evan = new ComputerPlayer("Evan", 8, 0, Color.yellow);
		hoff = new HumanPlayer("Prof. Hoff", 3, 7, Color.black);
		
		Card locationT = new Card("CTLM");
		Card locationF = new Card("BB280");
		Card personT = new Card("Reed");
		Card personF = new Card("Mehmet");
		Card weaponT = new Card("M&M");	
		Card weaponF = new Card("Gravity");
		
		Solution accuse = new Solution(personT, locationT, weaponT);
		
		mehmet.addCards(locationF);
		hoff.addCards(personF);
		evan.addCards(weaponF);
		
		Map<String, ComputerPlayer> computers = new HashMap<String, ComputerPlayer>();
		
		computers.put("Reed", reed);
		computers.put("Mehmet", mehmet);
		computers.put("Evan", evan);
		
		
		// suggestion no one can disprove returns null from handleSuggestion
		assertEquals(null, board.handleSuggestion(reed, computers, hoff, accuse));
		
		reed.addCards(locationT);
		
		// suggestion only accuser can disprove returns null
		assertEquals(null, board.handleSuggestion(reed, computers, hoff, accuse));
		
		reed.getMyCards().clear();
		reed.addCards(personF);

		hoff.getMyCards().clear();
		hoff.addCards(locationT);
		
		// suggestion only human can disprove return answer
		assertEquals(locationT, board.handleSuggestion(reed, computers, hoff, accuse));
		
		// suggestion only human, the accuser, can disprove return answer
		assertEquals(locationT, board.handleSuggestion(hoff, computers, hoff, accuse));
		
		hoff.getMyCards().clear();
		hoff.addCards(locationF);
		
		reed.getMyCards().clear();
		reed.addCards(personT);
		
		mehmet.getMyCards().clear();
		mehmet.addCards(locationT);
		
		// Suggestion that two players can disprove, returns answer of last correct player
		assertEquals(personT, board.handleSuggestion(hoff, computers, hoff, accuse));
		
		reed.getMyCards().clear();
		reed.addCards(personF);
		
		hoff.getMyCards().clear();
		hoff.addCards(locationT);
		
		// when both a player and a another computer can disprove, return answer of the computer
		assertEquals(locationT, board.handleSuggestion(reed, computers, hoff, accuse));
	}
	
}




