public class Main {
    public static void main(String[] args) {
        //do wywalenia
        LoadGame.loadGame();

        LevelFactory defaultLevelFactory = new DefaultLevelFactory();
        ConsoleHandler console = new ConsoleHandlerImpl();
        IMapService mapService = new MapService(console);
        ISaveGame saveGame = new SaveGame();
        Game game = new Game(3, 8, console, mapService, defaultLevelFactory, saveGame);
        game.start();

    }
}
