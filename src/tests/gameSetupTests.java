package tests;

import java.awt.Color;

import org.junit.BeforeClass;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class gameSetupTests {
	private static Board board;
	private static HumanPlayer PlayerOne;
	private static ComputerPlayer CompOne;
	private static ComputerPlayer CompTwo;
	private static ComputerPlayer CompThree;
	private static ComputerPlayer CompFour;
	private static ComputerPlayer CompFive;
	
	@BeforeClass
	public static void setup() {
		// initialize the player objects and locations:
		PlayerOne = new HumanPlayer("Colonel Mustard", 21, 8, Color.yellow); // human player initialization
		CompOne = new ComputerPlayer("Mrs. White", 18, 0, Color.white); // computer player 1 initialization
		CompTwo = new ComputerPlayer("Mr. Boddy", 8, 0, Color.black); // computer player 2 initialization
		CompThree = new ComputerPlayer("Mr. Green", 3, 22, Color.green); // computer player 3 initialization
		CompFour = new ComputerPlayer("Professor Plum", 8, 22, Color.cyan); // computer player 4 initialization
		CompFive = new ComputerPlayer("Mrs. Peacock", 14, 22, Color.blue); // computer player 5 initialization
		
		// initialize the board:
		board = Board.getInstance(); // only set instance variable for Board
		board.setConfigFiles("OurBoardLayout.csv", "OurRooms.txt"); // set the file names for setConfigFiles()
		board.initialize(); // load both config files for tests
	}

}
