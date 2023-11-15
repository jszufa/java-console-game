import java.util.Random;

public class Game {

    int height = 10;
    char[][] charMatrix = new char[height][height];
    Hero hero = new Hero(height);


    public Game() {
        createFrame();
        charMatrix[hero.position.x][hero.position.y] = 'H';
    }

    public void createFrame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1)
                    charMatrix[i][j] = '+';

                else
                    charMatrix[i][j] = ' ';
            }
        }
    }


    public void printCharArray() {
        for (char[] row : charMatrix) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }

    public void handleCommand(String input) {
        char command = input.charAt(0);

        charMatrix[hero.position.x][hero.position.y] = ' ';

        hero.moveHero(command);

        charMatrix[hero.position.x][hero.position.y] = 'H';
    }
}
