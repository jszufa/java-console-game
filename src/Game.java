import java.io.*;

import com.google.gson.Gson;

public class Game {
    ConsoleHandler console;
    IMapService mapService;
    LevelFactory levelFactory;
    ISaveGame saveGame;
    boolean victory = false;
    boolean gameOver = false;
    int levelCount;
    int actualLevelNum;
    int mapHeight;

    public Game(int levelCount, int mapHeight, ConsoleHandler console, IMapService mapService, LevelFactory levelFactory, ISaveGame saveGame) {
        this.console = console;
        this.mapService = mapService;
        this.levelFactory = levelFactory;
        this.saveGame = saveGame;
        this.levelCount = levelCount;
        this.mapHeight = mapHeight;
    }

    public void start() {
        String input;
        
        //game loop
        outerLoop:
        for (actualLevelNum = 1; actualLevelNum <= levelCount; actualLevelNum++) {
            Level actualLevel = levelFactory.createLevel("Level" + actualLevelNum, mapHeight);

            while (true) {
                clearConsole();
                printLevelLabel(actualLevel);
                printMap(actualLevel);

                if (actualLevelNum == levelCount && actualLevel.completed) {
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
                    saveGame.save(this, actualLevel);
                    continue;
                }
                if (input.equalsIgnoreCase("load")) {
                    actualLevel = this.loadGame();
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

    //to prawdopodobnie powinno być w klasie saveGame albo innej... w każdym razie powinno być oddzielone od klasy Game
    public Level loadGame() {
        //reading data from the file
        String filePath = "save.json";
        String saveJson;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            saveJson = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //converting data into SaveGame object
        Gson gson = new Gson();
        SaveGame saveGame = gson.fromJson(saveJson, SaveGame.class);

        //load saved game fields
        this.levelCount = saveGame.levelCount;
        this.actualLevelNum = saveGame.actualLevelNum;
        this.mapHeight = saveGame.mapHeight;

        //return saved level
        return levelFactory.loadLevel(saveGame.level, mapHeight);
    }
}
