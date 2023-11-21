public class Wall extends Token {

    public Wall(int height, char[][] matrix, char initialSymbol) {
        super(height, matrix, initialSymbol);
    }

    @Override
    public void setInitialPosition(int height, char[][] matrix) {

    }



    public static void createFrame(int height, char[][] map) {

        if (height < 6) {
            throw new IllegalArgumentException("Height must be 6 or greater.");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1) {


                    map[i][j] = '+';
                }
                else
                    map[i][j] = ' ';
            }
        }
    }
}
