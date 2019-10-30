package clueGame;

public class Card {
	private String cardName;
	
	public Card(String name) {
		cardName = name;
	}

	public boolean equals(Card one, Card two) {
		return (one == two);
	}
	
	@Override
	public String toString() {
		return cardName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
