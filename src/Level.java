public class Level {

    String label;
    int height = 7; //Enter number between 6 and 20
    char[][] map = createEmptyMap(height);
    Game game;

    //this could be also done by "createGame" method
    Walls walls = new Walls(height, map, 0, '+');
    Hero hero = new Hero(height, map, 'H');
    Stone stone = new Stone(height, map, 'O');
    Hole hole = new Hole(height, map, 'X');
    Trap trap = new Trap(height, map, '8');


    public Level(String label) {
        this.label = label;
        this.game = new Game(hero, stone, hole, trap, walls, height, map);
    }

    public void resetLevel() {

        Token[] tokens = {hero, stone, hole, trap};

        for (Token token : tokens) {
            token.clearPosition(map, token.position);
            //assigns values (not the reference)
            token.position.x = token.initialPosition.x;
            token.position.y = token.initialPosition.y;
            token.newPosition(map, token.position, token.symbol);

        }
    }

    public static char[][] createEmptyMap(int height) {

        if (height < 6 || height > 20) {
            throw new IllegalArgumentException("Height must be greater than 5 and smaller than 21");
        }

        char[][] map = new char[height][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = ' ';
            }
        }
        return map;
    }
}

