public class MapService {

    int height;
    char[][] map;



    public static void handleCommand(String input, Level level, Game game) {

        Hero hero = level.hero;
        Stone stone = level.stone;
        var map = level.map;



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
                level.completed = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] == '8') {
                stone.move(command, map);
                hero.move(command, map);
                game.gameOver = true;
            } else if (map[futureStoneMove.x][futureStoneMove.y] != '+') {

                stone.move(command, map);
                hero.move(command, map);
            }
        }

        //hole or trap
        else if (map[futureMove.x][futureMove.y] == 'X' || map[futureMove.x][futureMove.y] == '8') {
            hero.move(command, map);
            game.gameOver = true;
        }

        //wall (last condition)
        else if (map[futureMove.x][futureMove.y] != '+') {
            hero.move(command, map);
        }
    }
}
