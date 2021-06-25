package Game;

public class Chances {
    public static boolean enemyChance() {
        return Math.random() > 0.65;
    }

    public static boolean fleeChance() {
        return Math.random() > 0.50;
    }

    public static boolean coinsChance() {
        return Math.random() > 0.85;
    }

    public static boolean chestChance() {
        return Math.random() > 0.98;
    }

    public static boolean potionChance() {
        return Math.random() > 0.988;
    }

    public static boolean holyTreeBranchChance() {
        return Math.random() > 0.995;
    }

    public static boolean luckyRockChance() {
        return Math.random() > 0.995;
    }
}
