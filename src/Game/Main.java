package Game;

import java.util.Scanner;

public class Main {
    private static boolean works = true;

    public static void main(String[] args) {
        final Player pll = new Player();
        final Menu men = new Menu();
        final Events evv = new Events();
        final Map map = new Map();
        final Shop sh = new Shop();
        final Battle bt = new Battle();

        System.out.println("Welcome to InsertName!\n" +
                "(Pro tip №1: read Info if this is your first time)\n");
        evv.setPlayer(pll);
        evv.setMap(map);
        men.setEvents(evv);
        men.mainMenu();
        evv.setShop(sh);
        evv.setBattle(bt);
        bt.setEvents(evv);

        while (works) {
            for (int i = 0; i < pll.getActions().length - 5; i++) {
                System.out.print(pll.getActions()[i] + "\t");
            }

            System.out.println();

            Scanner scan = new Scanner(System.in);
            String action = scan.nextLine();
            action = action.substring(0, 1).toUpperCase() + action.substring(1).toLowerCase();

            try {
                switch (action) {
                    case "Move" -> evv.changePos();
                    case "Items" -> {
                        if (pll.getItems().length > 1) {
                            pll.showItems();
                        } else System.out.println("You have no items!");
                    }
                    case "Use" -> pll.useItems(evv);
                    default -> throw new WrongException("action");
                }
            } catch (WrongException e) {
                System.err.println(e.getMessage());
            }
            if (evv.die()) works = false;
            if (pll.getBossDefeated()) {
                evv.win();
                works = false;
            }
        }
    }
}