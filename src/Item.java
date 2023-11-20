public interface Item {

    default void newPosition(char[][] matrix, Coordinates position, char symbol) {
        matrix[position.x][position.y] = symbol;
    }

    default void clearPosition(char[][] matrix, Coordinates position) {
        matrix[position.x][position.y] = ' ';
    }

    default Coordinates checkRoad(char command, Coordinates position) {

        int tempX = position.x;
        int tempY = position.y;

        switch (command) {
            case 'w' -> tempX -= 1;
            case 's' -> tempX += 1;
            case 'a' -> tempY -= 1;
            case 'd' -> tempY += 1;
        }

        return new Coordinates(tempX, tempY);
    }

    default void move(char command, char[][] matrix, Coordinates position, char symbol) {
        this.clearPosition(matrix, position);

        switch (command) {
            case 'w' -> position.x -= 1;
            case 's' -> position.x += 1;
            case 'a' -> position.y -= 1;
            case 'd' -> position.y += 1;
        }
        this.newPosition(matrix, position, symbol);
    }

    void setInitialPosition(int height, char[][] matrix);
}
