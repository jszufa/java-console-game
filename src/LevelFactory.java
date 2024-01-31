public interface LevelFactory {
    Level createLevel(String label, int mapHeight);
    Level loadLevel(SaveGame.SimpleLevel loadedLevel, int mapHeight);
}
