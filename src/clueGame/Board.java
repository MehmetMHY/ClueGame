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

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
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
	private String weaponConfigFile;
	private String peopleConfigFile;
	private Set<BoardCell> visited;
	private ArrayList<Card> weaponDeck;
	private ArrayList<Card> playerDeck;
	private ArrayList<Card> roomDeck;
	private ArrayList<Card> solution;
	private Map<String, ComputerPlayer> computers;
	private HumanPlayer player;
	private Solution answer;
	
	private Set<Card> cardDealt;
	private ArrayList<Card> completeDeck;

	// variable used for singleton pattern
	private static Board theInstance = new Board();

	// constructor is private to ensure only one can be created, its also used to initialize legend, adjMatrix, and targets
	private Board() {
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		legend = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		weaponDeck = new ArrayList<Card>();
		playerDeck = new ArrayList<Card>();
		roomDeck = new ArrayList<Card>();
		computers = new HashMap<String, ComputerPlayer>();
		cardDealt = new HashSet<Card> ();
		solution = new ArrayList<Card>();
	}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	// This function loads cards and players.
	@SuppressWarnings("resource")
	public void loadConfigFiles() throws BadConfigFormatException, IOException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		// create weapon, room, and player decks
		createAllCards();
				
		Random rand = new Random();
		
		// Add 3 random cards into solution
		
		int randint1 = rand.nextInt(weaponDeck.size());
		int randint2 = rand.nextInt(roomDeck.size());
		int randint3 = rand.nextInt(playerDeck.size());
		
		solution.add(weaponDeck.get(randint1));
		solution.add(roomDeck.get(randint1));
		solution.add(playerDeck.get(randint1));

		// create a random index value from the weapon, room, and player arraylists
		int pW = rand.nextInt(weaponDeck.size());
		int pR = rand.nextInt(roomDeck.size());
		int pP = rand.nextInt(playerDeck.size());
		
		// add random weapon from weaponDeck to player
		player.addCards(weaponDeck.get(pW));
		// add dealt card to carDealt
		cardDealt.add(weaponDeck.get(pW));

		// add random room from roomDeck to player
		player.addCards(roomDeck.get(pR));
		// add dealt card to carDealt
		cardDealt.add(roomDeck.get(pR));

		// add random person from playerDeck to player
		player.addCards(playerDeck.get(pP));
		// add dealt card to carDealt
		cardDealt.add(playerDeck.get(pP));
		
		// Create temporal arraylist so we can delete things
		ArrayList<Card> tempWeaponDeck = new ArrayList<Card>(weaponDeck);
		ArrayList<Card> tempRoomDeck = new ArrayList<Card>(roomDeck);
		ArrayList<Card> tempPlayerDeck = new ArrayList<Card>(playerDeck);
		
		// deal the cards to the players
		dealCards(rand, tempWeaponDeck, tempRoomDeck, tempPlayerDeck);
		
		// set the class's Solution object, answer,  equal to the values in the solution arraylist
		answer = new Solution(solution.get(0), solution.get(1), solution.get(2));
	}

	private void dealCards(Random rand, ArrayList<Card> tempWeaponDeck, ArrayList<Card> tempRoomDeck,
			ArrayList<Card> tempPlayerDeck) {
		for (Map.Entry<String, ComputerPlayer> entry : computers.entrySet()) {
			
			// Initialize random cards and make sure they are not in cardDealt
			int cW = rand.nextInt(tempWeaponDeck.size());
			while (cardDealt.contains(tempWeaponDeck.get(cW))) {
				cW = rand.nextInt(tempWeaponDeck.size());
			}
			int cR = rand.nextInt(tempRoomDeck.size());
			while (cardDealt.contains(tempRoomDeck.get(cR))) {
				cR = rand.nextInt(tempRoomDeck.size());
			}
			int cP = rand.nextInt(tempPlayerDeck.size());
			while (cardDealt.contains(tempPlayerDeck.get(cP)) ) {
				cP = rand.nextInt(tempPlayerDeck.size());
			}
			
			// Add/deal cards into players' hand
			entry.getValue().addCards(tempWeaponDeck.get(cW));
			entry.getValue().addCards(tempRoomDeck.get(cR));
			entry.getValue().addCards(tempPlayerDeck.get(cP));
			
			// Add these cards into the cardDealt
			cardDealt.add(tempWeaponDeck.get(cW));
			cardDealt.add(tempRoomDeck.get(cR));
			cardDealt.add(tempPlayerDeck.get(cP));
		}
	}

	// create the cards for weapons, person, and locations
	private void createAllCards() throws FileNotFoundException, IOException, BadConfigFormatException,
			IllegalAccessException, NoSuchFieldException {
		
		// The first reader reads in weapon cards from the weapons text file
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(weaponConfigFile));
		String tempLine = reader.readLine();
		while (tempLine != null) {
			String[] line = tempLine.split(", ");
			if (line.length == 1) {
				Card temp = new Card(line[0]);
				temp.setType(CardType.WEAPON);
				weaponDeck.add(temp);
			} else {
				throw new BadConfigFormatException("Weapon file's format not valid");
			}
			tempLine = reader.readLine();
		}
		reader.close();
		
		// The second reader (reader1) reads in player cards from the player text file
		BufferedReader reader1;
		reader1 = new BufferedReader(new FileReader(peopleConfigFile));
		String tempLine1 = reader1.readLine();
		while (tempLine1 != null) {
			String[] line = tempLine1.split(", ");
			Card temp = new Card(line[0]);
			temp.setType(CardType.PERSON);
			playerDeck.add(temp);
			tempLine1 = reader1.readLine();
		}
		reader1.close();
		
		// The third(reader11) reads in both human and computer players from ourRoom text file
		BufferedReader reader11;
		reader11 = new BufferedReader(new FileReader(peopleConfigFile));
		String tempLine11 = reader11.readLine();
		boolean playerSet = false;
		while (tempLine11 != null) {
			String[] line = tempLine11.split(", ");
			if (playerSet) {
				int inRow = Integer.parseInt(line[2]);
				int inCol = Integer.parseInt(line[3]);
				Color newColor = (Color)Color.class.getField(line[1]).get(null);
				ComputerPlayer tempC = new ComputerPlayer(line[0], inRow, inCol, newColor);
				computers.put(line[0], tempC);
			} else {
				int inRow = Integer.parseInt(line[2]);
				int inCol = Integer.parseInt(line[3]);
				Color newColor = (Color)Color.class.getField(line[1]).get(null);
				player = new HumanPlayer(line[0], inRow, inCol, newColor);
				playerSet = true;
			}
			tempLine11 = reader11.readLine();
		}
		reader11.close();
	}
	
	public void selectAnswer() {
		// TODO
	}
				
	// initialize boardConfigFile and roomConfigFile
	public void setConfigFiles(String csvFile, String textFile) {
		boardConfigFile = csvFile;
		roomConfigFile = textFile;
	}
	
	// initialize weapon and people files' names
	public void setCardConfigFiles(String weapon, String names) {
		weaponConfigFile = weapon;
		peopleConfigFile = names;
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
				if (line[2].equals("Card")) {
					Card rCard = new Card(line[1]);
					rCard.setType(CardType.ROOM);
					roomDeck.add(rCard);
				}
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
	@SuppressWarnings({ "incomplete-switch", "incomplete-switch" })
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
	
	// checks if the accusation is correct with the correct Solution Answer from the class
	public boolean checkAccusation(Solution accusation) {
		if(answer.equals(accusation)) {
			return true;
		}else {
			return false;
		}
	}
	
	// method that handles the suggestion(s) from the players
	public Card handleSuggestion(Player accuser, Map<String, ComputerPlayer> computers, HumanPlayer human, Solution guess) {
		Card answer = new Card("tempAnswer");
		Card playerDisprove = new Card("tempPlayerCard"); // stores the returned card from human
		ArrayList<Card> computerDisprove = new ArrayList<Card>(); // arraylist of cards returned from computers
		
		// if the human disprove, assign the card to playerDisprove
		if (human.disproveSuggestion(guess) != null) {
			playerDisprove = human.disproveSuggestion(guess);
		}
		
		// loop though the map computers
		for (Entry<String, ComputerPlayer> c : computers.entrySet()) {
			// if any computer disproves the suggestion and this computer player is not the accuser,
			// add the returned card to computerDisprove.
			if (c.getValue().disproveSuggestion(guess) != null && c.getValue() != accuser) {
				computerDisprove.add(c.getValue().disproveSuggestion(guess));
			}
		}
		
		// if at least on computer player disproved, return the last card
		if (computerDisprove.size() > 0) {
			return computerDisprove.get(computerDisprove.size() - 1);
		// if the human player disproved return the human player's card
		} else if (!playerDisprove.getCardName().equals("tempPlayerCard")) {
			return playerDisprove;
		// else return null
		} else {
			return null;
		}
	}
	
	public Set<Card> getCardDealt() {
		return cardDealt;
	}
	
	public HumanPlayer getP1() {
		return player;
	}
	
	public ArrayList<Card> getWeaponDeck() {
		return weaponDeck;
	}

	public ArrayList<Card> getPlayerDeck() {
		return playerDeck;
	}

	public ArrayList<Card> getRoomDeck() {
		return roomDeck;
	}

	public Map<String, ComputerPlayer> getPlayers() {
		return computers;
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

	public ArrayList<Card> getSolutionDeck() {
		return solution;
	}

	public void setSolutionDeck(ArrayList<Card> solution) {
		this.solution = solution;
	}

	public ArrayList<Card> getCompleteDeck() {
		return completeDeck;
	}

	public void setCompleteDeck(ArrayList<Card> completeDeck) {
		this.completeDeck = completeDeck;
	}


}