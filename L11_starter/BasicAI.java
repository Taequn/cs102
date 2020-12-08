import java.util.ArrayList;

public class BasicAI implements Player{
    
    private ArrayList<Integer> history;
    
    
    
    public void initPlayer(int startingSticks){
        history = new ArrayList<Integer>();
    }
    
    public int takeTurn(int remainingSticks){
        int maxChoice = Math.min(Matchsticks.MAX_STICKS_CHOICE, remainingSticks); //make sure choice is not > sticks left
        int choice = Matchsticks.rand.nextInt(maxChoice) + 1;
        history.add(choice);
        return choice;
    }
    
    public String getDebugInfo(){
        //Print turn history for the current game
        String debug = "Previous Moves: ";
        if (history.size() == 0)
            return debug + "no turns taken yet!";
        for(int i = 0; i < history.size(); i++){
            debug += "Turn " + (i+1) + ": " + history.get(i);
            if (i != history.size()-1)
                debug += ", ";
        }
        return debug;
    }
    
    public void gameOver(boolean didPlayerWin){
        //clear the history for the next game
        history = new ArrayList<Integer>();
    }
    
    public String getPlayerType(){
        return "Basic AI";
    }
    
}