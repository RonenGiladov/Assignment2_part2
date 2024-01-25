// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;

public class PlayerType {
    //private char name;
    private boolean isX;
    private boolean isY;
    private boolean hisTurn;

    public PlayerType(char X_O){
        if(X_O == 'X'){
            isX = true;
            isY = false;
        }else {
            isY = true;
            isX = false;
        }
        hisTurn = true;
    }

    public char getPlayerSymbol(){
        if(isX){
            return 'X';
        }
        return 'O';
    }
}
