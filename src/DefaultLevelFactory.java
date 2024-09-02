public class DefaultLevelFactory implements LevelFactory {
    @Override
    public Level createLevel(String label, int mapHeight) {
        return new Level(label, mapHeight);
    }

    @Override
    public Level loadLevel(GameState.SimpleLevel loadedLevel, int mapHeight){
        return new Level(loadedLevel, mapHeight);
    }

}
