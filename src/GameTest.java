import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    int mapHeight;
    Level mockedLevel;
    LevelFactory mockedLevelFactory;
    ConsoleHandler mockedConsole;
    IMapService mockedMapService;
    IGameService mockedSaveGame;
    String input;

    @BeforeEach
    public void setUp() {
        mapHeight = 7;
        mockedLevel = mock(Level.class);
        mockedLevelFactory = mock(LevelFactory.class);
        mockedConsole = mock(ConsoleHandler.class);
        mockedMapService = mock(MapService.class);
        mockedSaveGame = mock(IGameService.class);
    }

    @Test
    void completedLevel_should_winGame() {
        //arrange
        String label = "Level";
        int levelCount = 3;

        //mock - tworzy imitację klasy - bierze pod uwagę tylko szkielet
        //spy - bierze rzeczywistą instancję, śledzi jej zachowanie i może zmieniać działanie programu
        Game game = new Game(levelCount, mapHeight, mockedConsole, mockedMapService, mockedLevelFactory, mockedSaveGame);
        Game spyGame = spy(game);

        for (int i = 1; i <= levelCount; i++) {
            when(mockedLevelFactory.createLevel(label + i, mapHeight)).thenReturn(mockedLevel);
        }

        doNothing().when(spyGame).clearConsole();
        doNothing().when(spyGame).printLevelLabel(mockedLevel);
        doNothing().when(spyGame).printMap(mockedLevel);
        doNothing().when(spyGame).printVictory();
        mockedLevel.completed = true;

        //act
        spyGame.start();

        //assert
        assertTrue(spyGame.victory);
    }

    @Test
    void lostGame_should_printGameOver() {
        //arrange
        String label = "Level1";
        int levelCount = 1;

        Game game = new Game(levelCount, mapHeight, mockedConsole, mockedMapService, mockedLevelFactory, mockedSaveGame);
        Game spyGame = spy(game);

        when(mockedLevelFactory.createLevel(label, mapHeight)).thenReturn(mockedLevel);
        doNothing().when(spyGame).clearConsole();
        doNothing().when(spyGame).printLevelLabel(mockedLevel);
        doNothing().when(spyGame).printMap(mockedLevel);
        doNothing().when(spyGame).printVictory();
        doNothing().when(spyGame).printGameOver();
        spyGame.gameOver = true;

        //act
        spyGame.start();

        //assert
        verify(spyGame).printGameOver();
    }

    @Test
    void quit_shouldNot_invokeMapService () {
        //arrange
        String label = "Level1";
        int levelCount = 1;
        String input = "quit";

        Game game = new Game(levelCount, mapHeight, mockedConsole, mockedMapService, mockedLevelFactory, mockedSaveGame);
        Game spyGame = spy(game);

        when(mockedLevelFactory.createLevel(label, mapHeight)).thenReturn(mockedLevel);
        doNothing().when(spyGame).clearConsole();
        doNothing().when(spyGame).printLevelLabel(mockedLevel);
        doNothing().when(spyGame).printMap(mockedLevel);
        doNothing().when(spyGame).printInputMessage();
        when(mockedConsole.readInput()).thenReturn(input);

        //act
        spyGame.start();

        //assert
        verify(mockedMapService, never()).handleCommand(input, mockedLevel, spyGame);
    }

    @Test
    void reset_should_resetLevel () {
        //arrange
        String label = "Level1";
        int levelCount = 1;
        this.input = "reset";

        Game game = new Game(levelCount, mapHeight, mockedConsole, mockedMapService, mockedLevelFactory, mockedSaveGame);
        Game spyGame = spy(game);

        when(mockedLevelFactory.createLevel(label, mapHeight)).thenReturn(mockedLevel);
        doNothing().when(spyGame).clearConsole();
        doNothing().when(spyGame).printLevelLabel(mockedLevel);
        doNothing().when(spyGame).printMap(mockedLevel);
        doNothing().when(spyGame).printInputMessage();
        //thanAnswer - dynamicznie generuje odpowiedź zmockowanej klasy
        //thanReturn - generuje statycznie. Czyli tylko raz odczyta wartość, którą ma zwracać.
        when(mockedConsole.readInput()).thenAnswer(invocationOnMock -> input);

        doAnswer(invocationOnMock ->
        {input = "quit"; return null;
        }).when(mockedLevel).resetLevel();

        //act
        spyGame.start();

        //assert
        verify(mockedLevel, times(1)).resetLevel();
    }
}
