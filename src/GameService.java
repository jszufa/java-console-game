import com.google.gson.Gson;

import java.io.*;

public class GameService implements IGameService{

    public void save(GameState gameState) {

        String filePath = "save.json";
        Gson gson = new Gson();

        //convert gameState object to JSON
        String saveJson = gson.toJson(gameState);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(saveJson);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameState load() {
        String filePath = "save.json";
        Gson gson = new Gson();

        //try-with-resources statement - ensures that resources (reader) are properly closed, even in the presence of exceptions.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            GameState gameState = gson.fromJson(reader, GameState.class);
            return gameState;

        } catch (IOException e) {
            throw new RuntimeException("Error loading game state: " + e.getMessage(), e);
        }
    }
}

//może to też powinno być osobno jako GameSaver - dla klarowności, a nie pod SaveGame... albo GameService
//jak wstrzykiwać saveGame??

//czyli w skrócie teraz jest pytanie, czy żeby dobrze działało load game, to powinno być coś ala Game service, który jest wyżej od Game w hierarchii - wydaje się że to ma sens
//web-scrapping

//mam obiekt saveGame - może powinno być GameState (jednostka danych) - który służy do przechowywania stanu gry
//mam interfejs Game service, który określa metody służące do sejwowania i ??(wczytywania stanu gry) - czyli parametrem Game service, powinno być w zasadzie SaveGame), a nie game i level
//
