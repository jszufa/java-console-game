import java.util.Random;

public class Token implements Item {
    Random generator = new Random();
    public Coordinates position = new Coordinates(0, 0);
    Coordinates initialPosition;
    char symbol;

    public Token (int height, char[][] matrix, char initialSymbol) {
        symbol = initialSymbol;
        setInitialPosition(height, matrix);
    }

    @Override
    public void setInitialPosition(int height, char[][] matrix) {
        int rowIndex;
        do {
            rowIndex = generator.nextInt(height - 1);
        } while (rowIndex == 0);

        int colIndex;
        do {
            colIndex = generator.nextInt(height - 1);
        } while (colIndex == 0);

        position.x = rowIndex;
        position.y = colIndex;

        //check if the field is free
        if (matrix[position.x][position.y] != ' ' && matrix[position.x][position.y] != '\0') {
            setInitialPosition(height, matrix);
        }
        //it gets cleared when drawing the frame, but still it's a placeholder for initializing
        matrix[position.x][position.y] = symbol;
        this.initialPosition = new Coordinates(position.x, position.y);
    }
}

