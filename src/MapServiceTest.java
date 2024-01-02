import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.Position;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MapServiceTest {
    Level mockedLevel;
    Game mockedGame;
    MapService mockedMapService;
    Hero mockedHero;
    Stone mockedStone;
    Hole mockedHole;
    char [][] map;


    @BeforeEach
    public void setUp(){
        mockedLevel = mock(Level.class);
        mockedGame = mock(Game.class);
        mockedMapService = mock(MapService.class);
        mockedHero = mock(Hero.class);
        mockedStone = mock(Stone.class);
        mockedHole = mock(Hole.class);
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
        //Future move
        map = new char[6][6];
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);
        char command = 'a';

        //setting hero's future move
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);

        //stone is on the hero's way
        when(map[futureMove.x][futureMove.y] == mockedStone.symbol).thenReturn(true);

        //setting stone's future move
        when(mockedStone.checkRoad(command, mockedStone.position)).thenReturn(futureStoneMove);

        //hole is on the stone's way
        when(map[futureStoneMove.x][futureStoneMove.y] == mockedHole.symbol).thenReturn(true);


        //doNothing().when(mockedMapService).handleCommand("a", mockedLevel, mockedGame);
//        mockedLevel.hole.position = new Coordinates(1,1);
//        mockedLevel.stone.position = new Coordinates(1,2);
//        mockedLevel.hero.position = new Coordinates(1, 3);
        //doNothing().when(mockedMapService).handleCommand("a", mockedLevel, mockedGame);

        //act
        mockedMapService.handleCommand("a", mockedLevel, mockedGame);

        //assert
        assertTrue(mockedLevel.completed);
        //verify(mockedMapService).handleCommand("a", mockedLevel, mockedGame);
    }


}