public class Main {
    public static void main(String[] args) {

        LevelFactory defaultLevelFactory = new DefaultLevelFactory();
        ConsoleHandler console = new ConsoleHandlerImpl();
        IMapService mapService = new MapService(console);
        IGameService gameService = new GameService();
        Game game = new Game(3, 8, console, mapService, defaultLevelFactory, gameService);
        game.start();

    }
}
