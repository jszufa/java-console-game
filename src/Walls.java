import java.util.Random;

public class Walls {

    Random generator = new Random();
    public Coordinates[] positions;
    Coordinates[] initialPosition;
    char symbol;

    public Walls(char[][] map, int randomWallsNumber, char initialSymbol) {
        symbol = initialSymbol;
        positions = new Coordinates[calculateExpectedWallCount(randomWallsNumber, map)];
        initialPosition = new Coordinates[positions.length];
        setInitialPosition(map);
    }

    public void setInitialPosition(char[][] map) {
        int height = map.length;
        //Frame
        int wallCounter = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1) {

                    positions[wallCounter++] = new Coordinates(i, j);
                    map[i][j] = this.symbol;
                }
            }
        }

        //Logic for random walls - for ex. setRandomWalls

        //Saving initial positions
        for (int i = 0; i < positions.length; i++) {
            initialPosition[i] = new Coordinates(positions[i]);
        }
    }

    private int calculateExpectedWallCount(int randomWallsCount, char[][] map) {
        int height = map.length;
        int frame = (height - 1) * 4;
        return frame + randomWallsCount;
    }

}

