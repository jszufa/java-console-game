import java.util.Random;

public class Entity implements Item {
    Random generator = new Random();
    public Coordinates position = new Coordinates(0, 0);
    Coordinates initialPosition;
    char symbol;

    public Entity(char[][] map, char initialSymbol) {
        symbol = initialSymbol;
        setInitialPosition(map);
    }

    @Override
    public void setInitialPosition(char[][] map) {
        int height = map.length;

        if (height < 6) {
            throw new IllegalArgumentException("Height must be 6 or greater");
        }

        int maxAttempts = (height - 2) * (height - 2);
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

        //throw exception if the place is not free after n (maxAttempts) attempts
        if (map[position.x][position.y] != ' ' && map[position.x][position.y] != '\0') {
            throw new IllegalStateException("Unable to find a free position after" + maxAttempts + " attempts");
        }

        //place token's symbol on the free position and remember it
        map[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);

    }


    public void move(char command, char[][] map) {
        this.clearPosition(map, position);

        int numRows = map.length;
        int numCols = map[0].length;

        switch (command) {
            case 'w':
                if (position.x > 0)
                    position.x -= 1;
                break;
            case 's':
                if (position.x < numRows - 1)
                    position.x += 1;
                break;
            case 'a':
                if (position.y > 0)
                    position.y -= 1;
                break;
            case 'd':
                if (position.y < numCols - 1)
                    position.y += 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid command passed to checkRoad method");
        }


        this.newPosition(map, this.position, this.symbol);
    }
}

