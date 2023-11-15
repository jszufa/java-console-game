import java.util.Random;

public class Game {

    int height = 10;
    char[][] charMatrix = new char[height][height];
    Random generator = new Random();

    //warto byłoby zapamiętać pozycję H

    public Game() {
        createFrame();
        setHero();
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

    public void setHero() {

        int rowRandom;
        do  {
            rowRandom = generator.nextInt(height);
        } while (rowRandom == 0);

        int colRandom;
        do  {
            colRandom = generator.nextInt(height);
        } while (colRandom == 0);

        charMatrix[rowRandom][colRandom] = 'H';
    }

    public void printCharArray() {
        for (char[] row : charMatrix) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }

    //handle command -można użyć switchy case:...


}
