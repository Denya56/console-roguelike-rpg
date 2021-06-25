package Game;

import java.util.Scanner;

public class Menu {
    private Events ev;

    public Menu() {

    }

    public void setEvents(Events evv) {
        ev = evv;
    }

    public void mainMenu() {
        System.out.println("→ Start\n" +
                "→ Info");

        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        option = option.substring(0, 1).toUpperCase() + option.substring(1).toLowerCase();
        try {
            if (option.equals("Info")) {
                System.out.println(showDescription());
                mainMenu();
            } else if (option.equals("Start")) {
                ev.gameStart();
            } else {
                throw new WrongException("action");
            }
        } catch(WrongException e) {
            System.err.println(e.getMessage());
            mainMenu();
        }
    }

    public static String showDescription() {
        return "Basics:\n\n" +
                "The game starts after you chose a class\n" +
                "Difficulty depends on class you choose. Basically, it is in the following way:\n\n" +
                "Warrior - Easy\n" +
                "Scout - Medium\n" +
                "Wizard - Hard\n" +
                "Warrior has high HP and Att stat\n" +
                "Scout has high Att stat\n" +
                "Wizard has high Att stat but when you perform an attack you lose one Mana point\n\n" +
                "HP is your Health Points. If it drops to 0 you lose\n" +
                "Att is your attack. This is how much damage you deal to enemies\n" +
                "Mana is your mana points. It is available only for Wizard. It regenerates when you move or by potions\n" +
                "(Pro tip №2: Mana potions are useless for other classes)\n" +
                "Coins shows how much coins you have(Yes, I'm mister helpful). You can buy items or upgrades in shops for coins\n" +
                "Gain needed XP to levelup and increase your stats(Pro tip №3: monsters will become more powerful as well)\n\n" +
                "Map:\n\n" +
                "@ is you\n" +
                "T is tree\n" +
                "R is rock\n" +
                "M is merchant(just move in a way M is located to enter a shop)\n" +
                "* is boss. Defeat him to complete the game\n" +
                "(Pro tip №4: I'd not recommend to fight him until level 10)\n\n" +
                "Actions:\n\n" +
                "Move → move by one tile on a map\n" +
                "You can choose direction in the following way:\n" +
                "\tN\n" +
                "\t↑\n" +
                "W ←\t " +
                " → E\n" +
                "\t↓\n" +
                "\tS\n\n" +
                "(each letter corresponds to map directions: North, East, South, West)\n\n" +
                "Items → shows your items(use them by typing their name)\n" +
                "Attack → deal damage equal your Att stat to the enemy(appears only if you are in battle)\n" +
                "Flee → try to flee from battle(also appears only if you are in battle)\n" +
                "Buy and Sell → buy and sell items in shop respectively(suppose there's no need to say you can do this only in a shop)\n";
    }
}
