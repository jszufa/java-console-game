public class Main {
    public static void main(String[] args) {

        var game = new Game();
        game.printCharArray();
    }

}


//gra:
//
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