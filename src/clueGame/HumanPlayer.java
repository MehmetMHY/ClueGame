/**. 
 * HumanPlayer class that stores the values for the human player,
 * in the ClueGame.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer extends Player {
	// TESTING - TODO
	private Solution humanSolution;
	
	// constructor of for HumanPlayer
	public HumanPlayer(String name, int row, int col, Color color) {
		super();
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = color;
	}
		
	// equals method for comparing HumanPlayer objects
	public boolean equals(HumanPlayer temp) {
		if(!temp.playerName.equals(this.playerName)) {
			return false;
		}
		if(temp.row != (this.row)) {
			return false;
		}
		if(temp.column != (this.column)) {
			return false;
		}
		if(!temp.color.equals(this.color)) {
			return false;
		}
		return true;
	}

	@Override
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

	public Solution getHumanSolution() {
		return humanSolution;
	}

	public void setHumanSolution(Solution humanSolution) {
		this.humanSolution = humanSolution;
	}
}
