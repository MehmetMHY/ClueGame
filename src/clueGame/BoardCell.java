/**. 
 * BoardCell class that sets row and col and acts as an object
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

public class BoardCell {
	private String roomType; // holds what the cell type of a cell
	private int row; // row value for BoardCell object
	private int col; // col value for BoardCell object
	
	// constructor for BoardCell that sets the row and col for the BoardCell object
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	// additional constructor
	public BoardCell(BoardCell cell) {
		super();
		this.row = cell.row;
		this.col = cell.col;
		this.roomType = cell.roomType;
	}

	// checks if a cell is a Doorway
	public boolean isDoorway() {
		if (roomType.length() == 2 && roomType.charAt(1) != 'N' && roomType.charAt(1) != 'X') {
			return true;
		}
		return false;
	}

	// checks if a cell is a Room
	public boolean isRoom() {
		if (roomType.charAt(0) != 'W' && roomType.length() == 1) {
			return true;
		}
		if(roomType.length() == 2 && roomType.charAt(0) != 'W' && roomType.charAt(1) == 'X') {
			return true;
		}
		return false;
	}

	// based on the second char of the roomType, getDoorDirection returns the correcting DoorDirection
	public DoorDirection getDoorDirection() {
		switch(roomType.charAt(1)) {
		case 'U':
			return DoorDirection.UP;
		case 'D':
			return DoorDirection.DOWN;
		case 'L':
			return DoorDirection.LEFT;
		case 'R':
			return DoorDirection.RIGHT;
		}
		return null;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public Object getInitial() {
		return roomType.charAt(0);
	}
	
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}

	public String getRoomType() {
		return roomType;
	}
}
