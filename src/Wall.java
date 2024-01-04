//zobaczymy czy się przyda do tworzenia pojedynczych losowych murków.

public class Wall extends Entity {

    public Wall(char[][] map, char initialSymbol) {
        super(map, initialSymbol);
    }
    //needs to be placed on the map at the end, because it checks space availability with respect to other items

    @Override
    public void setInitialPosition(char[][] map) {
        int height = map.length;

        int maxAttempts = (height - 1) * (height - 1);
        int rowIndex;
        int colIndex;

        //generate random position and check if it's free
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            do {
                rowIndex = generator.nextInt(height - 2);
            } while (rowIndex == 0);

            do {
                colIndex = generator.nextInt(height - 2);
            } while (colIndex == 0);

            position.x = rowIndex;
            position.y = colIndex;

            //check if the field is free and if the surrounding fields are free or are walls
            if (
                    (map[position.x][position.y] == ' ' || map[position.x][position.y] == '\0')
                            && (map[position.x + 1][position.y] == ' ' || map[position.x + 1][position.y] == '\0' || map[position.x + 1][position.y] == this.symbol)
                            && (map[position.x - 1][position.y] == ' ' || map[position.x - 1][position.y] == '\0' || map[position.x - 1][position.y] == this.symbol)
                            && (map[position.x][position.y + 1] == ' ' || map[position.x][position.y + 1] == '\0' || map[position.x][position.y + 1] == this.symbol)
                            && (map[position.x][position.y - 1] == ' ' || map[position.x][position.y - 1] == '\0' || map[position.x][position.y - 1] == this.symbol)
                            && (map[position.x + 1][position.y + 1] == ' ' || map[position.x + 1][position.y + 1] == '\0' || map[position.x + 1][position.y + 1] == this.symbol)
                            && (map[position.x + 1][position.y - 1] == ' ' || map[position.x - 1][position.y - 1] == '\0' || map[position.x - 1][position.y - 1] == this.symbol)
                            && (map[position.x - 1][position.y + 1] == ' ' || map[position.x - 1][position.y + 1] == '\0' || map[position.x - 1][position.y + 1] == this.symbol)
                            && (map[position.x - 1][position.y - 1] == ' ' || map[position.x - 1][position.y - 1] == '\0' || map[position.x - 1][position.y - 1] == this.symbol)
            ) {
                break;
            }
        }

        //throw exception if the place is not found after n (maxAttempts) attempts
        if (
                !((map[position.x][position.y] == ' ' || map[position.x][position.y] == '\0')
                        && (map[position.x + 1][position.y] == ' ' || map[position.x + 1][position.y] == '\0' || map[position.x + 1][position.y] == this.symbol)
                        && (map[position.x - 1][position.y] == ' ' || map[position.x - 1][position.y] == '\0' || map[position.x - 1][position.y] == this.symbol)
                        && (map[position.x][position.y + 1] == ' ' || map[position.x][position.y + 1] == '\0' || map[position.x][position.y + 1] == this.symbol)
                        && (map[position.x][position.y - 1] == ' ' || map[position.x][position.y - 1] == '\0' || map[position.x][position.y - 1] == this.symbol)
                        && (map[position.x + 1][position.y + 1] == ' ' || map[position.x + 1][position.y + 1] == '\0' || map[position.x + 1][position.y + 1] == this.symbol)
                        && (map[position.x + 1][position.y - 1] == ' ' || map[position.x - 1][position.y - 1] == '\0' || map[position.x - 1][position.y - 1] == this.symbol)
                        && (map[position.x - 1][position.y + 1] == ' ' || map[position.x - 1][position.y + 1] == '\0' || map[position.x - 1][position.y + 1] == this.symbol)
                        && (map[position.x - 1][position.y - 1] == ' ' || map[position.x - 1][position.y - 1] == '\0' || map[position.x - 1][position.y - 1] == this.symbol)
        )) {
            throw new IllegalStateException("Unable to find a free position for random wall after" + maxAttempts + " attempts");
        }

        //place wall's symbol on the free position and remember it
        map[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);
    }
}

