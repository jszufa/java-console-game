import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.Position;

import static java.lang.Character.toLowerCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

class MapServiceTest {

    Level mockedLevel;
    Game mockedGame;
    MapService mockedMapService;
    Hero mockedHero;
    Stone mockedStone;
    Hole mockedHole;
    Trap mockedTrap;
    Walls mockedWalls;
    char [][] map;



    @BeforeEach
    public void setUp(){
        mockedLevel = mock(Level.class);
        mockedGame = mock(Game.class);
        mockedMapService = mock(MapService.class);
        mockedHero = mock(Hero.class);
        mockedStone = mock(Stone.class);
        mockedHole = mock(Hole.class);
        mockedTrap = mock(Trap.class);
        mockedWalls = mock(Walls.class);
    }

    @Test
    void q_shouldReturnFalse_onValidateCommand (){
        //arrange
        MapService mapService = new MapService();
        char command = 'q';

        //act
        boolean actualResult = mapService.validateCommand(command);

        //assert
        assertFalse(actualResult);
    }

    @Test
    void capitalW_shouldReturnTrue_onValidateCommand (){
        //arrange
        MapService mapService = new MapService();
        char command = 'W';

        //act
        boolean actualResult = mapService.validateCommand(command);

        //assert
        assertTrue(actualResult);
    }

    @Test
    void when_HandleCommandCalled_thenVerified() {
        //arrange
        doNothing().when(mockedMapService).handleCommand("a", mockedLevel, mockedGame);

        //act
        mockedMapService.handleCommand("a", mockedLevel, mockedGame);

        //assert
        verify(mockedMapService).handleCommand("a", mockedLevel, mockedGame);
    }

    //checking different tree paths


    @Test
    void stoneOnHole_shouldCompleteLevel() {
        //arrange
        MapService mapService = new MapService();
        map = new char[6][6];
        mockedLevel.map = map;

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);

        map[futureMove.x][futureMove.y] = mockedStone.symbol; //stone is on the hero's way
        map[futureStoneMove.x][futureStoneMove.y] = mockedHole.symbol; //hole is on the stone's way

        //mocking other dependencies
        mockedLevel.hero = mockedHero;
        mockedLevel.stone = mockedStone;
        mockedLevel.hole = mockedHole;
        mockedLevel.trap = mockedTrap;
        mockedLevel.walls = mockedWalls;

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);
        when(mockedStone.checkRoad(command, mockedStone.position)).thenReturn(futureStoneMove);

        //mocking move
        doNothing().when(mockedStone).move(command, map);
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        assertTrue(mockedLevel.completed);
    }

}