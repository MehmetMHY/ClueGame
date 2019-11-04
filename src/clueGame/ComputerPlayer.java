package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.awt.Color;

public class ComputerPlayer extends Player {
	private String name;
	private int row;
	private int col;
	private Color color;
	private Solution myAccusation;
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.name = name;
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public boolean equals(ComputerPlayer temp) {
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
	
	@Override
	public String toString() {
		return "ComputerPlayer [name=" + name + ", row=" + row + ", col=" + col + ", color=" + color + "]";
	}


	@Override
	public Card disproveSuggestion(Solution suggection) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean correctAccusation(Solution answer) {
		if(this.myAccusation.equals(answer)) {
			return true;
		}else {
			return false;
		}
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		BoardCell temp = new BoardCell(0,0);
		int i = 0;
		int index = new Random().nextInt(targets.size());
		for(BoardCell point : targets) {
			if(point.isDoorway()) {
				return point;
			}else if(i == index){
				temp = point;
			}
			i++;
		}
		return temp;
	}
	
	public void makeAccusation(Solution accusation) {
		myAccusation = accusation;
		//System.out.println(myAccusation);
	}
	
	public Solution createSuggestion(ArrayList<Card> weapons, ArrayList<Card> players) {
		Random r = new Random();
		Card loc = new Card("Bathroom");
		Solution tempSuggestion = new Solution(players.get(r.nextInt(players.size())), loc, weapons.get(r.nextInt(weapons.size())));
		//String room = board.getCellAt(row, col).getRoomType();
		//String name = 
		return tempSuggestion;
	}


}
