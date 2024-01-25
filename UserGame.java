// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserGame extends Game{

    private boolean playerXHasWon = false;
    private boolean playerOHasWon = false;


    public synchronized boolean getPlayerXHasWon(){
        return playerXHasWon;
    }
    public synchronized boolean getPlayerOHasWon(){
        return playerOHasWon;
    }
    // self thread player with the symbol X
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

        // if there is a free cell on the board play
        if(getFreeCells().size() > 0){
            Random random = new Random();
            int arrSize = getFreeCells().size();
            // getting a free random cell from the ArrayList and setting it
            int randCellNumArr = random.nextInt(arrSize);
            Coordinate newCoordPlay = getFreeCells().get((randCellNumArr));
            // placing a move
            setGameBoard(newCoordPlay, 'X');
            if(hasWon('X')){
                System.out.println("The thread has won!");
                printBoard(getGameBoard());
                playerXHasWon = true;
                notifyAll();
                return;
            }
        // if there is no free space
        }else{
            if (hasWon('O')){
                System.out.println("Player O has won");
            }
            if (hasWon('X')){
                System.out.println("Player X has won");
            }
            System.out.println("The board is full");
            return;
        }
        isPlayerX = false;
        notifyAll();
    }
    // self thread player with the symbol O
    public synchronized void playO(){
        if(playerXHasWon){
            return;
        }
        try {
            while (getTurn()){
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

            setGameBoard(newCoordPlay, 'O');
            if(hasWon('O')){
                System.out.println("The thread has won!");
                printBoard(getGameBoard());
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
            System.out.println("The board is full");
            return;
        }
        isPlayerX = true;
        notifyAll();
    }

    // user thread player with the symbol X
    public synchronized void userPlayX(){
        if(playerOHasWon){
            return;
        }
        try{
            while(!getTurn()){
                if(playerOHasWon){
                    return;
                }
                wait();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        // get the user coordinate
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the thread a play to place " + 'X' + " (write x and y coordinates" +
                " x goes 1 to 5 left to right ,y goes 1 to 5 down to up)");
        int userX = scan.nextInt();
        int userY = scan.nextInt();
        scan.nextLine(); // clean buffer
        Coordinate userCoor = new Coordinate(userX, userY);
        ArrayList<Coordinate> tempArr = getFreeCells();
        // if cell is occupied
        if(getGameBoard()[5 - userY][userX - 1] == 'O' || getGameBoard()[5 - userY][userX - 1] == 'X'){
            System.out.println("The cell is already occupied");
            return;
        }

        // playing the move and checking if won
        setGameBoard(userCoor, 'X');
        getFreeCells();
        if(hasWon('X')){
            setGameBoard(userCoor, 'X');
            System.out.println("You have won!");
            playerXHasWon = true;
            printBoard(getGameBoard());
            notifyAll();
            return;
        }

        isPlayerX = false;
        notifyAll();

    }
    // user thread player with the symbol O
    public synchronized void userPlayO(){
        if(playerXHasWon){
            return;
        }
        try{
            while(getTurn()){
                if(playerXHasWon){
                    return;
                }
                wait();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        // get the user coordinate
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the thread a play to place " + 'O' + " (write x and y coordinates" +
                " x goes 1 to 5 left to right ,y goes 1 to 5 down to up)");
        int userX = scan.nextInt();
        int userY = scan.nextInt();
        scan.nextLine(); // clean buffer
        Coordinate userCoor = new Coordinate(userX, userY);
        ArrayList<Coordinate> tempArr = getFreeCells();
        // if cell is occupied
        if(getGameBoard()[5 - userY][userX - 1] == 'X' || getGameBoard()[5 - userY][userX - 1] == 'O'){
            System.out.println("The cell is already occupied");
            return;
        }

        setGameBoard(userCoor, 'O');
        getFreeCells();
        if(hasWon('O')){
            setGameBoard(userCoor, 'O');
            System.out.println("You have won!");
            playerOHasWon = true;
            printBoard(getGameBoard());
            notifyAll();
            return;
        }

        isPlayerX = true;
        notifyAll();
    }

}
