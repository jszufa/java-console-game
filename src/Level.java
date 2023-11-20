public class Level extends Game{

    String label;

    Hero initHero = null;
    Stone initStone = null;
    Hole initHole = null;
    Trap initTrap = null;
    public Level (String label){
        this.label = label;
        //CODE HERE
        //SAVE INITIAL SETTING
        //to powinien być zestaw współrzędnych, tak żeby kiedy przekaże się ten zestaw w konstruktorze levelu, to powstanie gotowy level
        //może przeładować konstruktor tutaj albo w game??
    }

}
