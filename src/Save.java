public class Save {
    private int levelCount;
    private SimpleLevel level;

    public Save(Game game, Level actualLevel) {

        this.levelCount = game.levelCount;
        //problem comes here, because actualLevel is complex, and I need just a simple version of actualLevel
        this.level = new SimpleLevel(actualLevel);
    }

    class SimpleLevel {
        private SimpleEntity hero;
        private SimpleEntity stone;
        private SimpleEntity hole;
        private SimpleEntity trap;
        private SimpleWalls walls;

        public SimpleLevel(Level level) {
            this.hero = new SimpleEntity(level.hero);
            this.stone = new SimpleEntity(level.stone);
            this.hole = new SimpleEntity(level.hole);
            this.trap = new SimpleEntity(level.trap);
            this.walls = new SimpleWalls(level.walls);
        }
    }

    class SimpleEntity {
        private Coordinates position;
        private Coordinates initialPosition;
        private char symbol;

        public SimpleEntity(Entity entity) {
            this.position = entity.position;
            this.initialPosition = entity.initialPosition;
            this.symbol = entity.symbol;
        }
    }

    class SimpleWalls {
        private Coordinates[] positions;
        private Coordinates[] initialPositions;
        private char symbol;
        private int randomWallsNumber;

        public SimpleWalls(Walls walls) {
            this.positions = walls.positions;
            this.initialPositions = walls.initialPositions;
            this.symbol = walls.symbol;
            this.randomWallsNumber = walls.randomWallsNumber;
        }
    }
}
//this.currentLevelNum =