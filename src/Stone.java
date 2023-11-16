public class Stone extends Hero {

    public Stone(int height, char[][] matrix, char initialSymbol) {
        super(height, matrix, initialSymbol);
        //probably I should create a parent class for all objects without "settingInitialPosition"
        // now I set it to times one from parent constructor and one here...
        setInitialPosition(height, matrix);
    }

    //stone needs a distance from wall
    private void setInitialPosition(int height, char[][] matrix) {
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
        if (matrix[position.x][position.y] != ' ' && matrix[position.x][position.y] != '\0') {
            setInitialPosition(height, matrix);
        }
    }
}
