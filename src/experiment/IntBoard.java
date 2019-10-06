/**. 
 * IntBoard class that creates the grid, adjacency cells set,
 * initialize visited set, create targets set for the character(s),
 * as well as set up BoardCell object. This is the main class for,
 * setting up the grid for the game as well as determine all,
 * targeted and adjacency cells on the grid.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package experiment;

import java.util.*;

public class IntBoard {
	
	private BoardCell[][] grid; // 2D array for board's grid
	private Map<BoardCell, Set<BoardCell>> adjMtx; // set of adjacency cells for the character(s)
	private Set<BoardCell> visited; // set of visited cells from the character(s)
	private Set<BoardCell> targets; // set of "target" cells or cells that the character(s) can move to
	private final static int ROW = 4; // row size of grid
	private final static int COL = 4; // col size of grid
	
	// sets grid's dimensions and cell coordinates as well as set all possible adjacency cells
	public IntBoard() {
		// initialize grid for BoardCell with ROW and COL final static int value
		grid = new BoardCell[ROW][COL];
		// sets BoardCell object for grid for each coordinate of the grid
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		// initialize adjMtx
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		// initialize targets
		targets = new HashSet<BoardCell>();
		// sets adjMtx values
		calcAdjacencies();
	}
	
	// calculates all possible adjacency cells on the board and stores it in the adjMtx set
	public void calcAdjacencies() {
		Set<BoardCell> tempSet;
		BoardCell temp;
		// determines if (ROW+1), (ROW-1), (COL+1), & (COL-1) is possible for each Cell in the grid and stores those coordinates in adjMtx
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
	
	// calculates all possible target cells on the board, based on the pathLength and stores it in the targets set
	public void calcTargets(BoardCell startCell, int pathLength) {
		// loops though each ROW and COL of the grid
		for (int i = 0; i < ROW ; i++) {
			for (int j = 0; j < COL; j++) {
				// loops though each (ROW, COL) coordinate and determines every target based on the startCell and pathLength
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
		return targets;
	}

	/**
	 * @return Returns grid[i][j] based on int i and j, if generated otherwise returns null
	 */
	public BoardCell getCell(int i, int j) {
		return grid[i][j];
	}

	// gets the adjacent cells based on a BoardCell cell
	public Set<BoardCell> getAdjList(BoardCell cell) {
		Set<BoardCell> temp = new HashSet<BoardCell>();
		// loops though adjMtx map and finds the key, based on the field: cell and sets temp as the value to that key
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
