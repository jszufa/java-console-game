public class DefaultLevelFactory implements LevelFactory {
    @Override
    public Level createLevel(String label, int mapHeight) {
        return new Level(label, mapHeight);
    }
}
