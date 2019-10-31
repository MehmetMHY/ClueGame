package clueGame;

import java.util.Set;
import java.awt.Color;

public class ComputerPlayer extends Player {
	private String name;
	private int row;
	private int col;
	private Color color;

	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.name = name;
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public boolean equals(ComputerPlayer temp) {
		if(!temp.name.equals(this.name)) {
			return false;
		}
		if(temp.row != (this.row)) {
			return false;
		}
		if(temp.col != (this.col)) {
			return false;
		}
		if(!temp.color.equals(this.color)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "ComputerPlayer [name=" + name + ", row=" + row + ", col=" + col + ", color=" + color + "]";
	}


	@Override
	public Card disproveSuggestion(Solution suggection) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public void makeAccusation() {
		
	}
	
	public void createSugguestion() {
		
	}

}
