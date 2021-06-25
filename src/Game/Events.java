package Game;

import java.util.Scanner;

public class Events {
    private String dir = null;
    private Player pl;
    private Map mp;
    private Shop sh;
    private Battle btt;

    public Events() {

    }

    public void setBattle(Battle bt) {
        btt = bt;
    }

    public void setMap(Map map) {
        mp = map;
    }

    public void setPlayer(Player pll) {
        pl = pll;
    }

    public void setShop(Shop shop) {
        sh = shop;
    }

    public void gameStart() {
        pl.chooseClass(pl.getPlayClass());
        mp.setMap(pl);
        System.out.println(pl.toString());
        mp.showMap(pl);
    }

    public boolean die() {
        return pl.healthPoints <= 0;
    }

    public void win() {
        System.out.println("You won!\n" +
                "Here is a cake for you!\n" +
                "          ~                  ~\n" +
                "     *                   *                *       *\n" +
                "                  *               *\n" +
                "  ~       *                *         ~    *          \n" +
                "              *       ~        *              *   ~\n" +
                "                  )         (         )              *\n" +
                "    *    ~     ) (_)   (   (_)   )   (_) (  *\n" +
                "           *  (_) # ) (_) ) # ( (_) ( # (_)       *\n" +
                "              _#.-#(_)-#-(_)#(_)-#-(_)#-.#_    \n" +
                "  *         .' #  # #  #  # # #  #  # #  # `.   ~     *\n" +
                "           :   #    #  #  #   #  #  #    #   :   \n" +
                "    ~      :.       #     #   #     #       .:      *\n" +
                "        *  | `-.__                     __.-' | *\n" +
                "           |      `````\"\"\"\"\"\"\"\"\"\"\"`````      |         *\n" +
                "     *     |                                   |    \n" +
                "           |                                  |       ~\n" +
                "   ~   *   |                                 | * \n" +
                "           |                                    |         *\n" +
                "   *    _.-|                                  |-._  \n" +
                "      .'   '.                               .'   `.  *\n" +
                "      :      `-.__                     __.-'      :\n" +
                "       `.         `````\"\"\"\"\"\"\"\"\"\"\"`````         .'\n" +
                "         `-.._                             _..-'\n" +
                "              `````\"\"\"\"-----------\"\"\"\"`````\n" +
                "(Uhh, I'd personally not take this... Maybe it's a lie, who knows?)");
    }

    public void bossBattle() {
        btt.battleStart(pl, true);
    }

    public void changePos() {
        Scanner scan = new Scanner(System.in);
        dir = scan.nextLine().toUpperCase();

        try {
            switch (dir) {
                case "N" -> {
                    for (int i = 0; i < mp.getMap().length; i++) {
                        for (int j = 0; j < mp.getMap()[i].length; j++) {
                            if (mp.getMap()[i][j].equals(" @ ")) {
                                checkObstacles(i, j, -1, 0);
                            }
                        }
                    }
                }
                case "E" -> {
                    for (int i = mp.getMap().length - 1; i >= 0; i--) {
                        for (int j = mp.getMap()[i].length - 1; j >= 0; j--) {
                            if (mp.getMap()[i][j].equals(" @ ")) {
                                checkObstacles(i, j, 0, 1);
                            }
                        }
                    }
                }
                case "S" -> {
                    for (int i = mp.getMap().length - 1; i >= 0; i--) {
                        for (int j = mp.getMap()[i].length - 1; j >= 0; j--) {
                            if (mp.getMap()[i][j].equals(" @ ")) {
                                checkObstacles(i, j, 1, 0);
                            }
                        }
                    }
                }
                case "W" -> {
                    for (int i = 0; i < mp.getMap().length; i++) {
                        for (int j = 0; j < mp.getMap()[i].length; j++) {
                            if (mp.getMap()[i][j].equals(" @ ")) {
                                checkObstacles(i, j, 0, -1);
                            }
                        }
                    }
                }
                default -> throw new WrongException("action");
            }
        } catch (WrongException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkObstacles(int i, int j, int d1, int d2) {
        switch (mp.getMap()[i + d1][j + d2]) {
            case " . " -> {
                mp.getMap()[i + d1][j + d2] = mp.getMap()[i][j];
                moveP(i, j, dir);
            }
            case " T " -> {
                if (Chances.holyTreeBranchChance()) {
                    pl.gainItems("Holy tree branch");
                } else System.out.println("A tree is blocking your way");
            }
            case " R " -> {
                if (Chances.luckyRockChance()) {
                    pl.gainItems("Big rock");
                } else System.out.println("A rock is blocking your way");
            }
            case " M " -> {
                System.out.println("You entered a shop!");
                sh.enterShop(pl);
            }
            case " * " -> btt.battleStart(pl, true);
        }
    }

    private void moveP(int i, int j, String dir) {
        mp.getMap()[i][j] = " . ";
        if (!Chances.enemyChance()) {
            if (Chances.coinsChance()) {
                System.out.println("You found a secret!");
                pl.addMoney((int)(Math.random() * 4) + 1);
            } else if (Chances.chestChance()) {
                System.out.println("You found a CHEST!");
                pl.addMoney((int)(Math.random() * 15) + 40);
            } else if (Chances.potionChance()) {
                String what = Math.random() > 0.5 ? "Health potion" : "Mana potion";
                pl.gainItems(what);
            }
        } else {
            btt.battleStart(pl, false);
        }

        switch(dir) {
            case "S" -> pl.setY();
            case "E" -> pl.setX();

        }
        mapUpgrade();
        System.out.println(pl.toString());
        mp.showMap(pl);

    }

    public void mapUpgrade() {
        switch (dir) {
            case "N" -> {
                String[][] tmp = new String[mp.getMap().length + 1][mp.getMap()[0].length];
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        tmp[i + 1][j] = mp.getMap()[i][j];
                    }
                }
                mp.changeMap(tmp);
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        mp.mapRefill(i, j);
                    }
                }
            }
            case "S" -> {
                String[][] tmp = new String[mp.getMap().length + 1][mp.getMap()[0].length];
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        tmp[i][j] = mp.getMap()[i][j];
                    }
                }
                mp.changeMap(tmp);
                for (int i = mp.getMap().length - 1; i < mp.getMap().length; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        mp.mapRefill(i, j);
                    }
                }
            }
            case "E" -> {
                String[][] tmp = new String[mp.getMap().length][mp.getMap()[0].length + 1];
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        tmp[i][j] = mp.getMap()[i][j];
                    }
                }
                mp.changeMap(tmp);
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = mp.getMap()[i].length - 1; j < mp.getMap()[i].length; j++) {
                        mp.mapRefill(i, j);
                    }
                }
            }
            case "W" -> {
                String[][] tmp = new String[mp.getMap().length][mp.getMap()[0].length + 1];
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = 0; j < mp.getMap()[i].length; j++) {
                        tmp[i][j + 1] = mp.getMap()[i][j];
                    }
                }
                mp.changeMap(tmp);
                for (int i = 0; i < mp.getMap().length; i++) {
                    for (int j = 0; j < 1; j++) {
                        mp.mapRefill(i, j);
                    }
                }
            }
        }
    }
}

