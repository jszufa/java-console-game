import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";

        var level1 = new Level("Level 1");
        var level2 = new Level("Level 2");
        var level3 = new Level("Level 3");

        Level[] levels = {level1, level2, level3};

        outerloop:
        for (Level level : levels) {

            while (!level.game.victory && !level.game.gameOver) {
                while (!input.toLowerCase().equals("quit")) {

                    if (input.toLowerCase().equals("reset")) {
                        level.resetLevel();
                        System.out.println(level.hero.initialPosition.x);
                        System.out.println(level.hero.initialPosition.y);
                    }

                    clearConsole();
                    System.out.println(level.label);
                    System.out.println();
                    level.game.printMap();


                    if (level.game.victory) {
                        System.out.print("------VICTORY!--------");
                        break;
                    } else if (level.game.gameOver) {
                        level.game.printGameOver();
                        break;
                    }

                    System.out.print("Enter command: ");
                    input = scanner.next();
                    level.game.handleCommand(input, level.hero, level.stone);

                    if (level.game.victory) {
                        System.out.print("------VICTORY!--------");
                        break;
                    } else if (level.game.gameOver) {
                        level.game.printGameOver();
                        break outerloop;
                    }
                }
            }
        }
    }

    public static void clearConsole() {
        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            System.out.println();
        }
    }

}