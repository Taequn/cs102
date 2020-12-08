import java.util.Arrays;

public class GameLauncher{
    
    //Our enumerated type!
    public enum PlayerType{Human, AIBasic, AILearning, AILearningDemo};
    
    //Controls what type of player for Players 1 and 2
    private static PlayerType Player1Type = PlayerType.Human;
    private static PlayerType Player2Type = PlayerType.AIBasic;

    //accepted inputs when asking users to play again
    private static final String PLAY_AGAIN_YES = "y";
    private static final String PLAY_AGAIN_NO = "n";
    private static final String[] PLAY_AGAIN_CHOICES = {PLAY_AGAIN_YES, PLAY_AGAIN_NO};
    
    
    private static Player createPlayer(PlayerType p){
        switch(p){
            case Human:
                return new Human();
            case AIBasic:
                return new BasicAI();  
            case AILearning:
            //    return new LearningAI();   //To be implemented by students!
            case AILearningDemo:
                return new LearningAIDemo();
            default:
                //Not a valid AI type!
                return null;
        }                        
    }
    
    //Initializes a Matchsticks game object
    private static Matchsticks initGame(String[] args){
        int sticks = -1;
        if (args.length > 0){
            try{
                sticks = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException nfe){
                System.out.println("Non numeric value entered for number of sticks, using default!");
            }
        }
        if (sticks > 0)     
            return new Matchsticks(createPlayer(Player1Type), createPlayer(Player2Type), sticks);     
        else                                        
            return new Matchsticks(createPlayer(Player1Type), createPlayer(Player2Type));     
    }
    
    
    
    private static void playGame(Matchsticks game){
        
        String again;  
        if (Matchsticks.DEBUG){
            System.out.println("\n\t********************************************");
            System.out.println("\t**           DEBUG MODE ENABLED           **");
            System.out.println("\t********************************************\n");   
        }
        do{ //keep playing so long as the user wants to continue
            game.playSingleGame();
            again = Matchsticks.getValidInput("Do you want to play again (y/n)?: ", PLAY_AGAIN_CHOICES);
        } while (again.equalsIgnoreCase(PLAY_AGAIN_YES));
        System.out.println("Thanks for playing!");
        
    }    
    
    
    
    public static void main(String[] args){
        Matchsticks game = initGame(args);
        playGame(game);
    }
    
    

        
    
}