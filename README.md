# Up-Or-Red

## Description:

### What is the goal of the game:

A player must traverse a map by guessing and remembering w
hither each spot in the map is greater or smaller then 7 OR if it is black or red. By guessing correctly, they move on to the next card of the map until they have reached the end. If they make a mistake while progressing through the game, they must restart and try again. Depending on how many turns it took to finish the game, you are scored accordingly.

### How is the game set up:
The game is set up on a 2D array of card objects. Using them, a map is built where the player traverses from the top left corner to the bottom right. 
	
### How does gameplay progress:
The players must guess if the card they are on is red or black, or if it is higher or lower than 7. If they guess the card right, they move on to the next card in the map. If they make a mistake, the game is reset and it is the next players turn. 
	
### Win condition:
Once a player has correctly guessed/remembered every card on the map, a message will appear saying they beat the game while stating the number of turns it took them to. The game will then end. 

### Points:
Assuming one successfully completes the game, they will be a given score equivalent to the amount of turns it took them. The lower the score, the better. 

### Extra features:
- Players - Up to ten players can compete against each other! Instead of just playing by yourself, you can play with others. 
	
- Dynamic Game Size - The user gets to choose how many cards the map will have (Must be a perfect square: 4, 9, 16, 25, 36, or 49). 
	
- Card visibility settings -Allow users to choose how long they can see information about a card after successfully guessing it. Setting a limit, such as three guesses, means that only cards guessed three or fewer times before will be visible.
	
- Erases past prints - Using a timer and clear options, the user can only temporarily see previous guesses. 
	
	
	
## Gameplay (User Interface controls)
When the game starts, the user must input some basic game settings to prepare the game. Once the game begins, a user must guess if the card they have to currently predict is higher or equal to 7 ('high') or lower ('low') than 7. They can also choose to guess the color of the card by saying 'red' or 'black'. If they predict the card right, they move on to the next card. They will carry on doing this until they have gone through all the cards. If they make a mistake at some point, however, the cards reset and it becomes the next players turn. 
