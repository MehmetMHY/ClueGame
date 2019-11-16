/**. 
 * HumanPlayer class that stores the values for the human player,
 * in the ClueGame.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {
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
		// TODO Auto-generated method stub
		return null;
	}
}
