public class Level {

    String label;
    int height = 6;
    char[][] map = new char[height][height];
    Game game;

    //this could be also done by "createGame" method
    Hero hero = new Hero(height, map, 'H');
    Stone stone = new Stone(height, map, 'O');
    Hole hole = new Hole(height, map, 'X');
    Trap trap = new Trap(height, map, '8');

    //metoda set initial postion mogłaby też zwracać initial postion i zapisywać ją w levelu, albo każdy obiekt mógłby mieć swoje initial position w dane grze. (no bo u mnie itemy mają swoje współrzędne)

    public Level(String label) {
        this.label = label;
        this.game = new Game(hero, stone, hole, trap, height, map);

        //CODE HERE
        //SAVE INITIAL SETTING
        //to powinien być zestaw współrzędnych, tak żeby kiedy przekaże się ten zestaw w konstruktorze levelu, to powstanie gotowy level
        //może przeładować konstruktor tutaj albo w game??
    }

    public void resetLevel() {

        Token[] tokens = {hero, stone, hole, trap};

        for (Token token : tokens) {
            token.clearPosition(map, token.position);
            //assigns values (not the reference)
            token.position.x = token.initialPosition.x;
            token.position.y = token.initialPosition.y;
            token.newPosition(map, token.position, token.symbol);

        }
    }
}
