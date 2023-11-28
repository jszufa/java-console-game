public class Game {
    ConsoleHandlerImpl console = new ConsoleHandlerImpl();
    boolean victory = false;
    boolean gameOver = false;

    public Game(int levelCount, int mapHeight) {
        String input;

        //game loop
        outerloop:
        for (int i = 1; i <= levelCount; i++) {
            Level actualLevel = new Level("Level" + i, mapHeight);

            while (true) {
                clearConsole();
                printLevelLabel(actualLevel);
                printMap(actualLevel);

                if (i == levelCount && actualLevel.completed) {
                    victory = true; //just for clarity, there is nothing functional about it yet ;)
                    printVictory();
                    break;
                }
                else if (actualLevel.completed) {
                    continue outerloop;
                }
                else if (gameOver) {
                    printGameOver(); //problem to solve - gameOver prints but under next level...
                    break;
                }


                printInputMessage();
                input = console.readInput();
                if (input.equalsIgnoreCase("reset")) {
                    actualLevel.resetLevel();
                    continue;
                }
                if (input.equalsIgnoreCase("quit")) {
                    break outerloop;
                }

                MapService.handleCommand(input, actualLevel, this);
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

    public static void clearConsole() {
        ConsoleHandlerImpl console = new ConsoleHandlerImpl();

        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            console.displayOutputLn();
        }
    }
}
