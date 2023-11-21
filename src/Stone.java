public class Stone extends Token {

    public Stone(int height, char[][] matrix, char initialSymbol) {
        super(height, matrix, initialSymbol);
    }

    //stone needs a distance from wall
    //maybe change to: "adjust stone position if it's to close to the wall" - or make the logic different
    @Override
    public void setInitialPosition(int height, char[][] map) {
        int rowIndex;
        do {
            rowIndex = generator.nextInt(height - 2);
        } while (rowIndex <= 1);

        int colIndex;
        do {
            colIndex = generator.nextInt(height - 2);
        } while (colIndex <= 1);

        position.x = rowIndex;
        position.y = colIndex;

        //check if the field is free
        if (map[position.x][position.y] != ' ' && map[position.x][position.y] != '\0') {
            setInitialPosition(height, map);
        }

        map[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);
    }
}
