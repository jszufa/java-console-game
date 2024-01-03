import static java.lang.Character.toLowerCase;

public class MapService {


    public void handleCommand(String input, Level level, Game game) {

        var map = level.map;
        Hero hero = level.hero;
        Stone stone = level.stone;
        Hole hole = level.hole;
        Trap trap = level.trap;
        Walls walls = level.walls;

        char command = toLowerCase(input.charAt(0));
        if (!validateCommand(command)) return;

        Coordinates futureMove = hero.checkRoad(command, hero.position);

        //stone
        if (map[futureMove.x][futureMove.y] == stone.symbol) {
            Coordinates futureStoneMove = stone.checkRoad(command, stone.position);

            if (map[futureStoneMove.x][futureStoneMove.y] == hole.symbol) {
                stone.move(command, map);
                hero.move(command, map);
                level.completed = true;

            } else if (map[futureStoneMove.x][futureStoneMove.y] == trap.symbol) {
                stone.move(command, map);
                hero.move(command, map);
                game.gameOver = true;

            } else if (map[futureStoneMove.x][futureStoneMove.y] == walls.symbol) {
                // no action needed in this case
            }
            else {
                stone.move(command, map);
                hero.move(command, map);
                //tutaj jest potencjalny bug case, jeśli nie będzie ramki. Wtedy stone się nie przesunie (bo taka jest logika "move"), ale hero się przesunie i nadpisze jego pozycję.
            }
        }

        //hole or trap
        else if (map[futureMove.x][futureMove.y] == hole.symbol || map[futureMove.x][futureMove.y] == trap.symbol) {
            hero.move(command, map);
            game.gameOver = true;
        }

        //wall
        else if (map[futureMove.x][futureMove.y] == walls.symbol) {
            // no action needed in this case
        }
        else {
            hero.move(command, map);
        }
    }

    public void printControlsMessage() {
        ConsoleHandlerImpl console = new ConsoleHandlerImpl();

        console.displayOutputLn("Command not recognized.");
        console.displayOutputLn("Use W, S, A, D to move around.");
        console.displayOutputLn("Type QUIT to quit the game or RESET to reset the level.");
    }

    public boolean validateCommand(char commandInput) {
        char command = toLowerCase(commandInput);
        if (command != 'w' && command != 's' && command != 'a' && command != 'd') {
            printControlsMessage();
            return false;
        } else {
            return true;
        }
    }
}
