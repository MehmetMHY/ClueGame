package clueGame;

import java.awt.Color;

abstract class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	
	abstract public Card disproveSuggestion(Solution suggection);
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Color getColor() {
		return null;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
