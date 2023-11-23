public class Level {

    String label;
    char[][] map;
    Walls walls;
    Hero hero;
    Stone stone;
    Hole hole;
    Trap trap;

    boolean completed = false;



    public Level(String label, int mapHeight) {
        this.label = label;
        this.map = createEmptyMap(mapHeight);
        this.walls = new Walls(map, 0, '+');
        this.hero = new Hero(map, 'H');
        this.stone = new Stone(map, 'O');
        this.hole = new Hole(map, 'X');
        this.trap = new Trap(map, '8');
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

