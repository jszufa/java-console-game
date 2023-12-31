public class Game {

    int height = 6;
    char[][] map = new char[height][height];

    boolean victory = false;
    boolean gameOver = false;
    Hero hero = new Hero(height, map, 'H');
    Stone stone = new Stone(height, map, 'O');
    Hole hole = new Hole(height, map, 'X');
    Trap trap = new Trap(height, map, '8');


    public Game() {
        createFrame();
        hero.newPosition(map);
        stone.newPosition(map);
        hole.newPosition(map);
        trap.newPosition(map);
    }

    public void createFrame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1)
                    map[i][j] = '+';

                else
                    map[i][j] = ' ';
            }
        }
    }


    public void printCharArray() {
        for (char[] row : map) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }



    public void handleCommand(String input) {
        char command = input.charAt(0);
        Coordinates futureMove = hero.checkRoad(command);

        //stone
        if (map[futureMove.x][futureMove.y] == 'O') {
            Coordinates futureStoneMove = stone.checkRoad(command);
            if (map[futureStoneMove.x][futureStoneMove.y] == 'X') {
                stone.move(command, map);
                hero.move(command, map);
                victory = true;
            }
            else if (map[futureStoneMove.x][futureStoneMove.y] == '8') {
                stone.move(command, map);
                hero.move(command, map);
                gameOver = true;
            }
            else if (map[futureStoneMove.x][futureStoneMove.y] != '+') {

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
