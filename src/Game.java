public class Game {

    int height = 10;
    char[][] charMatrix = new char[height][height];
    Hero hero = new Hero(height, charMatrix, 'H');
    Stone stone = new Stone(height, charMatrix, 'O');


    public Game() {
        createFrame();
        hero.newPosition(charMatrix);
        stone.newPosition(charMatrix);
    }

    public void createFrame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {

                if (i == 0 || i == height - 1 || j == 0 || j == height - 1)
                    charMatrix[i][j] = '+';

                else
                    charMatrix[i][j] = ' ';
            }
        }
    }


    public void printCharArray() {
        for (char[] row : charMatrix) {
            for (char element : row) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
    }

    public void handleCommand(String input) {
        char command = input.charAt(0);
        Coordinates futureMove= hero.checkRoad(command);

        //stone
        if(charMatrix[futureMove.x][futureMove.y] == 'O'){
            Coordinates futureStoneMove = stone.checkRoad(command);
            if (charMatrix[futureStoneMove.x][futureStoneMove.y] != '+'){

                stone.move(command, charMatrix);
                hero.move(command, charMatrix);
            }
        }
        //wall
        else if (charMatrix[futureMove.x][futureMove.y] != '+') {

            hero.move(command, charMatrix);
        }



    }
}
