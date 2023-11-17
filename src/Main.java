import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        var game = new Game();

        var level1 = new Level("Level 1");
        var level2 = new Level("Level 2");
        var level3 = new Level("Level 3");

        Level[] levels = {level1, level2, level3};

        outerloop:
        for (Level level : levels) {

            while (!level.victory && !level.gameOver) {
                while (!input.toLowerCase().equals("quit")) {

                    if (input.toLowerCase().equals("reset")) {
                        //tutaj będzie inaczej
                        level = new Level(level.label);
                    }

                    clearConsole();
                    System.out.println(level.label);
                    System.out.println();
                    level.printCharArray();


                    if (game.victory) {
                        System.out.print("------VICTORY!--------");
                        break;
                    } else if (game.gameOver) {
                        game.printGameOver();
                        break;
                    }

                    System.out.print("Enter command: ");
                    input = scanner.next();
                    level.handleCommand(input);

                    if (level.victory) {
                        System.out.print("------VICTORY!--------");
                        break;
                    } else if (level.gameOver) {
                        game.printGameOver();
                        break outerloop;
                    }
                }
            }
        }
    }

    public static void clearConsole() {
        int linesToClear = 50;
        for (int i = 0; i < linesToClear; i++) {
            System.out.println();
        }
    }

}


//gra:
//1
//Napisz grę w C# w której ludek reprezentowany przez H porusza się po mapie (na razie kwadratowej) ograniczonej przez +.
//
//Na mapie jest głaz O i dziura X oraz pułapka 8.
//
//Ludkiem porusza się wpisując w, s, a, d i klikając enter. Następnie gra czyście ekran konsoli i rysuje mapę jeszcze raz z ludkiem w nowym miejscu
//
//Wchodzenie na ściany nic nie zmienia, wchodzenie na pułapkę zabija, wejście na głaz przesuwa i ludka, i głaz (popycha głaz - jeśli się da) pchnięcie głazu na pułapkę zabija a na dziurę wygrywa grę.
//
//wpisanie reset resetuję grę, quit wyłącza.
//
//Miej na uwadzę że mogą pojawić się nowe mapy i nowe rzeczy/istoty (trzeba pomyśleć o obiektach i jak przechowywać levele)

//2
//Następnym krokiem będzie zrobienie 3 leveli.
//Zastanów się jak to zrobić żeby te level były jakoś zapisane i ładowały się jeden po drugim jak kamień dostanie się do dziury (nowe pole).
//Dodaj słowo "reset" które resetuje level.
//Level musi mieć zapisaną pozycję gracza, kamienia, wyjścia i ścian (na razie zrób żeby mapa zawsze była kwadratem takim samem, ale plusy mogą też być w środku i być przeszkodami)

