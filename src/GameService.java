import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameService implements IGameService{

    //może to też powinno być osobno jako GameSaver - dla klarowności, a nie pod SaveGame... albo GameService
    //jak wstrzykiwać saveGame??

    //czyli w skrócie teraz jest pytanie, czy żeby dobrze działało load game, to powinno być coś ala Game service, który jest wyżej od Game w hierarchii - wydaje się że to ma sens
    //web-scrapping

    //mam obiekt saveGame - może powinno być GameState (jednostka danych) - który służy do przechowywania stanu gry
    //mam interfejs Game service, który określa metody służące do sejwowania i ??(wczytywania stanu gry) - czyli parametrem Game service, powinno być w zasadzie SaveGame), a nie game i level
    //
    ;
    public void save(GameState gameState) {

        String filePath = "save.json";
        Gson gson = new Gson();

        //convert saveGame object to JSON
        String saveJson = gson.toJson(gameState);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(saveJson);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
