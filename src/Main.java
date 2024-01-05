public class Main {
    public static void main(String[] args) {

        LevelFactory defaultLevelFactory = new DefaultLevelFactory();
        Game game = new Game(3, 8, defaultLevelFactory);
        game.start();

    }
}
