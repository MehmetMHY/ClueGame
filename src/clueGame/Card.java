/**. 
 * Card class for card (players, rooms, & weapons) objects for each player
 * 
 * @author Mehmet Yilmaz
 * @author Ruidi Huang
 */

package clueGame;

public class Card {
	private CardType type; // stores the type of card
	private String cardName; // stores the name of the card
	
	// constructor for Card class
	public Card(String name) {
		cardName = name;
	}

	// equal method for Cards, checks if Card objects are equal to one another
	public boolean equals(Card temp) {
		return this.cardName.equals(temp.getCardName());
	}
	
	@Override
	public String toString() {
		return cardName;
	}

	public String getCardName() {
		return cardName;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
