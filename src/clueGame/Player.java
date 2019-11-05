package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

abstract class Player {

	static  ArrayList<Card> seenWeapons = new ArrayList<Card>();
	static  ArrayList<Card> seenPlayers = new ArrayList<Card>();
	
	protected ArrayList<Card> myCards = new ArrayList<Card>();
	protected String playerName;
	protected int row;
	protected int column;
	protected Color color;
	public final static int NUM_WEAPONS = 6;
	public final static int NUM_PLAYERS = 6;
	
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> temp = new ArrayList<Card>();
		for (Card c:myCards) {
			if (c.getCardName().equals(suggestion.person)) {
				temp.add(c);
			} else if (c.getCardName().equals(suggestion.room)) {
				temp.add(c);
			} else if (c.getCardName().equals(suggestion.weapon)) {
				temp.add(c);
			}
		}
		if (temp.isEmpty()) {
			return null;
		} else if (temp.size() == 1) {
			return temp.get(0);
		} else {
			Random r = new Random();
			int num = r.nextInt(temp.size());
			return temp.get(num);
		}
	}
	
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

	public static ArrayList<Card> getSeenPlayers() {
		return seenPlayers;
	}

	public static void addSeenPlayers(Card player) {
		seenPlayers.add(player);
	}
	
	public ArrayList<Card> getSeenWeapons() {
		return seenWeapons;
	}

	public static void addSeenWeapons(Card newCard) {
		seenWeapons.add(newCard);
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

	public void addCards(Card newCard) {
		myCards.add(newCard);
	}
	
}
