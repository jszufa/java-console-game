import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame implements ISaveGame{
    private int levelCount;
    private int actualLevelNum;
    private int mapHeight;
    private SimpleLevel level;

    public SaveGame(){}
    public SaveGame(Game game, Level actualLevel, int actualLevelNum) {

        this.levelCount = game.levelCount;
        this.actualLevelNum = actualLevelNum;
        this.mapHeight = game.mapHeight;
        this.level = new SimpleLevel(actualLevel);
    }

    class SimpleLevel {
        private String label;
        private SimpleEntity hero;
        private SimpleEntity stone;
        private SimpleEntity hole;
        private SimpleEntity trap;
        private SimpleWalls walls;


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

    public void save(Game game, Level level, int actualLevelNum) {

        String filePath = "save.json";

        SaveGame saveGame = new SaveGame(game, level, actualLevelNum);
        Gson gson = new Gson();

        //convert saveGame object to JSON
        String myJson = gson.toJson(saveGame);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(myJson);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}