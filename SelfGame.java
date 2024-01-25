// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;
import java.util.ArrayList;
import java.util.Random;

public class SelfGame extends Game{

    private boolean playerXHasWon = false;
    private boolean playerOHasWon = false;

    public synchronized boolean getPlayerXHasWon(){
        return playerXHasWon;
    }
    public synchronized boolean getPlayerOHasWon(){
        return playerOHasWon;
    }

    public synchronized void playX(){
        if(playerOHasWon){
            return;
        }
        try {
            while (!getTurn()){
                if(playerOHasWon){
                    return;
                }
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(getFreeCells().size() > 0){
            Random random = new Random();
            int arrSize = getFreeCells().size();
            // getting a free random cell from the ArrayList and setting it
            int randCellNumArr = random.nextInt(arrSize);
            Coordinate newCoordPlay = getFreeCells().get((randCellNumArr));
            // if the cell is already occupied
            if(getGameBoard()[5 - newCoordPlay.getY()][newCoordPlay.getX() - 1] == 'X' || getGameBoard()[5 - newCoordPlay.getY()][newCoordPlay.getX() - 1] == 'O'){
                return;
            }
            setGameBoard(newCoordPlay, 'X');
            if(hasWon('X')){
                printBoard(getGameBoard());
                System.out.println("Player X has won!");
                playerXHasWon = true;
                notifyAll();
                return;
            }

        }else{
            if (hasWon('O')){
                System.out.println("Player O has won");
            }
            if (hasWon('X')){
                System.out.println("Player X has won");
            }
            System.out.println("the board is full");
            return;
        }
        isPlayerX = false;
        notifyAll();
    }
    public synchronized void playO(){
        if(playerXHasWon){
            return;
        }
        try {
            while (getTurn()){
                if(playerXHasWon){
                    return;
                }
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(getFreeCells().size() > 0){
            Random random = new Random();
            int arrSize = getFreeCells().size();
            // getting a free random cell from the ArrayList and setting it
            int randCellNumArr = random.nextInt(arrSize);
            Coordinate newCoordPlay = getFreeCells().get((randCellNumArr));
            // if the cell is already occupied
            if(getGameBoard()[5 - newCoordPlay.getY()][newCoordPlay.getX() - 1] == 'O' || getGameBoard()[5 - newCoordPlay.getY()][newCoordPlay.getX() - 1] == 'X'){
                return;
            }
            setGameBoard(newCoordPlay, 'O');
            if(hasWon('O')){
                printBoard(getGameBoard());
                System.out.println("Player O has won!");
                playerOHasWon = true;
                notifyAll();
                return;
            }

        }else{
            if (hasWon('O')){
                System.out.println("Player O has won");
            }
            if (hasWon('X')){
                System.out.println("Player X has won");
            }
            System.out.println("the board is full");
            return;
        }
        isPlayerX = true;
        notifyAll();
    }

}
