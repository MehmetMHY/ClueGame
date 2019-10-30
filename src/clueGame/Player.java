package clueGame;

import java.awt.Color;
import java.util.ArrayList;

abstract class Player {

	private ArrayList<Card> seenCards = new ArrayList<Card>();
	static ArrayList<Card> myCards = new ArrayList<Card>();
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
		return this.color;
	}

	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}

	public void addSeenCards(Card newCard) {
		seenCards.add(newCard);
	}

	public static ArrayList<Card> getMyCards() {
		return myCards;
	}

	public static void setMyCards(ArrayList<Card> myCards) {
		Player.myCards = myCards;
	}
	
}
