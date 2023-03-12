package up_or_down;
import java.util.Scanner;

public class Driver {
	private static Scanner in = new Scanner(System.in);
	private static int numberOfPlayers, difficultyLevel, visibleFor;

	// Gets initial information needed from user to prepare game, creates the board, and begins the game. 
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello! Welcome to up or red");
		
		help();
		
		setGameSettings();
		
		Board board = new Board(numberOfPlayers, difficultyLevel, visibleFor);
		
		board.play();
	}
	
	// Gives an explanation of the game. 
	public static void help() {
		System.out.println("Woud you like an explanation of the game? Input 'yes' if you would like help");
		
		String userInput = in.nextLine();
		
		userInput = userInput.toLowerCase();
		
		if (userInput.equals("yes")) {
			System.out.println("\n\n\nWelcome to the \"Up or Down\" game!");
			System.out.println("In this game, you will be given a card and you must guess\n" 
					+ "whether it is higher or equal to 7 ('high') or lower ('low') than 7.\n"
					+ "You can also choose to guess the color of the card by saying 'red' or 'black'.");
			System.out.println("If you guess correctly, you will move on to the next card. You will\n" 
					+ "continue to guess the cards until you have gone through all of them.");
			System.out.println("But be careful! If you make a mistake at any point, the cards\nwill " 
					+ "reset and it will become the next player's turn.");
			System.out.println("Good luck!\n\n\n");
			
		}
	}
	
	/* 
	 * This method prompts the user to input game settings such as the number of players, 
	 * difficulty level, and visibility of guesses. It sets default values for each setting 
	 * and validates user input to ensure it falls within acceptable ranges.
	 */
	public static void setGameSettings() {
		
		int minimumNumberOfPlayers = 1;
		int maximumNumberOfPlayers = 10;
		int defalutNumberOfPlayers = 1;
		
		
		System.out.println("\nBefore we begin, please input the following information:\n");
		System.out.println("How many players will be playing today? (" + minimumNumberOfPlayers + "-" + maximumNumberOfPlayers + ")");
		
		numberOfPlayers = setGameValue(minimumNumberOfPlayers, maximumNumberOfPlayers, defalutNumberOfPlayers, false);

		
		int minimumDifficultyLevel = 4;
		int maximumDifficultyLevel = 49;
		int defaultDifficultyLevel = 16;
		
		System.out.println("\nWhat level difficulty would you like to play today?"
				+ " \nPlease ensure that input is a perfect square between " + minimumDifficultyLevel + "- " + maximumDifficultyLevel);
		
		difficultyLevel = setGameValue(minimumDifficultyLevel, maximumDifficultyLevel, defaultDifficultyLevel, true);
		
		int minimumViewFor = 0;
		int maximumViewFor = difficultyLevel;
		int defaultViewFor = difficultyLevel/2;
		
		System.out.println("If you get a card right, for how many views after should you be able to see what you guessed? (" + minimumViewFor + "-" + maximumViewFor + ")");
		
		visibleFor = setGameValue(minimumViewFor, maximumViewFor, defaultViewFor, false);
	}
	
	/* Receives a number for the user. 
	 * 
	 * {parameters}: 
	 * minValue: Minimum acceptable value
	 * maxValue: Maximum acceptable value
	 * defaultValue: In case of input not being acceptable, the default value returned
	 * perfectSquare: Does input need to be a perfect square.
	 * 
	 * returns user input or defaultValue
	 * 
	 */
	public static int setGameValue(int minValue, int maxValue, int defaultValue, boolean perfectSquare) {
		String userInput = in.nextLine();
		int valueRecieved;
		 
		// When user input not a Integer
		try {
			valueRecieved = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("Input was not a number. The number will be set to default: " + defaultValue);
			return defaultValue;
		}
		
		// When user input not meeting constraints. 
		
		if (valueRecieved < minValue) {
			System.out.println("Input was lower than minimum amount. Number will be set to default: " + defaultValue);
			valueRecieved = defaultValue;
		} else if (valueRecieved > maxValue) {
			System.out.println("Input was higher than maximum amount. Number will be set to default: " + defaultValue);
			return defaultValue;
		}
		
		if (!perfectSquare) return valueRecieved;
		
		if (!isPerfectSquare(valueRecieved)) {
			System.out.println("Input was not a perfect square. The number will be set to default: " + defaultValue);
			return defaultValue;
		}
		
		return valueRecieved;
	}
	
	// Returns true if perfect square
	public static boolean isPerfectSquare(int num) {
		for (int a = 1; a < num; a++) {
			if (num / a == a) return true;
		}
		return false;
	}
	
	

}






