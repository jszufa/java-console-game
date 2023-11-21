import java.util.Random;

public class Walls {

    Random generator = new Random();
    public Coordinates[] positions;
    Coordinates[] initialPosition;
    char symbol;

    public Walls(int height, char[][] matrix, int randomWallsNumber, char initialSymbol) {
        symbol = initialSymbol;
        positions = new Coordinates[calculateExpectedWallCount(height, randomWallsNumber)];
        initialPosition = new Coordinates[positions.length];
        setInitialPosition(height, matrix);
    }

    public void setInitialPosition(int height, char[][] map) {

        if (height < 6) {
            throw new IllegalArgumentException("Height must be 6 or greater.");
        }

        //Frame
        int wallCounter = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1) {

                    positions[wallCounter++] = new Coordinates(i, j);
                    map[i][j] = this.symbol;
                }
                //This (creating empty spaces) should go somewhere else to - draw map, or sth like this
                else
                    map[i][j] = ' ';
            }
        }

        //Logic for random walls
    }

    private int calculateExpectedWallCount(int height, int randomWalls) {
        int frame = (height-1)*4 ;
        return frame+randomWalls;
    }

}

