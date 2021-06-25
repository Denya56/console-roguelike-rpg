package Game;

import java.util.Scanner;

public class Battle {
    private static boolean active = false;
    private Events evv;

    public Battle() {
    }

    public void setEvents(Events ev) {
        evv = ev;
    }

    private void fight(Player pl, Enemy en) {
        while(active) {
            System.out.println(pl.toString());
            System.out.println(en.toString());

            for(int i = 3; i < pl.getActions().length-3; i++) {
                System.out.print(pl.getActions()[i] + "\t");
            }
            System.out.println();

            Scanner scan = new Scanner(System.in);
            String action = scan.nextLine();
            action = action.substring(0, 1).toUpperCase() + action.substring(1).toLowerCase();

            try {
                if (action.equals("Attack")) {
                    attackEnemy(pl, en);
                } else if (action.equals("Flee")) {
                    flee();
                } else throw new WrongException("action");
            } catch (WrongException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void battleStart(Player pl, boolean boss) {
        Enemy en = new Enemy(1, 1, 1, 1);
        active = true;
        System.out.println("A battle has started!");
        if(boss) {
            en.setType("Ancient Wyvern");
        } else {
            en.setType();
            en.level = pl.level;
        }

        switch (en.getType()) {
            case "Rat" -> en = new Enemy((int) (Math.random()) + (en.level), (int) (Math.random()) + en.level, (int) (Math.random() * 2) + Math.abs(pl.getLevel() - 2), (int)(Math.random() * 3) + (4 + 3 * en.level), en.getType());
            case "Goblin" -> en = new Enemy((int) (Math.random()) + (3 * en.level + 6), (int) (Math.random()) + (2 + en.level), (int) (Math.random() * 2) + Math.abs(pl.getLevel() - 2), (int)(Math.random() * 4) + (7 + 3 * en.level), en.getType());
            case "Goblin Spearman" -> en = new Enemy((int) (Math.random()) + (3 * en.level + 5), (int) (Math.random()) + (3 + en.level), (int) (Math.random() * 2) + Math.abs(pl.getLevel() - 2), (int)(Math.random() * 4) + (7 + 3 * en.level), en.getType());
            case "Wolf" -> en = new Enemy((int) (Math.random()) + (3 * en.level + 6), (int) (Math.random()) + (2 + en.level), (int) (Math.random() * 2) + Math.abs(pl.getLevel() - 2), (int)(Math.random() * 5) + (8 + 3 * en.level), en.getType());
            case "Ancient Wyvern" -> en = new Enemy(100, 25, 10, 10000, en.getType());
        }

        System.out.println("You've encountered a " + en.getType());

        fight(pl ,en);

        if(boss) {
            if(en.healthPoints <= 0) {
                pl.BossDefeated();
            }
        }
    }

    public void attackEnemy(Player pl, Enemy en) {
        if(pl.getPClass().equals("Wizard")) {
            if(pl.getManaPoints() > 0) {
                pl.setManaPoints(1);
            } else System.out.println("Not enough mana!");
        }

        en.healthPoints -= pl.attackPoints;
        System.out.println("You dealt " + pl.attackPoints + " damage");
        if(battleEnd(pl, en, evv)) {
            active = false;
            return;
        }
        pl.healthPoints -= en.attackPoints;
        System.out.println("You've got " + en.attackPoints + " damage");
        if(battleEnd(pl, en, evv)) {
            active = false;
        }
    }

    public void flee() {
        if(Chances.fleeChance()) {
            active = false;
        } else System.out.println("Failed to flee\n");
    }

    private boolean battleEnd(Player pl, Enemy en, Events evv) {
        if(en.healthPoints <= 0) {
            System.out.println(en.getType() + " is defeated");
            pl.addXP(en.getPossibleXP());
            pl.addMoney((int)(Math.random() * 2) + (4 + en.level));
            pl.lvlUp();
            return true;
        } else if(pl.healthPoints <= 0) {
            System.out.println("You've died(as a hero of course)");
            evv.die();
            return true;
        }
        return false;
    }
}
