public class Stone extends Entity {

    public Stone(char[][] map, char initialSymbol) {
        super(map, initialSymbol);
    }

    //stone needs a distance from wall frame
    //maybe change to: "adjust stone position if it's to close to the wall" - or make the logic different
    @Override
    public void setInitialPosition(char[][] map) {
        int height = map.length;

        int maxAttempts = (height - 2) * (height - 2);
        int rowIndex;
        int colIndex;

        //generate random position and check if it's free
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            do {
                rowIndex = generator.nextInt(height - 2);
            } while (rowIndex <= 1);

            do {
                colIndex = generator.nextInt(height - 2);
            } while (colIndex <= 1);

            position.x = rowIndex;
            position.y = colIndex;

            //check if the field is free
            if (map[position.x][position.y] == ' ' || map[position.x][position.y] == '\0') {
                break;
            }
        }

        //throw exception if the place is not free after n (maxAttempts) attempts
        if (map[position.x][position.y] != ' ' && map[position.x][position.y] != '\0') {
            throw new IllegalStateException("Unable to find a free position after" + maxAttempts + " attempts");
        }

        //place stone's symbol on the free position and remember it
        map[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);
    }
}

