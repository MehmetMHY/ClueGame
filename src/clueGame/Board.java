/**. 
 * Board class that creates the grid, adjacency cells set,
 * initialize visited set, create targets set for the character(s),
 * as well as set up BoardCell object. This is the main class for,
 * setting up the grid for the game as well as determine all,
 * targeted and adjacency cells on the grid.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public final int MAX_BOARD_SIZE = 50;
	private BoardCell board[][];
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	private Set<BoardCell> visited;
	private Set<Card> deck;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// constructor is private to ensure only one can be created, its also used to initialize legend, adjMatrix, and targets
	private Board() {
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		legend = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		deck = new HashSet<Card>();
	}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	@SuppressWarnings("resource")
	public void loadConfigFiles() throws BadConfigFormatException, IOException {

	}
	
	public void selectAnswer() {
		
	}
	
	public Card handleSuggestion() {
		return null;

	}
	
	public boolean checkAccusation(Solution accusation) {
		return false;
	}
	
	// initialize boardConfigFile and roomConfigFile
	public void setConfigFiles(String csvFile, String textFile) {
		boardConfigFile = csvFile;
		roomConfigFile = textFile;	
	}
	
	// calls loadRoomConfig and LoadBoardCongig and catch any thrown exception(s)
	public void initialize() {
		try {
			loadRoomConfig();
			loadBoardConfig();
			loadConfigFiles();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		calcAdjacencies();
	}

	// reads legend text file and stores it in legend private Map variable as well as throw any BadConfigFormatException
	@SuppressWarnings("resource")
	public void loadRoomConfig() throws BadConfigFormatException, IOException {
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(roomConfigFile));
		String tempLine = reader.readLine();
		while (tempLine != null) {
			String[] line = tempLine.split(", ");
			if (line[2].equals("Card") || line[2].equals("Other")) {
				legend.put(line[0].charAt(0), line[1]);
			} else {
				throw new BadConfigFormatException("Room type " + line[2] + " not valid");
			}
			tempLine = reader.readLine();
		}
	}
	
	// reads csv files and stores it in the board as well as throw any BadConfigFormatException
	public void loadBoardConfig() throws IOException, BadConfigFormatException {
		BufferedReader reader;
		String tempLine;
		
		// initialize numRows and numColumns
		calcRowCol();
		
		// initialize board with newly initialize numRows and numColumns
		board = new BoardCell[numRows][numColumns];
		
		// create board cell objects for board
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				board[i][j] = new BoardCell(i,j);
			}
		}
		
		// loads the content in the csv file into the board
		reader = new BufferedReader(new FileReader(boardConfigFile));
		tempLine = reader.readLine();
		
		// checks to make sure all the content on the board match the content in the legend
		int textRow = 0;
		int textCol = 0;
		while (tempLine != null) {
			textCol = 0;
			String[] line = tempLine.split(",");
			for (String i:line) {
				if (!legend.containsKey(i.charAt(0))) {
					// throw exception if something is on the board that is not included in the legend
					throw new BadConfigFormatException("Key " + i.charAt(0) + " doesn't exist");
				}
				board[textRow][textCol].setRoomType(i);
				textCol++;
			}
			textRow++;
			tempLine = reader.readLine();
		}
		
		// checks for valid doors
		checkDoorDirection();
	}
	
	// calculates the number of Rows and Cols in the csv file for the ClueGame board
	private void calcRowCol() throws FileNotFoundException, IOException, BadConfigFormatException {
		BufferedReader reader;
		int textRow = 0; // counter for rows
		int textCol = 0; // counter for cols

		// this loop determines the size of the board
		reader = new BufferedReader(new FileReader(boardConfigFile));
		String tempLine = reader.readLine();
		while (tempLine != null) {
			textRow++;
			String[] line = tempLine.split(",");
			if (textCol == 0) {
				// if the Col was not initialized we initialize it
				textCol = line.length;
			} else if (textCol != line.length) {
				// checks to make sure there is a consistent number of columns otherwise an excetpion is thrown
				throw new BadConfigFormatException("Column at " + textRow + " not valid");
			}
			tempLine = reader.readLine();
		}

		numRows = textRow;
		numColumns = textCol;
	}

	// checks for valid doors by making so that the door is by a walkway as well as if the do is in the correct DoorDirection 
	private void checkDoorDirection() throws BadConfigFormatException {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (board[i][j].isDoorway()) {
					switch(board[i][j].getDoorDirection()) {
					case UP:
						// throw exception if there is no walkway above an UP doorway
						if (i - 1 < 0 || !board[i-1][j].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case DOWN:
						// throw exception if there is no walkway above an DOWN doorway
						if (i + 1 >= numColumns || !board[i+1][j].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case LEFT:
						// throw exception if there is no walkway above an LEFT doorway
						if (j - 1 < 0 || !board[i][j-1].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case RIGHT:
						// throw exception if there is no walkway above an RIGHT doorway
						if (j + 1 >= numColumns || !board[i][j+1].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					}
				}
			}
		}
	}
	
	// calculates all possible adjacency cells on the board and stores it in the adjMatrix set
	public void calcAdjacencies() {
		Set<BoardCell> tempSet;
		BoardCell temp;
		
		// determines if (ROW+1), (ROW-1), (COL+1), & (COL-1) is possible for each Cell in the grid and stores those coordinates in adjMtx
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				tempSet = new HashSet<BoardCell>();
				
				 // calculating adjacency cells for doors and checking to make sure the doors are in the correct direction
				if (getCellAt(i,j).isDoorway()) {
					switch(getCellAt(i,j).getDoorDirection()) {
						case UP:
							tempSet.add(getCellAt(i-1,j));
							break;
						case DOWN:
							tempSet.add(getCellAt(i+1,j));
							break;
						case LEFT:
							tempSet.add(getCellAt(i,j-1));
							break;
						case RIGHT:
							tempSet.add(getCellAt(i,j+1));
							break;
					}
					adjMatrix.put(getCellAt(i,j), tempSet);
					continue;
				}
				
				// calculating adjacency cells for cells that are not Rooms (mainly walkways and doors)
				else if (!getCellAt(i,j).isRoom()) {
					// check door directions to make sure the doors are in the right direction
					checkDirection(tempSet, i, j);
				}
				adjMatrix.put(getCellAt(i,j), tempSet);
			}
		}
	}

	private void checkDirection(Set<BoardCell> tempSet, int i, int j) {
		// adds cell to set if the cell is a door in the UP direction or is not a door at call, this is for UP doors
		if (i + 1 < numRows && !getCellAt(i+1,j).isRoom()) {
			if ((getCellAt(i+1,j).isDoorway() && getCellAt(i+1,j).getDoorDirection() == DoorDirection.UP) || !getCellAt(i+1,j).isDoorway()) {
				tempSet.add(getCellAt(i+1,j));
			}
		}
		// adds cell to set if the cell is a door in the LEFT direction or is not a door at call, this is for LEFT doors
		if (j + 1 < numColumns && !getCellAt(i,j+1).isRoom()) {
			if ((getCellAt(i,j+1).isDoorway() && getCellAt(i,j+1).getDoorDirection() == DoorDirection.LEFT) || !getCellAt(i,j+1).isDoorway()) {
				tempSet.add(getCellAt(i,j+1));
			}
		} 
		// adds cell to set if the cell is a door in the DOWN direction or is not a door at call, this is for DOWN doors
		if (i - 1 >= 0 && !getCellAt(i-1,j).isRoom()) {
			if ((getCellAt(i-1,j).isDoorway() && getCellAt(i-1,j).getDoorDirection() == DoorDirection.DOWN) || !getCellAt(i-1,j).isDoorway()) {
				tempSet.add(getCellAt(i-1,j));
			}
		} 
		// adds cell to set if the cell is a door in the RIGHT direction or is not a door at call, this is for RIGHT doors
		if (j - 1 >= 0 && !getCellAt(i,j-1).isRoom()) {
			if ((getCellAt(i,j-1).isDoorway() && getCellAt(i,j-1).getDoorDirection() == DoorDirection.RIGHT) || !getCellAt(i,j-1).isDoorway()) {
				tempSet.add(getCellAt(i,j-1));
			}
		}
	}
	
	/**. 
	 * The pseudocode for calcTargets() and recursive() was provide by the 9-29-2019 before class prep work: "C12P Class Prep - Clue".
	 */
	
	// calculates all possible target cells on the board, based on the pathLength and both the desired row and colmn values
	public void calcTargets(int i, int j, int pathLength) {
		// re-initializing everything for recursive step(s)
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		
		// add current/standing-on cell to visited set
		visited.add(getCellAt(i,j));
		
		// run recursive element of calcTargets
		recursive(i,j, pathLength);
	}
	
	// calculates the target cells recursively (this is based on the pseudocode provided in one of the modules)
	public void recursive(int i, int j, int pathLength) {
		// iterate though cell's adjacent cells at cell (i, j)
		for (BoardCell cell : getAdjList(i,j)) {
			// if visited set does not contain a cell from the AdjList list, add that cell to the visited cell
			if (!visited.contains(cell)) {
				visited.add(cell);
				if (pathLength == 1) { // add the cell to target if the pathLength is 1 as a base case
					targets.add(cell);
				} else if (cell.isDoorway()) { // if the cell is a door way, also add it to targets as another base case
					targets.add(cell);
				}
				else { // repeat this process recursive with pathLength-1, do this until pathLength is 0
					recursive(cell.getRow(),cell.getCol(), pathLength-1);
				}
				// after all is done, remove the cell from the visited set
				visited.remove(cell);
			}
		}
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}

	public Map<Character, String> getLegend() {
		return legend;
	}
	
	public Set<BoardCell> getAdjList(int i, int j) {
		return adjMatrix.get(getCellAt(i,j));
	}

	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public Set<Card> getDeck() {
		return deck;
	}

	public void setDeck(Set<Card> deck) {
		this.deck = deck;
	}
}