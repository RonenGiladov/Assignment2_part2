// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please choose a game type:\n1. User Game\n2. Self Game\n");
        int userNum = scan.nextInt();
        scan.nextLine(); // Clean the buffer
        String userString;
        char userSymbol;
        int userX , userY;
        // User Game
        if(userNum == 1) {
            System.out.println("Please choose your symbol: O or X (big letters)");
            userString = scan.next();
            userSymbol = userString.charAt(0);
            UserGame newGame = new UserGame();
            PlayerType playerTypeO = new PlayerType('O');
            PlayerType playerTypeX = new PlayerType('X');
            // if the player wants for its thread to play O
            if (userSymbol == 'O') {
                UserPlayer userPlayer = new UserPlayer(newGame, playerTypeO);
                SelfPlayer selfPlayer = new SelfPlayer(newGame, playerTypeX);
                Thread userThread = new Thread(userPlayer);
                Thread selfThread = new Thread(selfPlayer);
                userThread.start();
                selfThread.start();

                try {
                    userThread.join();
                    selfThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                // if the player wants for its thread to play X
            }else {
                UserPlayer userPlayer = new UserPlayer(newGame, playerTypeX);
                SelfPlayer selfPlayer = new SelfPlayer(newGame, playerTypeO);
                Thread userThread = new Thread(userPlayer);
                Thread selfThread = new Thread(selfPlayer);
                userThread.start();
                selfThread.start();

                try {
                    userThread.join();
                    selfThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        // self game of two threads
        else{
            PlayerType playerTypeO = new PlayerType('O');
            PlayerType playerTypeX = new PlayerType('X');
            SelfGame newGame = new SelfGame();
            SelfPlayer player1 = new SelfPlayer(newGame,playerTypeX);
            SelfPlayer player2 = new SelfPlayer(newGame,playerTypeO);
            Thread threadPlayer1 = new Thread(player1);
            Thread threadPlayer2 = new Thread(player2);
            threadPlayer1.start();
            threadPlayer2.start();

            try{
                threadPlayer1.join();
                threadPlayer2.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
