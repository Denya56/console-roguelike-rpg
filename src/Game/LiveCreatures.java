package Game;

public class LiveCreatures {
    protected int healthPoints;
    protected int attackPoints;
    protected int level;

    public LiveCreatures(int healthPoints, int attackPoints, int level) {
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.level = level;
    }
}