/**. 
 * Player class that stores the values for the all the players,
 * in the ClueGame.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

abstract class Player {

	static  ArrayList<Card> seenWeapons = new ArrayList<Card>(); // stores all the seen Weapons by the player
	static  ArrayList<Card> seenPlayers = new ArrayList<Card>(); // stores all the seen Players by the player
	
	protected ArrayList<Card> myCards = new ArrayList<Card>(); // stores the cards the player has
	protected String playerName; // stores the player's name
	protected int row; // stores the row position of the player
	protected int column; // stores the column position of the player
	protected Color color; // stores the color value of the player
	public final static int NUM_WEAPONS = 6; // final value for the number of possible weapons
	public final static int NUM_PLAYERS = 6; // final value for the number of possible players
	
	// method for disproving suggestions by the player
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> temp = new ArrayList<Card>();
		for (Card c:myCards) {
			// if the suggestion contains a card with the right person, add it to temp
			if (c.getCardName().equals(suggestion.person)) {
				temp.add(c);
			// if the suggestion contains a card with the right room, add it to temp
			} else if (c.getCardName().equals(suggestion.room)) {
				temp.add(c);
			// if the suggestion contains a card with the right weapon, add it to temp
			} else if (c.getCardName().equals(suggestion.weapon)) {
				temp.add(c);
			}
		}
		// if the created temp is empty, return null
		if (temp.isEmpty()) {
			return null;
		// if the created temp is of size 1, return the first index of temp
		} else if (temp.size() == 1) {
			return temp.get(0);
		// else (if temp is larger then 1), return a random index element of temp
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
