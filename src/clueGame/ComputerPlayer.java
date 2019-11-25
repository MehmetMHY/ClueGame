/**. 
 * ComputerPlayer class which extends to Player class,
 * its sued for determining the values and controlling,
 * the actions of the computer/AI players
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.awt.Color;

public class ComputerPlayer extends Player {
	private Solution myAccusation;
	private boolean makeAccusation;
	
	// constructor for ComputerPlayer class
	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = color;
		makeAccusation = false;
	}
	
	// equals method for ComputerPlayer to check if ComputerPlayer objects are equal to one another
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
	
	// pickLocation method is used to pick a certain location for the computerPlayer based on targets BoardCell set
	public BoardCell pickLocation(Set<BoardCell> targets) {
		BoardCell temp = new BoardCell(0,0);
		int i = 0;
		int index = new Random().nextInt(targets.size()); // store random target cell
		// loop though targets set
		for(BoardCell point : targets) {
			// if a target cell is a Door Way, set the new location for the computerPlayer to that Door Way cell
			if(point.isDoorway()) {
				return point;
			// when the loop reaches the random determined index, set temp equal to that point
			}else if(i == index){
				temp = point;
			}
			i++;
		}
		// return a random target cell location for the computerPlayer if there are no Door Ways
		return temp;
	}

	// createSuggestion method creates Suggestions for the computerPlayer(s)
	public Solution createSuggestion(Board board) {
		
		// if only one person is seen, that person is selected for the suggestion
		Card player = new Card("temp");
		for (Card c:board.getPlayerDeck()) {
			if (!Player.getSeenPlayers().contains(c)) {
				player.setCardName(c.getCardName());
			}
		}
		
		// if only one room is seen, that room is selected for the suggestion
		Card curLoc = new Card("temp");
		String cardName = board.getLegend().get(board.getCellAt(row, column).getInitial());
		for (Card c:board.getRoomDeck()) {
			if (c.getCardName().equals(cardName)) {
				curLoc.setCardName(c.getCardName());
			}
		}
		
		// if only one weapon is seen, that weapon is selected for the suggestion
		Card weapon = new Card("temp");
		for (Card c:board.getWeaponDeck()) {
			if (!this.getSeenWeapons().contains(c)) {
				weapon.setCardName(c.getCardName());
			}
		}
		
		// select a Solution object as the selected player, room, and weapon
		Solution solution = new Solution(player, curLoc, weapon);
		
		// return that Solution object as theSuggestion
		return solution;
	}

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
	
	public boolean isMakeAccusation() {
		return makeAccusation;
	}

	public void setMakeAccusation(boolean makeAccusation) {
		this.makeAccusation = makeAccusation;
	}
	
	public Solution getMyAccusation() {
		return myAccusation;
	}

	public void setMyAccusation(Solution myAccusation) {
		this.myAccusation = myAccusation;
	}

	public void makeAccusation(Solution accusation) {
		setMyAccusation(accusation);
	}
}
