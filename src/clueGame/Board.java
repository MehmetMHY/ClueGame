package clueGame;

import java.io.BufferedReader;
import java.io.FileReader;
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
	// constructor is private to ensure only one can be created
	private Board() {
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void setConfigFiles(String csvFile, String textFile) {
		boardConfigFile = csvFile;
		roomConfigFile = textFile;	
	}
	
	public void initialize() {
		loadBoardConfig();
		loadRoomConfig();
	}


	
	public void loadRoomConfig() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(roomConfigFile));
			String tempLine = reader.readLine();
			while (tempLine != null) {
				String[] line = tempLine.split(", ");
				legend.put(line[0].charAt(0), line[1]);
				tempLine = reader.readLine();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadBoardConfig() {
		BufferedReader reader;
		int numRow = 0, numCol = 0;
		try {
			reader = new BufferedReader(new FileReader(boardConfigFile));
			String tempLine = reader.readLine();
			while (tempLine != null) {
				numRow++;
				String[] line = tempLine.split(",");
				numCol = line.length;
				tempLine = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		numRows = numRow;
		numColumns = numCol;
		board = new BoardCell[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				board[i][j] = new BoardCell(i,j);
			}
		}
		
		numRow = 0;
		numCol = 0;
		try {
			reader = new BufferedReader(new FileReader(boardConfigFile));
			String tempLine = reader.readLine();
			while (tempLine != null) {
				numCol = 0;
				String[] line = tempLine.split(",");
				for (String i:line) {
					board[numRow][numCol].setRoomType(i);
					numCol++;
				}
				numRow++;
				tempLine = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(board);
		
	}
	
	public void calcAdjacencies() {
		// TODO ???
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		// TODO ???
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
