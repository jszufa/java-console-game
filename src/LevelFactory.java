public interface LevelFactory {
    Level createLevel(String label, int mapHeight);
    Level loadLevel(GameState.SimpleLevel loadedLevel, int mapHeight);
}
