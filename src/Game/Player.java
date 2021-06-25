package Game;

import java.util.Scanner;

public class Player extends LiveCreatures {
    private int xp;
    private int manaPoints;
    private int money;
    private String[] items = new String[1];
    private String pClass;
    private boolean bossDefeated = false;

    private int x = (int) (Math.random() * 10) + 5;
    private int y = (int) (Math.random() * 10) + 5;
    private final String[] playClass = {"Warrior", "Scout", "Wizard"};
    private final String[] secClass = {"Strider the King", "Blondie the greenleaf", "G the white"};
    private final String[] actions = {"Move", "Items", "Use", "Attack", "Flee", "Buy", "Sell", "Exit"};

    public Player() {
        super(1, 1, 1);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX() {
        x++;
    }

    public void setY() {
        y++;
    }

    public String[] getActions() {
        return actions;
    }

    public String[] getPlayClass() {
        return playClass;
    }

    public String[] getItems() {
        return items;
    }

    public boolean getBossDefeated() {
        return bossDefeated;
    }

    public void BossDefeated() {
        bossDefeated = true;
    }

    public int getLevel() {
        return level;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public String getPClass() {
        return pClass;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int am) {
        money += am;
        System.out.println("You've " + ((am < 0) ? "spent " : "got ") + Math.abs(am) + " coins!");
    }

    public int getXP() {
        return xp;
    }

    public void addXP(int am) {
        xp += am;
        System.out.println("You've got " + am + " XP!");
    }

    public void upgrade() {
        switch (getPClass()) {
            case "Warrior", "Strider the king" -> {
                healthPoints += 7;
                attackPoints += 5;
            }
            case "Scout", "Blondie the greenleaf" -> {
                healthPoints += 4;
                attackPoints += 6;
            }
            case "Wizard", "G the white"-> {
                healthPoints += 2;
                attackPoints += 4;
                manaPoints += 5;
            }
        }
    }

    public void lvlUp() {
        if (xp >= (int)(250 * Math.pow(level, 2 + level*0.05))) {
            level++;
            upgrade();
            System.out.println("You gained level " + level + "!");
        }
    }

    public void setManaPoints(int am) {
        manaPoints -= am;
    }

    public String toString() {
        return "\nClass: " + pClass + "\nLevel: " + level + "\nXP: " + getXP() + " (" + (int)(250 * Math.pow(level, 2 + level*0.05)) + " to next level)" + "\nHP: " + healthPoints + "\nAttack: " + attackPoints + ((manaPoints != 0) ? "\nMana: " + manaPoints : ' ') + "\nCoins: " + money + "\n";
    }

    public void chooseClass(String[] Pclass) {
        System.out.println("Choose a class for your character:");
        for (String aClass : Pclass) {
            System.out.print("\t" + aClass + "\t");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        pClass = scanner.nextLine();

        try {
            pClass = pClass.substring(0, 1).toUpperCase() + pClass.substring(1).toLowerCase();

            switch (getPClass()) {
                case "Warrior" -> {
                    healthPoints = (int) (Math.random() * 9) + 12;
                    attackPoints = (int) (Math.random() * 2) + 4;
                    pClass = getPClass();
                }
                case "Scout" -> {
                    healthPoints = (int) (Math.random() * 4) + 7;
                    attackPoints = (int) (Math.random() * 2) + 6;
                    pClass = getPClass();
                }
                case "Wizard" -> {
                    healthPoints = (int) (Math.random() * 5) + 4;
                    attackPoints = (int) (Math.random() * 3) + 7;
                    manaPoints = 20;
                    pClass = getPClass();
                }
                case "Strider the king" -> {
                    healthPoints = (int) (Math.random() * 20) + 80;
                    attackPoints = (int) (Math.random() * 15) + 30;
                    level = 15;
                    pClass = getPClass();
                }
                case "Blondie the greenleaf" -> {
                    healthPoints = (int) (Math.random() * 15) + 50;
                    attackPoints = (int) (Math.random() * 10) + 40;
                    level = 15;
                    pClass = getPClass();
                }
                case "G the white" -> {
                    healthPoints = (int) (Math.random() * 10) + 45;
                    attackPoints = (int) (Math.random() * 10) + 45;
                    manaPoints = 50;
                    level = 15;
                    pClass = getPClass();
                }
                case "Mellon" -> chooseClass(secClass);
            }

            if (!isClass()) {
                throw new WrongException("class");
            }
        } catch (WrongException e) {
            System.err.println(e.getMessage());
            chooseClass(Pclass);
        }
    }

    private boolean isClass() {
        for (String s : playClass) {
            if (s.equals(pClass)) {
                return true;
            }
        }
        for (String s : secClass) {
            if (s.equals(pClass)) {
                return true;
            }
        }
        return false;
    }

    public void gainItems(String item) {
        items[items.length - 1] = item;
        String[] tmp = new String[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        items = tmp;
        System.out.println("You gained a " + item + "!");
        switch (item) {
            case "Holy tree branch" -> {
                attackPoints += 5;
                System.out.println("Your ATT increased by 5!");
            }
            case "Big rock" -> {
                healthPoints += 10;
                System.out.println("Your HP increased by 10!");
            }
        }
    }

    public void loseItems(String item) {
        String[] tmp = new String[items.length - 1];
        int count = 0;
        for (int i = 0, k = 0; i < items.length; i++) {
            if (count == 0 && items[i].equals(item)) {
                i++;
                count++;
            }
            tmp[k++] = items[i];
        }
        items = tmp;
        System.out.println("You lost a " + item + "!");
    }

    public void showItems() {
        if (items.length > 1) {
            for (int i = 0; i < items.length - 1; i++) {
                System.out.println(items[i]);
            }
        } else System.out.println("You have no items!");
    }

    public void useItems(Events evv) {
        if(getItems().length < 2) {
            System.out.println("You have no items!");
        } else {
            showItems();

            Scanner sc = new Scanner(System.in);
            String item = sc.nextLine();
            item = item.substring(0, 1).toUpperCase() + item.substring(1).toLowerCase();

            for (String s : items) {
                if (item.equals(s)) {
                    switch (item) {
                        case "Health potion" -> healthPoints += 10;
                        case "Mana potion" -> {
                            if (pClass.equals("Wizard")) {
                                manaPoints += 10;
                            } else System.out.println("You cannot use this item");
                        }
                        case "Holy tree branch", "Big rock" -> System.out.println("This item gives permanent effect only");
                        case "Emperor scroll" -> evv.bossBattle();
                    }
                }
            }
        }
    }
}