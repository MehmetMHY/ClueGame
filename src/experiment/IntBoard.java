package experiment;

import java.util.*;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private BoardCell[][] grid;
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private final static int ROW = 4;
	private final static int COL = 4;
	
	
	public IntBoard() {
		grid = new BoardCell[ROW][COL];
		calcAdjacencies();
		
	}
	
	public void calcAdjacencies() {
		Set<BoardCell> tempSet = new HashSet<BoardCell>();
		for (int i = 0; i < ROW+1; i++) {
			for (int j = 0; j < COL+1; j++) {
				BoardCell temp = new BoardCell(i,j);
				for (int a = -1; a <= 1; a++) {
					for (int b = -1; b <= 1; b++) {
						if (i + a >= 0 || i + a <= ROW) {
							if (j + b >= 0 || j + b <= COL) {
								if (Math.abs(a) == Math.abs(b)) {
									BoardCell tempT = new BoardCell(i + a,j + b);
									tempSet.add(tempT);
								}
							}
						}
					}
				}
				adjMtx.put(temp, tempSet);
				tempSet.clear();
			}
		}
	}
	
	public Set<BoardCell> getAdList(int row, int col) {
		BoardCell temp = new BoardCell(row,col);
		return adjMtx.get(temp);
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		for (int i = 0; i < ROW + 1; i++) {
			for (int j = 0; j < COL + 1; j++) {
				if (i + j == pathLength) {
					BoardCell temp = new BoardCell(i,j);
					targets.add(temp);
				}
			}
		}
	}
	
	public void getTargets() {
		
	}
	
	
	
}
