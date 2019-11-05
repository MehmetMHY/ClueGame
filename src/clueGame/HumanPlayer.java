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
	private String name; // name of the player
	private int row; // row position of the player
	private int col; // col position of the player
	private Color color; // color value of the player
	
	// constructor of for HumanPlayer
	public HumanPlayer(String name, int row, int col, Color color) {
		super();
		this.name = name;
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	// equals method for comparing HumanPlayer objects
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
}
