// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;

public class UserPlayer implements Runnable{

    private PlayerType threadPlayerType;
    private UserGame userGame;
    private boolean isUserGame;

    // constructor for userGame
    public UserPlayer(UserGame userGame, PlayerType threadPlayerType){
        this.userGame = userGame;
        isUserGame = true;
        this.threadPlayerType = threadPlayerType;
    }

    public void run(){
        boolean flag = true;
        while (flag){
            // user game
            if(isUserGame) {
                if (threadPlayerType.getPlayerSymbol() == 'X') {
                    userGame.userPlayX();
                } else {
                    userGame.userPlayO();
                }
                if (userGame.getFreeCells().size() <= 0) {
                    System.out.println("the board is full");
                    return;
                }
                // end game if someone has won
                if (userGame.thereIsAWinner()) {
                    flag = false;
                }
            }
        }
    }
}