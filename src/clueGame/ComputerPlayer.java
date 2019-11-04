package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.awt.Color;

public class ComputerPlayer extends Player {
	private Solution myAccusation;
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = color;
	}
	
	public boolean equals(ComputerPlayer temp) {
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
	public String toString() {
		return "ComputerPlayer [name=" + playerName + ", row=" + row + ", col=" + column + ", color=" + color + "]";
	}


	@Override
	public Card disproveSuggestion(Solution suggection) {
		Card temp = new Card("PlaceHolder");
		return temp;
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
	

	public Solution createSuggestion(Board board) {
		
		Card player = new Card("temp");
		for (Card c:board.getPlayerDeck()) {
			if (!Player.getSeenPlayers().contains(c)) {
				player.setCardName(c.getCardName());
			}
		}
		
		Card curLoc = new Card("temp");
		String cardName = board.getLegend().get(board.getCellAt(row, column).getInitial());
		for (Card c:board.getRoomDeck()) {
			if (c.getCardName().equals(cardName)) {
				curLoc.setCardName(c.getCardName());
			}
		}
		
		Card weapon = new Card("temp");
		for (Card c:board.getWeaponDeck()) {
			if (!this.getSeenWeapons().contains(c)) {
				weapon.setCardName(c.getCardName());
			}
		}
		

		
		Solution solution = new Solution(player, curLoc, weapon);
		return solution;
	}


}
