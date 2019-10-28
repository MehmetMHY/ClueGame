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

	@Override
	public Card disproveSuggestion(Solution suggection) {
		// TODO Auto-generated method stub
		return null;
	}
}
