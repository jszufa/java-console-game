import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void createFrameTest() {
        int height = 5;
        char[][] map = new char[height][height];

        //za dużo zależności...
        Hero hero = new Hero(height, map, 'H');
        Stone stone = new Stone(height, map, 'O');
        Hole hole = new Hole(height, map, 'X');
        Trap trap = new Trap(height, map, '8');
        var game = new Game(hero, stone, hole, trap, height, map);

        assertThrows(IllegalArgumentException.class , () -> game.createFrame());
    }
}