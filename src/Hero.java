import java.util.Random;

public class Hero {
    Random generator = new Random();
    public Coordinates position = new Coordinates(0, 0);

    public Hero(int height) {
        setPosition(height);
    }

    private void setPosition(int height) {
        int rowIndex;
        do  {
            rowIndex = generator.nextInt(height-1);
        } while (rowIndex == 0);

        int colIndex;
        do  {
            colIndex = generator.nextInt(height-1);
        } while (colIndex == 0);

        position.x = rowIndex;
        position.y = colIndex;
    }

    public void moveHero(char command) {
        switch(command) {
            case 'w':
                position.x -= 1;
                break;
            case 's':
                position.x += 1;
                break;
            case 'a':
                position.y -= 1;
                break;
            case 'd':
                position.y += 1;
                break;
        }
    }

    public Coordinates checkRoad(char command) {

        int tempX = position.x;
        int tempY = position.y;

        switch(command) {
            case 'w':
                tempX -= 1;
                break;
            case 's':
                tempX += 1;
                break;
            case 'a':
                tempY -= 1;
                break;
            case 'd':
                tempY += 1;
                break;
        }

        return new Coordinates(tempX, tempY);
    }

    //charMatrix[rowRandom][colRandom] = 'H';

}
