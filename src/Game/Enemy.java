package Game;

public class Enemy extends LiveCreatures{
    private final String[] typesList = {"Rat", "Goblin", "Goblin Spearman", "Wolf"};
    private String type;
    private final int possibleXP;

    public Enemy(int healthPoints, int attackPoints, int level, int xp) {
        super(healthPoints, attackPoints, level);
        this.possibleXP = xp;
    }

    public Enemy(int healthPoints, int attackPoints, int level, int xp, String type) {
        super(healthPoints, attackPoints, level);
        this.possibleXP = xp;
        this.type = type;
    }

    public String toString() {
        return getType() + "\nHP: " + healthPoints + "\nLVL: " + level + "\n";
    }

    public String getType() {
        return type;
    }

    public int getPossibleXP() {
        return possibleXP;
    }

    public void setType() {
        type = typesList[(int)(Math.random()* typesList.length)];
    }

    public void setType(String tp) {
        type = tp;
    }
}
