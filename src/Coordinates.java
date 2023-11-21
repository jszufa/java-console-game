public class Coordinates {
    int x;
    int y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    //overload
    public Coordinates (Coordinates coordinatesToCopy) {
        this.x = coordinatesToCopy.x;
        this.y = coordinatesToCopy.y;
    }
}
