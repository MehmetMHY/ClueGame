package experiment;

import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private BoardCell[][] grid;
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private final static int ROW = 5;
	private final static int COL = 5;
	
	
	public IntBoard() {
		grid = new BoardCell[ROW][COL];
		calcAdjacencies();
		
	}
	
	public void calcAdjacencies() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				
			}
		}
	}
	
	public void getAdList() {
		
	}
	
	public void calcTargets() {
		
	}
	
	public void getTargets() {
		
	}
	
	
	
}
