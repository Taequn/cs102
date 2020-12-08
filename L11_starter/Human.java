import java.util.ArrayList;

public class Human implements Player{
    

    
    public void initPlayer(int startingSticks){
        //nothing to initialize here for the human player (...yet)
    }
    
    public int takeTurn(int remainingSticks){
        int maxChoiceThisTurn =  Math.min(remainingSticks, Matchsticks.MAX_STICKS_CHOICE);
        String prompt = "How many sticks will you take (1 - " + maxChoiceThisTurn + ")?: ";
        return Matchsticks.getValidInput(prompt, 1, maxChoiceThisTurn);
    }
    
    
    public String getDebugInfo(){
        return "Human Debug info not implemented!";
    }
    
    public void gameOver(boolean didPlayerWin){
        //not implemented yet
    }
    
    public String getPlayerType(){
        return "Human";
    }
    
}