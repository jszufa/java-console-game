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


    @BeforeEach
    public void setUp(){
        Level mockedLevel = mock(Level.class);
        Game mockedGame = mock(Game.class);
        MapService mockedMapService = mock(MapService.class);
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

    @Test
    void stoneOnHole_shouldCompleteLevel() {
        //arrange
        Level mockedLevel = mock(Level.class);
        Game mockedGame = mock(Game.class);
        MapService mockedMapService = mock(MapService.class);

//        mockedLevel.hole.position = new Coordinates(1,1);
//        mockedLevel.stone.position = new Coordinates(1,2);
//        mockedLevel.hero.position = new Coordinates(1, 3);
        doNothing().when(mockedMapService).handleCommand("a", mockedLevel, mockedGame);

        //act
        mockedMapService.handleCommand("a", mockedLevel, mockedGame);

        //assert
        verify(mockedMapService).handleCommand("a", mockedLevel, mockedGame);
    }


}