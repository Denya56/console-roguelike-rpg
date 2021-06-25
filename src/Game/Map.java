package Game;

public class Map {
    private String[][] map = new String[51][51];

    public Map() {

    }

    public String[][] getMap() {
        return map;
    }

    public void changeMap(String[][] tmp) {
        map = tmp;
    }

    public void setMap(Player pl) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                mapRefill(i, j);
        }
        map[pl.getY()][pl.getX()] = " @ ";
    }

    public void showMap(Player pl) {
        for (int i = Math.max(pl.getY() - 5, 0); i < Math.min(pl.getY() + 6, map.length); i++) {
            for (int j = Math.max(pl.getX() - 5, 0); j < Math.min(pl.getX() + 6, map[i].length); j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean setTree() {
        return Math.random() > 0.95;
    }

    private boolean setRock() {
        return Math.random() > 0.95;
    }

    private boolean setShop() {
        return Math.random() > 0.99;
    }

    private boolean setBoss() {
        return Math.random() > 0.999;
    }

    public void mapRefill(int i, int j) {
        if(setBoss()) {
            map[i][j] = " * ";
        } else if (setTree()) {
            map[i][j] = " T ";
        } else if (setRock()) {
            map[i][j] = " R ";
        } else if(setShop()) {
            map[i][j] = " M ";
        } else map[i][j] = " . ";
    }
}
