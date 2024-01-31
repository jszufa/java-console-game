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
        for (int i = 1; i <= levelCount; i++) {
            actualLevelNum = i;
            Level actualLevel = levelFactory.createLevel("Level" + actualLevelNum, mapHeight);

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
                    saveGame.save(this, actualLevel);
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

    public void loadGame() {
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

        //Odczytaj wartości konkretnych pól obiektu SaveGame -- muszą mieć ustawiony dostęp na public, inaczej nie mam dostępu, chyba że metoda loadGame będzie działać na SaveGame
        System.out.println(saveGame.level.label);

        this.levelCount = saveGame.levelCount;
        this.actualLevelNum = saveGame.actualLevelNum;
        this.mapHeight = saveGame.mapHeight;

        //Stwórz nową grę lub zaktualizuj wartości obecnej gry
        //np. this.levelCount = x
        // i = y (wydaje się, że fajnie byłoby dodać iterator jako pole obiektu - będzie też wtedy łatwiej odczytać je w save game i nie trzeba będzie go przekazywać)
        //actualLevel = new Level (i tutaj wstrzykuję wartości z save'a), tylko odpowiedni konstruktor trzeba by zrobić.
        //To chyba będzie najprostsze

        //mogę też stworzyć nową pętlę gry i zobaczyć co się w praktyce zmieni...
        String input;

        //game loop
        outerLoop:
        for (int i = actualLevelNum; i <= levelCount; i++) {

            Level actualLevel = levelFactory.createLevel("Level" + actualLevelNum, mapHeight);
            //tutaj powinno być coś w rodzaju loadLevel // albo level.load

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
                    saveGame.save(this, actualLevel);
                    continue;
                }
                mapService.handleCommand(input, actualLevel,this);
            }
        }


        //na koniec można otestować nowy kod, który napisałem, ale wcześniej lepiej napisać do Miśka
    }
}
