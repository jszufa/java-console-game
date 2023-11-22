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
            default -> {
                throw new IllegalArgumentException("Invalid command passed to checkRoad method");
            }
        }

        if (tempX <0 || tempY < 0) return position;

        return new Coordinates(tempX, tempY);
    }


    void setInitialPosition(int height, char[][] matrix);
}
