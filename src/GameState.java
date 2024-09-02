public class GameState {
    public int levelCount;
    public int actualLevelNum;
    public int mapHeight;
    public SimpleLevel level;

    public GameState(Game game, Level actualLevel) {

        this.levelCount = game.levelCount;
        this.actualLevelNum = game.actualLevelNum;
        this.mapHeight = game.mapHeight;
        this.level = new SimpleLevel(actualLevel);
    }

    class SimpleLevel {
        public String label;
        public SimpleEntity hero;
        public SimpleEntity stone;
        public SimpleEntity hole;
        public SimpleEntity trap;
        public SimpleWalls walls;


        public SimpleLevel(Level level) {
            this.label = level.label;
            this.hero = new SimpleEntity(level.hero);
            this.stone = new SimpleEntity(level.stone);
            this.hole = new SimpleEntity(level.hole);
            this.trap = new SimpleEntity(level.trap);
            this.walls = new SimpleWalls(level.walls);
        }
    }

    class SimpleEntity {
        public Coordinates position;
        public Coordinates initialPosition;
        public char symbol;

        public SimpleEntity(Entity entity) {
            this.position = entity.position;
            this.initialPosition = entity.initialPosition;
            this.symbol = entity.symbol;
        }
    }

    class SimpleWalls {
        public Coordinates[] positions;
        public Coordinates[] initialPositions;
        public char symbol;
        public int randomWallsNumber;

        public SimpleWalls(Walls walls) {
            this.positions = walls.positions;
            this.initialPositions = walls.initialPositions;
            this.symbol = walls.symbol;
            this.randomWallsNumber = walls.randomWallsNumber;
        }
    }
}