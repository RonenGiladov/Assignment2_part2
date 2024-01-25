// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;
import java.util.ArrayList;

public abstract class Game {
    protected char gameBoard[][] = new char[5][5];
    protected boolean isPlayerX = true;
    private boolean thereIsAWinner = false;


    // prints the board
    public synchronized void printBoard(char gameBoard[][]){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    // return true if its player X turn
    public synchronized boolean getTurn(){
        return isPlayerX;
    }

    // return an ArrayList as coordinates of all the board cells
    public synchronized ArrayList<Coordinate> getFreeCells(){
        ArrayList<Coordinate> freeCells = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(gameBoard[i][j] == '\0'){
                    Coordinate y_x = new Coordinate(j + 1, 5 - i); // Like x y board where y is horizontal
                    freeCells.add(y_x);
                }
            }
        }
        return freeCells;
    }

    // getter for board
    public char[][] getGameBoard(){
        return gameBoard;
    }

    // setting a new symbol on the board
    public synchronized void setGameBoard(Coordinate y_x, char X_O){
        // mapping the y coordinate to a row in a reversed manner
        int row = 5 - y_x.getY();
        int col = y_x.getX() - 1;
        gameBoard[row][col] = X_O;
    }

    // checking if the play resulted in a win by checking all the cols then all the rows, and finally all the possible diagonals
    public synchronized boolean hasWon(char symbol){
        // check cols then rows and diagonal
        if((symbol == gameBoard[0][0] && symbol == gameBoard[0][1] && symbol == gameBoard[0][2] && symbol == gameBoard[0][3]) ||
           (symbol == gameBoard[0][1] && symbol == gameBoard[0][2] && symbol == gameBoard[0][3] && symbol == gameBoard[0][4]) ||
           (symbol == gameBoard[1][0] && symbol == gameBoard[1][1] && symbol == gameBoard[1][2] && symbol == gameBoard[1][3]) ||
           (symbol == gameBoard[1][1] && symbol == gameBoard[1][2] && symbol == gameBoard[1][3] && symbol == gameBoard[1][4]) ||
           (symbol == gameBoard[2][0] && symbol == gameBoard[2][1] && symbol == gameBoard[2][2] && symbol == gameBoard[2][3]) ||
           (symbol == gameBoard[2][1] && symbol == gameBoard[2][2] && symbol == gameBoard[2][3] && symbol == gameBoard[2][4]) ||
           (symbol == gameBoard[3][0] && symbol == gameBoard[3][1] && symbol == gameBoard[3][2] && symbol == gameBoard[3][3]) ||
           (symbol == gameBoard[3][1] && symbol == gameBoard[3][2] && symbol == gameBoard[3][3] && symbol == gameBoard[3][4]) ||
           (symbol == gameBoard[4][0] && symbol == gameBoard[4][1] && symbol == gameBoard[4][2] && symbol == gameBoard[4][3]) ||
           (symbol == gameBoard[4][1] && symbol == gameBoard[4][2] && symbol == gameBoard[4][3] && symbol == gameBoard[4][4]) ||

           (symbol == gameBoard[0][0] && symbol == gameBoard[1][0] && symbol == gameBoard[2][0] && symbol == gameBoard[3][0]) ||
           (symbol == gameBoard[1][0] && symbol == gameBoard[2][0] && symbol == gameBoard[3][0] && symbol == gameBoard[4][0]) ||
           (symbol == gameBoard[0][1] && symbol == gameBoard[1][1] && symbol == gameBoard[2][1] && symbol == gameBoard[3][1]) ||
           (symbol == gameBoard[1][1] && symbol == gameBoard[2][1] && symbol == gameBoard[3][1] && symbol == gameBoard[4][1]) ||
           (symbol == gameBoard[0][2] && symbol == gameBoard[1][2] && symbol == gameBoard[2][2] && symbol == gameBoard[3][2]) ||
           (symbol == gameBoard[1][2] && symbol == gameBoard[2][2] && symbol == gameBoard[3][2] && symbol == gameBoard[4][2]) ||
           (symbol == gameBoard[0][3] && symbol == gameBoard[1][3] && symbol == gameBoard[2][3] && symbol == gameBoard[3][3]) ||
           (symbol == gameBoard[1][3] && symbol == gameBoard[2][3] && symbol == gameBoard[3][3] && symbol == gameBoard[4][3]) ||
           (symbol == gameBoard[0][4] && symbol == gameBoard[1][4] && symbol == gameBoard[2][4] && symbol == gameBoard[3][4]) ||
           (symbol == gameBoard[1][4] && symbol == gameBoard[2][4] && symbol == gameBoard[3][4] && symbol == gameBoard[4][4]) ||

           (symbol == gameBoard[0][0] && symbol == gameBoard[1][1] && symbol == gameBoard[2][2] && symbol == gameBoard[3][3]) ||
           (symbol == gameBoard[1][1] && symbol == gameBoard[2][2] && symbol == gameBoard[3][3] && symbol == gameBoard[4][4]) ||
           (symbol == gameBoard[1][0] && symbol == gameBoard[2][1] && symbol == gameBoard[3][2] && symbol == gameBoard[4][3]) ||
           (symbol == gameBoard[4][0] && symbol == gameBoard[3][1] && symbol == gameBoard[2][2] && symbol == gameBoard[1][3]) ||
           (symbol == gameBoard[3][1] && symbol == gameBoard[2][2] && symbol == gameBoard[1][3] && symbol == gameBoard[0][4]) ||
           (symbol == gameBoard[3][0] && symbol == gameBoard[2][1] && symbol == gameBoard[1][2] && symbol == gameBoard[0][3]) ||
           (symbol == gameBoard[4][1] && symbol == gameBoard[3][2] && symbol == gameBoard[2][3] && symbol == gameBoard[1][4]))
        {
             endGame();
             return true;
        }
        printBoard(gameBoard);
        return false;

    }

    // checking if someone has won
    public synchronized boolean thereIsAWinner(){
        return thereIsAWinner;

    }

    public synchronized void endGame(){
        System.out.println("The victor is:");
        thereIsAWinner = true;

    }

}
