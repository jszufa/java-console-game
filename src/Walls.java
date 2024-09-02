public class Walls {
    public Coordinates[] positions;
    Coordinates[] initialPositions;
    char symbol;
    int randomWallsNumber;

    public Walls(char[][] map, char initialSymbol) {
        symbol = initialSymbol;
        randomWallsNumber = map.length > 6 ? map.length - 6 : 0; //maybe to be modified
        positions = new Coordinates[calculateExpectedWallCount(map)];
        initialPositions = new Coordinates[positions.length];
        setInitialPositions(map);
    }

    public Walls(GameState.SimpleWalls loadedWalls, char[][] map) {
        symbol = loadedWalls.symbol;
        randomWallsNumber = loadedWalls.randomWallsNumber;
        positions = loadedWalls.positions;
        initialPositions = loadedWalls.initialPositions;
        this.newWallsPositions(map, positions, symbol);
    }

    public void setInitialPositions(char[][] map) {
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
        for (int i = 0; i < randomWallsNumber; i++) {
            Wall randomWall = new Wall(map, symbol);
            positions[wallCounter++] = new Coordinates(randomWall.initialPosition);
        }

        //Saving initial positions
        for (int i = 0; i < positions.length; i++) {
            initialPositions[i] = new Coordinates(positions[i]);
        }
    }

    private int calculateExpectedWallCount(char[][] map) {
        int height = map.length;
        int frame = (height - 1) * 4;
        return frame + randomWallsNumber;
    }

    private void newWallsPositions(char[][] map, Coordinates[] positions, char symbol) {
        for(Coordinates position : positions) {
            validateWallsPosition(map, position);
            map[position.x][position.y] = symbol;
        }
    }

    private static void validateWallsPosition(char[][] map, Coordinates position) {
        int numRows = map.length;
        int numCols = map[0].length;
        if (position.x > numRows || position.y > numCols || position.x < 0 || position.y <0 ) {
            throw new IllegalArgumentException("New position cannot be outside of a map");
        }
    }

}

