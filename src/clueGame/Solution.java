/**. 
 * Solution is a class that handles what the solution is to the ClueGame.
 * The solution being who did it, where they did it, and what they used,
 * to do it.
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

public class Solution {
	public String person; // stores the value of the person who did it
	public String room; // stores the value of the room the person did it at
	public String weapon; // stores what weapon the user used
	
	// constructor for Solution class with parameters (Card, Card, Card)
	public Solution(Card who, Card where, Card what) {
		super();
		this.person = who.getCardName();
		this.room = where.getCardName();
		this.weapon = what.getCardName();
	}
	
	// constructor for Solution class with parameters (Solution)
	public Solution(Solution s) {
		super();
		this.person = s.person;
		this.room = s.person;
		this.weapon = s.weapon;
	}

	// equals method for Solution object. Checks to make sure a Solution, 
	// object is equal to another desired object:
	public boolean equals(Solution answer) {
		if(!answer.person.equals(this.person)) {
			return false;
		}
		if(!answer.room.equals(this.room)) {
			return false;
		}
		if(!answer.weapon.equals(this.weapon)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Solution [person=" + person + ", room=" + room + ", Weapon=" + weapon + "]";
	}
	
	
}
