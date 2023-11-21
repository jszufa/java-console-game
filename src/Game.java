public class Game {
    boolean victory = false;
    boolean gameOver = false;

    int height;
    char[][] map;

    public Game(
            Hero hero,
            Stone stone,
            Hole hole,
            Trap trap,
            Walls walls,
            int height,
            char[][] map
    ) {
        this.height = height;
        this.map = map;
    }

    public void printMap() {
        for (char[] row : map) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }


    public void handleCommand(String input, Hero hero, Stone stone) {
        char command = input.charAt(0);
        Coordinates futureMove = hero.checkRoad(command, hero.position);

        //stone
        if (map[futureMove.x][futureMove.y] == 'O') {
            Coordinates futureStoneMove = stone.checkRoad(command, stone.position);
            if (map[futureStoneMove.x][futureStoneMove.y] == 'X') {
                stone.move(command, map, stone.position, stone.symbol);
                hero.move(command, map, hero.position, hero.symbol);
                victory = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] == '8') {
                stone.move(command, map, stone.position, stone.symbol);
                hero.move(command, map, hero.position, hero.symbol);
                gameOver = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] != '+') {

                stone.move(command, map, stone.position, stone.symbol);
                hero.move(command, map, hero.position, hero.symbol);
            }
        }

        //hole or trap
        else if (map[futureMove.x][futureMove.y] == 'X' || map[futureMove.x][futureMove.y] == '8') {
            hero.move(command, map, hero.position, hero.symbol);
            gameOver = true;
        }

        //wall (last condition)
        else if (map[futureMove.x][futureMove.y] != '+') {

            hero.move(command, map, hero.position, hero.symbol);
        }
    }

    public void printGameOver() {
        System.out.println("------YOU-LOST--------");
        System.out.println("------BUT-STILL--------");
        System.out.println("----ENJOY-GOBLIN-SONG-----");
        System.out.println();
        System.out.println("\"Even though you had a map...\n You stupidly fell into our trap. \n Don't cry don't cry \n You'll be our pie\"");
    }
}
