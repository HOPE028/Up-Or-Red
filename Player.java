package up_or_down;
import java.util.Scanner;

public class Player {
	private int score;
	private String name;
	
	public Player(int score, String name) {
		this.score = score;
		this.name = name;
	}
	
	// Returns user guess.
	public String playerGuess() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your guess? (High, Low, Red, Black)");
		
		String userInput = in.nextLine();
		
		userInput = userInput.toLowerCase();
		
		// Checks if input is an acceptable guess.
		while (!userInput.equals("high") && !userInput.equals("low") 
				&& !userInput.equals("red") && !userInput.equals("black")) {
			System.out.println("Your guess is not in acceptable format. Try again: What is your guess? (High, Low, Red, Black)");
			userInput = in.nextLine();
		}
		
		return userInput;
	}
	
	public void increaseScore() {
		this.score++;
	}
	
	public void gameOver() {
		System.out.println("GAME OVER! Congratulations " + this.name + "! You beat the game in " + this.score + " tries!");
	}
	
	public void wrongAnswer() throws InterruptedException {
		System.out.println("\n\nWrong Answer!");
		Thread.sleep(2000);
	}
	
	public void sayTurn() throws InterruptedException {
		System.out.println("It is the turn of " + this.name + "!");
		Thread.sleep(2500);
	}
	
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
