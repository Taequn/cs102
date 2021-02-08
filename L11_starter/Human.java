import java.util.ArrayList;

public class Human implements Player{
    private ArrayList<Integer> history;


    public void initPlayer(int startingSticks){
        history = new ArrayList<Integer>();
    }
    
    public int takeTurn(int remainingSticks){
        int maxChoiceThisTurn =  Math.min(remainingSticks, Matchsticks.MAX_STICKS_CHOICE);
        String prompt = "How many sticks will you take (1 - " + maxChoiceThisTurn + ")?: ";
        int choice = Matchsticks.getValidInput(prompt, 1, maxChoiceThisTurn);
        history.add(choice);
        return choice;
    }

    public String getDebugInfo(){
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
        history = new ArrayList<Integer>();
    }
    
    public String getPlayerType(){
        return "Human";
    }
    
}