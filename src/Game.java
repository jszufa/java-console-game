import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class Game {
    ConsoleHandler console;
    IMapService mapService;
    LevelFactory levelFactory;
    boolean victory = false;
    boolean gameOver = false;
    int levelCount;
    int mapHeight;

    public Game(int levelCount, int mapHeight, ConsoleHandler console, IMapService mapService, LevelFactory levelFactory) {
        this.console = console;
        this.mapService = mapService;
        this.levelFactory = levelFactory;
        this.levelCount = levelCount;
        this.mapHeight = mapHeight;
    }

    public void start() {
        String input;
        
        //game loop
        outerLoop:
        for (int i = 1; i <= levelCount; i++) {
            Level actualLevel = levelFactory.createLevel("Level" + i, mapHeight);

            while (true) {
                clearConsole();
                printLevelLabel(actualLevel);
                printMap(actualLevel);

                if (i == levelCount && actualLevel.completed) {
                    victory = true; //just for clarity
                    printVictory();
                    break outerLoop;
                } else if (actualLevel.completed) {
                    continue outerLoop;
                } else if (gameOver) {
                    printGameOver();
                    break outerLoop;
                }

                printInputMessage();
                input = console.readInput();
                if (input.equalsIgnoreCase("reset")) {
                    actualLevel.resetLevel();
                    continue;
                }
                if (input.equalsIgnoreCase("quit")) {
                    break outerLoop;
                }
                if (input.equalsIgnoreCase("save")) {
                    this.save(actualLevel);
                    continue;
                }
                mapService.handleCommand(input, actualLevel,this);
            }
        }
    }
    

    public void printMap(Level level) {
        for (char[] row : level.map) {
            for (char element : row) {
                console.displayOutput(element + "  ");
            }
            console.displayOutputEmptyLn();
        }
    }

    public void printInputMessage() {
        console.displayOutput("Enter command: ");
    }


    public void printVictory() {
        console.displayOutput("------VICTORY!--------");
    }

    public void printGameOver() {
        console.displayOutputLn("------YOU-LOST--------");
        console.displayOutputLn("------BUT-STILL--------");
        console.displayOutputLn("----ENJOY-GOBLIN-SONG-----");
        console.displayOutputEmptyLn();
        console.displayOutputLn("\"Even though you had a map...\n You stupidly fell into our trap. \n Don't cry don't cry \n You'll be our pie\"");
    }

    public void printLevelLabel(Level level) {
        console.displayOutputLn(level.label);
    }

    public void clearConsole() {
        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            console.displayOutputEmptyLn();
        }
    }

    public void save(Level level) {

        String data = "asdfsdf";
        String filePath = "save.json";
        //potrzebuję zapisać:
        //stan levelu, czyli współrzędne wszystkich obiektów
        //stan początkowy levelu, żeby można go było zresetować
        //numer levelu oraz ile jest wszystkich leveli
        //czyli np. levelCount: levelCount

        String strLevelCount = String.valueOf(levelCount);
        data = "levelCount: " + strLevelCount;

        String strLevelHero = String.valueOf(level.hero.position);
        data = "levelHeroPosition: " + strLevelHero;

        //potrzebuję stworzyć sobie strukturę bazy danych, czyli jak to będzie zapisywane - czego użyć..


        Save save = new Save(this, level);
        Gson gson = new Gson();

        //convert to JSON
        String myJson = gson.toJson(save);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(myJson);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
