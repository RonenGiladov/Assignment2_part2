// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;

public class Coordinate {
    private int x;
    private int y;

    // Default constructor
    public Coordinate(){
        x = 0;
        y = 0;
    }
    // constructor
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        String str = "(";
        str = str + x + ", " + y + ")";
        return str;
    }
}
