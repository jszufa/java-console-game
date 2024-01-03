import java.util.Random;

public class Walls {

    Random generator = new Random();
    public Coordinates[] positions;
    Coordinates[] initialPosition;
    char symbol;
    int randomWallsNumber;

    public Walls(char[][] map, int randomWallsNumber, char initialSymbol) {
        symbol = initialSymbol;
        this.randomWallsNumber = randomWallsNumber;
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

        //Logic for random walls
        //for (int i =0; i < randomWallsNumber; i++) {
            //warunek randomWalls number < height - 3
          // Wall randomWall = new Wall (map, symbol);
        //}

        //może zrobić osobny obiekt dla ramki, a osobny dla ranodm walls, a nie wrzucać wszystkiego w logikę walls. To brzmi sensownie.

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

