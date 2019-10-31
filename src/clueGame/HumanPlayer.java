package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {
	private String name;
	private int row;
	private int col;
	private Color color;
	
	public HumanPlayer(String name, int row, int col, Color color) {
		super();
		this.name = name;
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public boolean equals(HumanPlayer temp) {
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
	public Card disproveSuggestion(Solution suggection) {
		// TODO Auto-generated method stub
		return null;
	}
}
