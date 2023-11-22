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

        //check for special words (maybe should store all special words in some place?)
        if(input.toLowerCase().equals("quit") ||
                input.toLowerCase().equals("reset")) {
            return;
        }

        char command = input.charAt(0);
        if ( command != 'w' && command != 's' && command != 'a' && command != 'd'  ){
            System.out.println("Command not recognized.");
            System.out.println("Use W, S, A, D to move around.");
            System.out.println("Type QUIT to quit the game or RESET to reset the level.");
            return;
        }

        Coordinates futureMove = hero.checkRoad(command, hero.position);

        //stone
        if (map[futureMove.x][futureMove.y] == 'O') {
            Coordinates futureStoneMove = stone.checkRoad(command, stone.position);
            if (map[futureStoneMove.x][futureStoneMove.y] == 'X') {
                stone.move(command, map);
                hero.move(command, map);
                victory = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] == '8') {
                stone.move(command, map);
                hero.move(command, map);
                gameOver = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] != '+') {

                stone.move(command, map);
                hero.move(command, map);
            }
        }

        //hole or trap
        else if (map[futureMove.x][futureMove.y] == 'X' || map[futureMove.x][futureMove.y] == '8') {
            hero.move(command, map);
            gameOver = true;
        }

        //wall (last condition)
        else if (map[futureMove.x][futureMove.y] != '+') {

            hero.move(command, map);
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
