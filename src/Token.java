import java.util.Random;

public class Token implements Item {
    Random generator = new Random();
    public Coordinates position = new Coordinates(0, 0);
    Coordinates initialPosition;
    char symbol;

    public Token(int height, char[][] matrix, char initialSymbol) {
        symbol = initialSymbol;
        setInitialPosition(height, matrix);
    }

    @Override
    public void setInitialPosition(int height, char[][] map) {

        if (height < 6) {
            throw new IllegalArgumentException("Height must be 6 or greater");
        }

        int maxAttempts = (height-2)*(height-2);
        int rowIndex;
        int colIndex;

        //generate random position and check if it's free
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            do {
                rowIndex = generator.nextInt(height - 1);
            } while (rowIndex == 0);

            do {
                colIndex = generator.nextInt(height - 1);
            } while (colIndex == 0);

            position.x = rowIndex;
            position.y = colIndex;

            //check if the place is free
            if (map[position.x][position.y] == ' ' || map[position.x][position.y] == '\0') {
                break;
            }
        }

        //throw exception if the place is not free after n(maxAttempts) attempts
        if (map[position.x][position.y] != ' ' && map[position.x][position.y] != '\0') {
            throw new IllegalStateException("Unable to find a free position after" + maxAttempts + " attempts");
        }

        //place token's symbol on the free position and remember it
        map[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);

    }


    public void move(char command, char[][] matrix) {
        this.clearPosition(matrix, position);

        switch (command) {
            case 'w' -> position.x -= 1;
            case 's' -> position.x += 1;
            case 'a' -> position.y -= 1;
            case 'd' -> position.y += 1;
            default -> throw new IllegalArgumentException("Invalid command passed to checkRoad method");
        }
        this.newPosition(matrix, this.position, this.symbol);
    }
}

