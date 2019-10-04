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
		calcAdjacencies();
		
	}
	
	public void calcAdjacencies() {
		Set<BoardCell> tempSet;
		BoardCell temp;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				tempSet = new HashSet<BoardCell>();
				temp = new BoardCell(i,j);
				if (i + 1 < ROW) {
					BoardCell tempT = new BoardCell(i + 1, j);
					tempSet.add(tempT);
				} 
				if (j + 1 < COL) {
					BoardCell tempT = new BoardCell(i, j + 1);
					tempSet.add(tempT);
				} 
				if (i - 1 >= 0) {
					BoardCell tempT = new BoardCell(i - 1, j);
					tempSet.add(tempT);
				} 
				if (j - 1 >= 0) {
					BoardCell tempT = new BoardCell(i, j - 1);
					tempSet.add(tempT);
				}
				//System.out.println(i + " " + j + " " + tempSet);
				adjMtx.put(temp, tempSet);;
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
				if (i + j == pathLength) {
					BoardCell temp = new BoardCell(i,j);
					targets.add(temp);
				}
			}
		}
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}

	public BoardCell getCell(int i, int j) {
		return grid[i][j];
	}

	public Set<BoardCell> getAdjList(BoardCell cell) {
		Set<BoardCell> temp = new HashSet<BoardCell>();
		for (BoardCell key : adjMtx.keySet()) {
			if (key.getCol() == cell.getCol()) {
				if (key.getRow() == cell.getRow()) {
					temp = adjMtx.get(key);
				}
			}
		}
		return temp;
	}

	
}
