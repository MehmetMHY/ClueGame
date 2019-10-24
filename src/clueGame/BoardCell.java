/**. 
 * BoardCell class that sets row and col and acts as an object
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

public class BoardCell {
	private int row; // row value for BoardCell object
	private int col; // col value for BoardCell object
	
	private String roomType;
	
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

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}

	public boolean isDoorway() {
		if (roomType.length() == 2 && roomType.charAt(1) != 'N') {
			return true;
		}
		return false;
	}

	public boolean isRoom() {
		if (roomType.charAt(0) != 'W' && roomType.length() == 1) {
			return true;
		}
		return false;
	}
	
	public Object getInitial() {
		return roomType.charAt(0);
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
}
