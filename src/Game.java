public class Game {
    ConsoleHandlerImpl console = new ConsoleHandlerImpl();
    boolean victory = false;
    boolean gameOver = false;
    MapService mapService;
    LevelFactory levelFactory;
    int levelCount;
    int mapHeight;

    public Game(int levelCount, int mapHeight, LevelFactory levelFactory) {
        this.mapService = new MapService();
        this.levelFactory = levelFactory;
        this.levelCount = levelCount;
        this.mapHeight = mapHeight;
    }

    public void start() {
        String input;
        
        //game loop
        outerLoop:
        for (int i = 1; i <= levelCount; i++) {
            Level actualLevel = levelFactory.createLevel("Level" + i, mapHeight);

            while (true) {
                clearConsole();
                printLevelLabel(actualLevel);
                printMap(actualLevel);

                if (i == levelCount && actualLevel.completed) {
                    victory = true; //just for clarity, there is nothing functional about it yet ;)
                    printVictory();
                    break outerLoop;
                } else if (actualLevel.completed) {
                    continue outerLoop;
                } else if (gameOver) {
                    printGameOver();
                    break outerLoop;
                }

                printInputMessage();
                input = console.readInput();
                if (input.equalsIgnoreCase("reset")) {
                    actualLevel.resetLevel();
                    continue;
                }
                if (input.equalsIgnoreCase("quit")) {
                    break outerLoop;
                }
                mapService.handleCommand(input, actualLevel, this);
            }
        }
    }
    

    public void printMap(Level level) {
        for (char[] row : level.map) {
            for (char element : row) {
                console.displayOutput(element + "  ");
            }
            console.displayOutputLn();
        }
    }

    public void printInputMessage() {
        console.displayOutput("Enter command: ");
    }


    public void printVictory() {
        console.displayOutput("------VICTORY!--------");
    }

    public void printGameOver() {
        console.displayOutputLn("------YOU-LOST--------");
        console.displayOutputLn("------BUT-STILL--------");
        console.displayOutputLn("----ENJOY-GOBLIN-SONG-----");
        console.displayOutputLn();
        console.displayOutputLn("\"Even though you had a map...\n You stupidly fell into our trap. \n Don't cry don't cry \n You'll be our pie\"");
    }

    public void printLevelLabel(Level level) {
        console.displayOutputLn(level.label);
    }

    public void clearConsole() {
        ConsoleHandlerImpl console = new ConsoleHandlerImpl();

        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            console.displayOutputLn();
        }
    }
}
