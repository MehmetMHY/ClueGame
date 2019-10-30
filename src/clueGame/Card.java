package clueGame;

public class Card {
	private CardType type;
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
