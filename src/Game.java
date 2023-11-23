import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    boolean victory = false;
    boolean gameOver = false;

    public Game(int levelCount, int mapHeight) {

        String input = "";
        //game loop
        outerloop:
        for (short i = 1; i <= levelCount; i++) {
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
                    printGameOver();
                    break;
                }

                printInputMessage();
                input = handleInput();
                if (input.toLowerCase().equals("reset")) {
                    actualLevel.resetLevel();
                    continue;
                }
                if (input.toLowerCase().equals("quit")) {
                    break outerloop;
                }

                MapService.handleCommand(input, actualLevel, this);
            }
        }
    }


    public void printMap(Level level) {
        for (char[] row : level.map) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }

    public void printInputMessage() {
        System.out.print("Enter command: ");
    }

    public String handleInput() {
        String input = scanner.next();
        return input;
    }

    public void printVictory() {
        System.out.print("------VICTORY!--------");
    }

    public void printGameOver() {
        System.out.println("------YOU-LOST--------");
        System.out.println("------BUT-STILL--------");
        System.out.println("----ENJOY-GOBLIN-SONG-----");
        System.out.println();
        System.out.println("\"Even though you had a map...\n You stupidly fell into our trap. \n Don't cry don't cry \n You'll be our pie\"");
    }

    public void printLevelLabel(Level level) {
        System.out.println(level.label);
    }

    public static void clearConsole() {
        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            System.out.println();
        }
    }
}
