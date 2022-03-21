# console-roguelike-rpg
Console turn-based roguelike RPG game on Java

Basic:

	1. Storing info about the player:
		Class, Health, Attack, Mana(Wizard only) points, xp, level, coins, X and Y position
		There are 3? classes: Warrior, Scout, Wizard
	2. Placing player on the map:
		@ is a player
	3. Allowing player to move on the board:
		You can choose direction in the following way:
		  	N
                  	↑
		   W ←     → E
                  	↓
                  	S
	4. Added map elements:
		- T is tree
                - R is rock
                - M is merchant(NPC)
                - * is boss
	5. Added randomness in:
		- Player's stats(HP, ATT, Mana(optional)) depend on its class and are randomly set
		- Enemie's stats(HP, ATT) and xp you'll get after defeating it depend on its type 
                  and are randomly set
		- Map elements are randomly set
		- You can randomly encounter an enemy, find a treasure or chest, containing random 
		  amount of coins, potions
		- Sometimes a tree can be a Holy tree 
		  Sometimes a rock can be a special rock
		  Moving in direction where T or R is located you can check if it's the one
		  If so you will get an item with permanent effect
	6. There is an ability to level up:
		When your XP will reach needed amout player's level will increase by one.
		This causes HP and ATT to increase as well depending on class.
	7. 1) There are 8 main actions:
		General:
			a) Move - let you make a move on the board
			b) Items - shows your items
			c) Use - let you use your items
		Combat:
			a) Attack - attack an enemy
			b) Flee - try to flee from battle
		Merchant:
			a) Buy - buy items from Merchant(you can choose which one)
			b) Sell - sell your items to Merchant(you can choose which one)
			c) Exit - exit a shop(Merchant)
	  2) There are some secondary actions:
		Using potions will regenerate your appropriate stats
		Using Emperor's scroll will instantly teleport you to the final boss 
		and starts a battle with it
	8. NPC:
		Merchant actually is an NPC. You can buy and sell items for coins
	9. Lose/Win:
		You lose when your HP drops to or below 0
		You win when you defeat a final boss
	10. Game map is infinte:
		When you move to some direction map increases by 1 element in that direction
There are 2 easter eggs in the game ;)
