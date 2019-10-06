/**. 
 * BoardCell class that sets row and col and acts as an object
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package experiment;

public class BoardCell {
	private int row; // row value for BoardCell object
	private int col; // col value for BoardCell object
	
	// constructor for BoardCell that sets the row and col for the BoardCell object
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}
}
