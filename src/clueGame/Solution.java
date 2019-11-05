package clueGame;

public class Solution {
	public String person;
	public String room;
	public String weapon;
	
	public Solution(Card who, Card where, Card what) {
		super();
		this.person = who.getCardName();
		this.room = where.getCardName();
		this.weapon = what.getCardName();
	}
	
	public Solution(Solution s) {
		super();
		this.person = s.person;
		this.room = s.person;
		this.weapon = s.weapon;
	}

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
