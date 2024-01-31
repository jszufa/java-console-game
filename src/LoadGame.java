import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadGame {

    public static void loadGame() {
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

        //Odczytaj wartości konkretnych pól obiektu SaveGame -- muszą mieć ustawiony dostęp na public, inaczej nie mam dostępu
        System.out.println(saveGame.level.label);


        //Stwórz nową grę lub zaktualizuj wartości obecnej gry
        //np. this.levelCount = x
        // i = y (wydaje się, że fajnie byłoby dodać iterator jako pole obiektu - będzie też wtedy łatwiej odczytać je w save game i nie trzeba będzie go przekazywać)
        //actualLevel = new Level (i tutaj wstrzykuję wartości z save'a), tylko odpowiedni konstruktor trzeba by zrobić.
        //To chyba będzie najprostsze


        //na koniec można otestować nowy kod, który napisałem, ale wcześniej lepiej napisać do Miśka
    }
}
