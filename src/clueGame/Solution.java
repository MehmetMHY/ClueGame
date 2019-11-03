package clueGame;

public class Solution {
	public String person;
	public String room;
	public String Weapon;
	
	public Solution(Card who, Card where, Card what) {
		super();
		this.person = who.toString();
		this.room = where.toString();
		Weapon = what.toString();
	}

	@Override
	public String toString() {
		return "Solution [person=" + person + ", room=" + room + ", Weapon=" + Weapon + "]";
	}
	
	
}
