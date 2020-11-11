
//Client code used to launch our game
public class GameLauncher{ 

    //create and run the game!
    public static void main(String[] args) {        
        AbstractGame game = new TetrisGame();
        game.run();    
    }
    
}
