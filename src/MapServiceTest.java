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

    //invocation verification - is it necessary?
    @Test
    void when_HandleCommandCalled_thenVerified() {
        //arrange
        doNothing().when(mockedMapService).handleCommand("a", mockedLevel, mockedGame);

        //act
        mockedMapService.handleCommand("a", mockedLevel, mockedGame);

        //assert
        verify(mockedMapService).handleCommand("a", mockedLevel, mockedGame);
    }

    // handleCommand TESTS
    void customSetup_for_handleCommand_tests () {
        mockedLevel.hero = mockedHero;
        mockedLevel.stone = mockedStone;
        mockedLevel.hole = mockedHole;
        mockedLevel.trap = mockedTrap;
        mockedLevel.walls = mockedWalls;

        map = new char[6][6];
        mockedLevel.map = map;
    }

    //checking different tree paths
    @Test
    void stoneOnHole_shouldCompleteLevel() {
        //arrange
        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);

        mockedStone.symbol = 'O';
        map[futureMove.x][futureMove.y] = 'O'; //stone is on the hero's way

        mockedHole.symbol = 'X';
        map[futureStoneMove.x][futureStoneMove.y] = 'X'; //hole is on the stone's way

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

    @Test
    void stoneOnTrap_should_loseGame() {
        //arrange
        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);

        mockedStone.symbol = 'O';
        map[futureMove.x][futureMove.y] = 'O'; //stone is on the hero's way

        mockedTrap.symbol = '8';
        map[futureStoneMove.x][futureStoneMove.y] = '8'; //trap is on the stone's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);
        when(mockedStone.checkRoad(command, mockedStone.position)).thenReturn(futureStoneMove);

        //mocking move
        doNothing().when(mockedStone).move(command, map);
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        assertTrue(mockedGame.gameOver);
    }

    @Test
    void stoneOnWall_shouldNot_moveItems() {
        //arrange
        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);

        mockedStone.symbol = 'O';
        map[futureMove.x][futureMove.y] = 'O'; //stone is on the hero's way

        mockedWalls.symbol = '+';
        map[futureStoneMove.x][futureStoneMove.y] = '+'; //wall is on the stone's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);
        when(mockedStone.checkRoad(command, mockedStone.position)).thenReturn(futureStoneMove);

        //mocking move
        doNothing().when(mockedStone).move(command, map);
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        verify(mockedHero, never()).move(command, map);
        verify(mockedStone, never()).move(command, map);
    }

    @Test
    void stoneOnEmpty_should_moveItems () {
        //arrange
        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);
        Coordinates futureStoneMove = new Coordinates(1, 1);

        mockedStone.symbol = 'O';
        map[futureMove.x][futureMove.y] = 'O'; //stone is on the hero's way
        map[futureStoneMove.x][futureStoneMove.y] = ' '; //nothing is on the stone's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);
        when(mockedStone.checkRoad(command, mockedStone.position)).thenReturn(futureStoneMove);

        //mocking move
        doNothing().when(mockedStone).move(command, map);
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        verify(mockedHero, times(1)).move(command, map);
        verify(mockedStone, times(1)).move(command, map);
    }

    //potencjalny test...
    @Test
    void stoneOutOfMap_shouldNot_moveItems() {

    }

    @Test
    void heroOnTrap_should_endGame() {

        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);

        mockedTrap.symbol = '8';
        map[futureMove.x][futureMove.y] = '8'; //trap is on the hero's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);

        //mocking move
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        assertTrue(mockedGame.gameOver);
    }

    @Test
    void heroOnHole_should_endGame() {

        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);

        mockedHole.symbol = 'X';
        map[futureMove.x][futureMove.y] = 'X'; //hole is on the hero's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);

        //mocking move
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        assertTrue(mockedGame.gameOver);
    }

    @Test
    void heroOnWall_shouldNot_move() {

        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);

        mockedWalls.symbol = '+';
        map[futureMove.x][futureMove.y] = '+'; //hole is on the hero's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);

        //mocking move
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        verify(mockedHero, never()).move(command, map);
    }

    @Test
    void heroOnEmpty_should_move() {
        customSetup_for_handleCommand_tests(); //mocking some dependencies

        MapService mapService = new MapService();

        String input = "a";
        char command = 'a';
        Coordinates futureMove = new Coordinates(1, 2);

        map[futureMove.x][futureMove.y] = ' '; //nothing is on the hero's way

        //mocking checkRoad
        when(mockedHero.checkRoad(command, mockedHero.position)).thenReturn(futureMove);

        //mocking move
        doNothing().when(mockedHero).move(command, map);

        //act
        mapService.handleCommand(input, mockedLevel, mockedGame);

        //assert
        verify(mockedHero, times(1)).move(command, map);
    }

    //case when something unexpected is on the way - throw exception.
}

// przetestować inne scenariusze - może mogę gdzieś umieścić wiele elementów z sekcji arrange, tak żeby ich nie dublować w następnych testach.
//potencjalne testy:
//4.co z pchnięciem poza planszę?
//5.co jeszcze może się przytrafić?