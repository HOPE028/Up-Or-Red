package up_or_down;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board {
	private static int numberOfPlayers, difficultyLevel, visibleFor;
	private static Card[][] gameMap;
	private static Player[] players;
	private static int gameMapDimension;
	
	public Board(int numberOfPlayers, int difficultyLevel, int visibleFor) {
		this.numberOfPlayers = numberOfPlayers;
		this.difficultyLevel = difficultyLevel;
		this.visibleFor = visibleFor;
		
		createGameMap(this.difficultyLevel);
		createPlayers(numberOfPlayers);
	}
	
	/*
	 * This method creates the game map, which is a two-dimensional array of Card objects. 
	 * It takes a parameter, sizeOfCards, which represents the total number of cards to be 
	 * created. The method generates an ArrayList of cards using the createCards method, and 
	 * then fills the game map by randomly selecting cards from the ArrayList and placing 
	 * them in the two-dimensional array. The size of the array is determined by taking the 
	 * square root of sizeOfCards.
	 */
	private void createGameMap(int sizeOfCards) {
		Random rand = new Random();
		ArrayList<Card> cards = createCards();
		
		gameMapDimension = (int) Math.sqrt(sizeOfCards);
		this.gameMap = new Card[gameMapDimension][gameMapDimension];
		
		for (int a = 0; a < gameMapDimension; a++) {
			for (int b = 0; b < gameMapDimension; b++) {
				int indexToTake = rand.nextInt(cards.size());
				gameMap[a][b] = cards.get(indexToTake);
				cards.remove(indexToTake);
			}
		}
	}
	
	// Creates and returns an ArrayList of a deck of cards. 
	private ArrayList<Card> createCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for (int suits = 0; suits < 4; suits++) {
			for (int value = 1; value <= 13; value++) {
				switch (suits) {
					case 0:
						cards.add(new Card(value, '♠', 0));
						break;
					case 1:
						cards.add(new Card(value, '♣', 0));
						break;
					case 2:
						cards.add(new Card(value, '♦', 0));
						break;
					case 3:
						cards.add(new Card(value, '♥', 0));
						break;
				}
			}
		}
		
		return cards;
	}
	
	// Creates an array of players
	private void createPlayers(int numberOfPlayers) {
		Scanner in = new Scanner(System.in);
		players = new Player[numberOfPlayers];
		
		System.out.println("Almost ready to player!\n");
		
		for (int a = 0; a < numberOfPlayers; a++) {
			System.out.println("Hey Player " + (a+1) + "! What is your name?");
			String userInput = in.nextLine();
			players[a] = new Player(1, userInput);
		}
			
	}
	
	/* 
	 * This method is the main gameplay loop for a game that involves guessing words 
	 * associated with a grid of cards. The method begins by clearing the screen and 
	 * setting the first player to roll. Then it enters a loop where each player takes 
	 * turns guessing a word associated with a card on the grid. If the guess is correct, 
	 * the player advances to the next row. If the guess is incorrect, the turn is over 
	 * and the next player goes. The loop continues until one player successfully guesses 
	 * all the cards on the grid or all players have had a turn. The method also handles 
	 * scorekeeping and resetting the visibility of the cards after each turn.
	 */
	public void play() throws InterruptedException {
		
		clearScreen();
		
//		int playerToRoll = 0;
		
		for (int playerToRoll = 0; playerToRoll < this.numberOfPlayers; playerToRoll++) {
			players[playerToRoll].sayTurn();
			
			int row = 0;
			int col = 0;
			
			boolean turnOver = false;
			
			do {
				clearScreen();
				printGameMap(col, row);
				String userInput = players[playerToRoll].playerGuess();
				if (gameMap[col][row].correctGuess(userInput)) {
					gameMap[col][row].setRemainingVisibleFor(this.visibleFor);
					row++;
				} else {
					turnOver = true;
				}
				
				// Completed the last card. Game over.
				if (col == this.gameMapDimension-1 && row == this.gameMapDimension) {
					gameOverMessage(playerToRoll);
					return;
				}
				
				// Row Complete. On to next column.
				if (row == this.gameMapDimension) {
					row = 0;
					col++;
				}
				
			} while (!turnOver);
			
			// Player was unsuccessful and thus their score is increased and are alerted they gave the wrong answer. 
			
			players[playerToRoll].increaseScore();
			players[playerToRoll].wrongAnswer();
			
			// Resets the game.
			resetCardVisibility();
			clearScreen();
			
			// If on last player, then reset to first player
			if (playerToRoll == this.numberOfPlayers - 1) {
				playerToRoll = -1;
			}
		}
	}
	
	private void clearScreen() {
		for (int a = 0; a < 100; a++) {
			System.out.println();
		}
	}
	
	// Ends game and sends game over message.
	private void gameOverMessage(int winningPlayerIndex) {
		players[winningPlayerIndex].gameOver();
	}
	
	// Makes all card info not available
	private void resetCardVisibility() {
		for (int col = 0; col < this.gameMapDimension; col++) {
			for (int row = 0; row < this.gameMapDimension; row++) {
				gameMap[col][row].setRemainingVisibleFor(0);
			}
		}
	}
	
	//prints map
	private void printGameMap(int currentColumn, int currentRow) {
		for (int col = 0; col < this.gameMap.length; col++) {
			for (int row = 0; row < this.gameMap[0].length; row++) {
				if (col == currentColumn && row == currentRow) {
					System.out.print("?? ");
				} else {
					// Asks the card for the information it can return
					System.out.print(this.gameMap[col][row].showCardInfo() + " ");
				}
			}
			System.out.println();
		}
	}
	
	// Getters and Setters 

	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public static void setNumberOfPlayers(int numberOfPlayers) {
		Board.numberOfPlayers = numberOfPlayers;
	}

	public static int getDifficultyLevel() {
		return difficultyLevel;
	}

	public static void setDifficultyLevel(int difficultyLevel) {
		Board.difficultyLevel = difficultyLevel;
	}

	public static Card[][] getGameMap() {
		return gameMap;
	}

	public static void setGameMap(Card[][] gameMap) {
		Board.gameMap = gameMap;
	}

	public static Player[] getPlayers() {
		return players;
	}

	public static void setPlayers(Player[] players) {
		Board.players = players;
	}

	public static int getGameMapDimension() {
		return gameMapDimension;
	}

	public static void setGameMapDimension(int gameMapDimension) {
		Board.gameMapDimension = gameMapDimension;
	}

	public static int getVisibleFor() {
		return visibleFor;
	}

	public static void setVisibleFor(int visibleFor) {
		Board.visibleFor = visibleFor;
	}
}







