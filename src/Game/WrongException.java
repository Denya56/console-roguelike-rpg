package Game;

public class WrongException extends Exception {
    public WrongException(String what) {
        super("Wrong " + what + "!");
    }
}