// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;

public abstract class Player{
    private PlayerType X = new PlayerType('X');
    private PlayerType O = new PlayerType('O');

    public PlayerType getX() {
        return X;
    }

    public PlayerType getO() {
        return O;
    }
}
