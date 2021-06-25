package Game;

import java.util.Scanner;

public class Shop {
    private final String[] thingsPrices = {"Health potion - 50", "Mana potion - 50", "Emperor scroll - 500"};

    public Shop() {
    }

    public void enterShop(Player pl) {
        System.out.println("Greetings traveler!\n");
        for (int i = 5; i < pl.getActions().length; i++) {
            System.out.print(pl.getActions()[i] + "\t");
        }
        System.out.println();

        Scanner scn = new Scanner(System.in);
        String act = scn.nextLine();
        act = act.substring(0, 1).toUpperCase() + act.substring(1).toLowerCase();

        try {
            switch (act) {
                case "Buy" -> buy(pl);
                case "Sell" -> {
                    if (pl.getItems().length < 2) {
                        System.out.println("You have no items!");
                        enterShop(pl);
                    } else {
                        pl.showItems();
                        sell(pl);
                    }
                }
                case "Exit" -> exit();
                default -> throw new WrongException("item");
            }
        } catch (WrongException e) {
            System.out.println(e.getMessage());
            enterShop(pl);
        }
    }

    private void buy(Player pl) {
        for (String thingsPrice : thingsPrices) {
            System.out.print(thingsPrice + "\t");
        }
        System.out.println();

        Scanner sc = new Scanner(System.in);
        String thing = sc.nextLine();
        thing = thing.substring(0, 1).toUpperCase() + thing.substring(1).toLowerCase();

        try {
            switch (thing) {
                case "Health potion", "Mana potion" -> {
                    if (pl.getMoney() < 50) {
                        System.out.println("You don't have enough money!");
                    } else {
                        pl.addMoney(-50);
                        pl.gainItems(thing);
                    }
                }
                case "Emperor scroll" -> {
                    if (pl.getMoney() < 500) {
                        System.out.println("You don't have enough money!");
                    } else {
                        pl.addMoney(-500);
                        pl.gainItems(thing);
                    }
                }
                default -> throw new WrongException("item");
            }
            enterShop(pl);
        } catch (WrongException e) {
            System.out.println(e.getMessage());
            enterShop(pl);
        }
    }

    private void sell(Player pl) {

        Scanner scan = new Scanner(System.in);
        String it = scan.nextLine();
        it = it.substring(0, 1).toUpperCase() + it.substring(1).toLowerCase();

        try {
            switch (it) {
                case "Health potion", "Mana potion" -> {
                    pl.addMoney(15);
                    pl.loseItems(it);
                }
                case "Emperor scroll" -> {
                    pl.addMoney(150);
                    pl.loseItems(it);
                }
                default -> throw new WrongException("item");
            }
            enterShop(pl);
        } catch (WrongException e) {
            System.err.println(e.getMessage());
            enterShop(pl);
        }
    }

    private void exit() {
        System.out.println("You quit the shop!");
    }
}


