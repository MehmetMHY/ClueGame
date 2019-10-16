package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {

	private int numRows;
	
	private int numColumns;
	
	public final int MAX_BOARD_SIZE = 50;
	
	private Board BoardCell[][];
	
	private Map<Character, String> legend;
	
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	
	private Set<BoardCell> targets;
	
	private String boardConfigFile;
	
	private String roomConfigFile;
	
	
	
	public Board getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public void loadRoomConfig() {
		// TODO FIX THIS SHIT
	}
	
	public void loadBoardConfig() {
		// TODO DO IT
	}
	
	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNumRows() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNumColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
