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
	static  ArrayList<Card> seenRooms = new ArrayList<Card>(); // stores all the seen Players by the player
	
	protected ArrayList<Card> myCards = new ArrayList<Card>(); // stores the cards the player has
	protected String playerName; // stores the player's name
	protected int row; // stores the row position of the player
	protected int column; // stores the column position of the player
	protected Color color; // stores the color value of the player
	public final static int NUM_WEAPONS = 6; // final value for the number of possible weapons
	public final static int NUM_PLAYERS = 6; // final value for the number of possible players
		
	abstract public Card disproveSuggestion(Solution suggestion);
	
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
	
	public static void addSeenRooms(Card newCard) {
		seenRooms.add(newCard);
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

	public void addCards(Card newCard) {
		myCards.add(newCard);
	}
	
}
