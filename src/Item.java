public interface Item {

    default void newPosition(char[][] map, Coordinates position, char symbol) {
            validatePosition(map, position);
            map[position.x][position.y] = symbol;
    }

    default void clearPosition(char[][] map, Coordinates position) {
        validatePosition(map, position);
        map[position.x][position.y] = ' ';
    }

    default Coordinates checkRoad(char command, Coordinates position) {

        int tempX = position.x;
        int tempY = position.y;

        switch (command) {
            case 'w' -> tempX -= 1;
            case 's' -> tempX += 1;
            case 'a' -> tempY -= 1;
            case 'd' -> tempY += 1;
            default -> {
                throw new IllegalArgumentException("Invalid command passed to checkRoad method");
            }
        }

        if (tempX <0 || tempY < 0) return position;

        return new Coordinates(tempX, tempY);
    }


    void setInitialPosition(char[][] map);

    static void validatePosition(char[][] map, Coordinates position) {
        int numRows = map.length;
        int numCols = map[0].length;
        if (position.x > numRows || position.y > numCols || position.x < 0 || position.y <0 ) {
            throw new IllegalArgumentException("New position cannot be outside of a map");
        }
    }
}


