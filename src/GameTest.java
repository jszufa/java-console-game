import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class GameTest {
    //czy tę klasę w ogóle warto testować?

    @Test
    void someTest() {
        //arrange
        Level mockedLevel = mock(Level.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        String label = "Level1";
        int mapHeight = 7;
        int levelCount = 3;

        //mock - tworzy imitację klasy - bierze pod uwagę tylko szkielet
        //spy - bierze rzeczywistą instancję, śledzi jej zachowanie i może zmieniać działanie programu
        Game game = new Game(levelCount, mapHeight, mockedLevelFactory);
        Game spyGame = spy(game);

        when(mockedLevelFactory.createLevel(label, mapHeight)).thenReturn(mockedLevel);
        doNothing().when(spyGame).clearConsole();
        doNothing().when(spyGame).printLevelLabel(mockedLevel);
        doNothing().when(spyGame).printMap(mockedLevel);


        //act
        spyGame.start();



    }
}
