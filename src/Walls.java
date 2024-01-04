public class Walls {
    public Coordinates[] positions;
    Coordinates[] initialPosition;
    char symbol;
    int randomWallsNumber;

    public Walls(char[][] map, char initialSymbol) {
        symbol = initialSymbol;
        randomWallsNumber =  map.length > 6 ? map.length - 6 : 0; //maybe to be modified
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

        //random Walls
        for (int i =0; i < randomWallsNumber; i++) {
           Wall randomWall = new Wall (map, symbol);
           positions[wallCounter++] = new Coordinates(randomWall.initialPosition);
        }


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

