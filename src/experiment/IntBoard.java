package experiment;

import java.util.*;

public class IntBoard {
	
	private BoardCell[][] grid;
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	private final static int ROW = 4;
	private final static int COL = 4;
	
	
	public IntBoard() {
		grid = new BoardCell[ROW][COL];
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
	}
	
	public void calcAdjacencies() {
		Set<BoardCell> tempSet;
		BoardCell temp;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				tempSet = new HashSet<BoardCell>();
				if (i + 1 < ROW) {
					tempSet.add(getCell(i+1,j));
				} 
				if (j + 1 < COL) {
					tempSet.add(getCell(i,j+1));
				} 
				if (i - 1 >= 0) {
					tempSet.add(getCell(i - 1, j));
				} 
				if (j - 1 >= 0) {
					tempSet.add(getCell(i,j - 1));
				}
				adjMtx.put(getCell(i,j), tempSet);;
			}
		}
	}
	
	public Set<BoardCell> getAdList(int row, int col) {
		BoardCell temp = new BoardCell(row,col);
		return adjMtx.get(temp);
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		for (int i = 0; i < ROW ; i++) {
			for (int j = 0; j < COL; j++) {
				for(int k = pathLength; k >= 1; k -= 2) {
					if (Math.abs(i - startCell.getRow()) + Math.abs(j - startCell.getCol()) == k) {
						this.targets.add(getCell(i, j));
					}
				}
			}
		}
	}
	
	/**
	 * @return Returns targets if generated otherwise returns null
	 */
	public Set<BoardCell> getTargets() {
		return null;
		
//		return targets;
	}

	public BoardCell getCell(int i, int j) {
		return grid[i][j];
	}

	public Set<BoardCell> getAdjList(BoardCell cell) {
		return null;
		
//		Set<BoardCell> temp = new HashSet<BoardCell>();
//		for (BoardCell key : adjMtx.keySet()) {
//			if (key.getCol() == cell.getCol()) {
//				if (key.getRow() == cell.getRow()) {
//					temp = adjMtx.get(key);
//				}
//			}
//		}
//		return temp;
	}
}
