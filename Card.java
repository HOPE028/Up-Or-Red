package up_or_down;

public class Card {
	private int value;
	private char suits;
	private int remainingVisibleFor;
	
	public Card(int value, char suits, int remainingVisibleFor) {
		this.value = value;
		this.suits = suits;
		this.remainingVisibleFor = remainingVisibleFor;
	}
	
	// Returns card info if user has access to it or returns non-visible info. 
	// Every time called, it will decrease the amount of future instances the card can be seen.
	public String showCardInfo() {
		if (remainingVisibleFor > 0) {
			remainingVisibleFor--;
			return Integer.toString(this.value) + Character.toString(this.suits);
		}
		return "■■";
	}
	
	// Returns if guess is correct. 
	public boolean correctGuess(String guess) {
		guess = guess.toLowerCase();
		
		if (guess.equals("high") && this.value >= 7) {
			return true;
		} else if (guess.equals("low") && this.value < 7) {
			return true;
		} else if (guess.equals("red") && this.suits == '♦' || guess.equals("red") && this.suits == '♥') {
			return true;
		} else if (guess.equals("black") && this.suits == '♣' || guess.equals("black") && this.suits == '♠') {
			return true;
		}
		
		return false;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public char getSuits() {
		return suits;
	}

	public void setSuits(char suits) {
		this.suits = suits;
	}

	public int getRemainingVisibleFor() {
		return remainingVisibleFor;
	}

	public void setRemainingVisibleFor(int remainingVisibleFor) {
		this.remainingVisibleFor = remainingVisibleFor;
	}

}
