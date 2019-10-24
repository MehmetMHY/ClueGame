/**. 
 * Board class that reads the files and initializes everything
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// constructor is private to ensure only one can be created, its also used to initialize legend, adjMatrix, and targets
	private Board() {
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
	}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		calcAdjacencies();
	}

	// reads legend text file and stores it in legend as well as throw any BadConfigFormatException
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
	@SuppressWarnings("resource")
	public void loadBoardConfig() throws IOException, BadConfigFormatException {
		BufferedReader reader;
		int numRow = 0, numCol = 0;

		// the first loop determines the size of the board
		reader = new BufferedReader(new FileReader(boardConfigFile));
		String tempLine = reader.readLine();
		while (tempLine != null) {
			numRow++;
			String[] line = tempLine.split(",");
			if (numCol == 0) {
				numCol = line.length;
			} else if (numCol != line.length) {
				throw new BadConfigFormatException("Column at " + numRow + " not valid");
			}
			tempLine = reader.readLine();
		}
		numRows = numRow;
		numColumns = numCol;
		board = new BoardCell[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				board[i][j] = new BoardCell(i,j);
			}
		}
		
		// after finding the size of the board, it loads the content in the csv file into the board
		numRow = 0;
		numCol = 0;
		reader = new BufferedReader(new FileReader(boardConfigFile));
		tempLine = reader.readLine();
		while (tempLine != null) {
			numCol = 0;
			String[] line = tempLine.split(",");
			for (String i:line) {
				if (!legend.containsKey(i.charAt(0))) {
					throw new BadConfigFormatException("Key " + i.charAt(0) + " doesn't exist");
				}
				board[numRow][numCol].setRoomType(i);
				numCol++;
			}
			numRow++;
			tempLine = reader.readLine();
		}
		
		// checks for valid doors by making so that the door is by a walkway as well as if the do is in the correct DoorDirection
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (board[i][j].isDoorway()) {
					switch(board[i][j].getDoorDirection()) {
					case UP:
						if (i - 1 < 0 || !board[i-1][j].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case DOWN:
						if (i + 1 >= numColumns || !board[i+1][j].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case LEFT:
						if (j - 1 < 0 || !board[i][j-1].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					case RIGHT:
						if (j + 1 >= numColumns || !board[i][j+1].getInitial().equals('W')) {
							throw new BadConfigFormatException("Doorway at " + i + " " + j + " not valid");	
						}
						break;
					}
				}
			}
		}
	}
	
	public void calcAdjacencies() {
		Set<BoardCell> tempSet;
		BoardCell temp;
		// determines if (ROW+1), (ROW-1), (COL+1), & (COL-1) is possible for each Cell in the grid and stores those coordinates in adjMtx
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				tempSet = new HashSet<BoardCell>();
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
				else if (!getCellAt(i,j).isRoom()) {
					if (i + 1 < numRows && !getCellAt(i+1,j).isRoom()) {
						if (getCellAt(i+1,j).isDoorway() && getCellAt(i+1,j).getDoorDirection() == DoorDirection.UP) {
							tempSet.add(getCellAt(i+1,j));
						} else if (!getCellAt(i+1,j).isDoorway()) {
							tempSet.add(getCellAt(i+1,j));
						}
					}
					if (j + 1 < numColumns && !getCellAt(i,j+1).isRoom()) {
						if (getCellAt(i,j+1).isDoorway() && getCellAt(i,j+1).getDoorDirection() == DoorDirection.LEFT) {
							tempSet.add(getCellAt(i,j+1));
						} else if (!getCellAt(i,j+1).isDoorway()) {
							tempSet.add(getCellAt(i,j+1));
						}
					} 
					if (i - 1 >= 0 && !getCellAt(i-1,j).isRoom()) {
						if (getCellAt(i-1,j).isDoorway() && getCellAt(i-1,j).getDoorDirection() == DoorDirection.DOWN) {
							tempSet.add(getCellAt(i-1,j));
						} else if (!getCellAt(i-1,j).isDoorway()) {
							tempSet.add(getCellAt(i-1,j));
						}
					} 
					if (j - 1 >= 0 && !getCellAt(i,j-1).isRoom()) {
						if (getCellAt(i,j-1).isDoorway() && getCellAt(i,j-1).getDoorDirection() == DoorDirection.RIGHT) {
							tempSet.add(getCellAt(i,j-1));
						} else if (!getCellAt(i,j-1).isDoorway()) {
							tempSet.add(getCellAt(i,j-1));
						}
					}
				}
				adjMatrix.put(getCellAt(i,j), tempSet);
			}
		}
		
	}
	
	public void calcTargets(int a, int b, int pathLength) {
		targets.clear();
		for (int i = 0; i < numRows ; i++) {
			for (int j = 0; j < numColumns; j++) {
				// loops though each (ROW, COL) coordinate and determines every target based on the startCell and pathLength
				for(int k = pathLength%2; k <= pathLength; k += 2) {
					if (Math.abs(i - getCellAt(a,b).getRow()) + Math.abs(j - getCellAt(a,b).getCol()) == k) {
						if (!getCellAt(i, j).isRoom() && getCellAt(i,j) != getCellAt(a,b)) {
							if (getAdjList(i,j))
							targets.add(getCellAt(i, j));
						}
					}
				}
//				if (Math.abs(i - getCellAt(a,b).getRow()) + Math.abs(j - getCellAt(a,b).getCol()) == pathLength) {
//					if (!getCellAt(i, j).isRoom() && getCellAt(i,j) != getCellAt(a,b)) {
//						targets.add(getCellAt(i, j));
//					}
//				}
			}
		}
	}
	
	public Set<BoardCell> getAdjList(int i, int j) {
		return adjMatrix.get(getCellAt(i,j));
	}


	public Set<BoardCell> getTargets() {
		return targets;
	}

	public Map<Character, String> getLegend() {
		return legend;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
}
