// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignmet2_part2
package XO;

public class SelfPlayer extends Player implements Runnable{

    private PlayerType threadPlayerType;
    private UserGame userGame;
    private boolean isUserGame;
    private SelfGame selfGame;
    private boolean isSelfGame;

    // constructor for userGame
    public SelfPlayer(UserGame userGame, PlayerType threadPlayerType){
        this.userGame = userGame;
        isUserGame = true;
        isSelfGame = false;
        this.threadPlayerType = threadPlayerType;
    }

    // constructor for selfGame
    public SelfPlayer(SelfGame selfGame, PlayerType threadPlayerType){
        this.selfGame = selfGame;
        isSelfGame = true;
        isUserGame = false;
        this.threadPlayerType = threadPlayerType;

    }

    public void run(){
        boolean flag = true;
        while (flag){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // user game
            if(isUserGame){
                if(userGame.getFreeCells().size() <= 0) {
                    System.out.println("The board is full");
                    return;
                }
                if(threadPlayerType.getPlayerSymbol() == 'X'){
                    userGame.playX();
                }else{
                    userGame.playO();
                }
                // end game if someone has won
                if(userGame.thereIsAWinner()){
                    if(userGame.getPlayerOHasWon()){
                        System.out.println("Player O has won");
                    }
                    if(userGame.getPlayerXHasWon()){
                        System.out.println("Player X has won");

                    }
                    flag = false;
                }

                    // self game
            }else{
                if(selfGame.getFreeCells().size() <= 0) {
                    System.out.println("the board is full");
                    return;
                }
                if(threadPlayerType.getPlayerSymbol() == 'X'){
                    selfGame.playX();
                }else{
                    selfGame.playO();
                }
                // end game if someone has won
                if(selfGame.thereIsAWinner()){
                    flag = false;
                }
            }

        }
    }
}
