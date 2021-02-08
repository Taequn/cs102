import java.util.ArrayList;

/**
 .---.  .---.     .-''-.    .---.     .---.       ,-----.
 |   |  |_ _|   .'_ _   \   | ,_|     | ,_|     .'  .-,  '.
 |   |  ( ' )  / ( ` )   ',-./  )   ,-./  )    / ,-.|  \ _ \
 |   '-(_{;}_). (_ o _)  |\  '_ '`) \  '_ '`) ;  \  '_ /  | :
 |      (_,_) |  (_,_)___| > (_)  )  > (_)  ) |  _`,/ \ _/  |
 | _ _--.   | '  \   .---.(  .  .-' (  .  .-' : (  '\_/ \   ;
 |( ' ) |   |  \  `-'    / `-'`-'|___`-'`-'|___\ `"/  \  ) /
 (_{;}_)|   |   \       /   |        \|        \'. \_/``".'
 '(_,_) '---'    `'-..-'    `--------``--------`  '-----'
 ,---.    ,---.   ____   ,---------. ,---------.
 |    \  /    | .'  __ `.\          \\          \
 |  ,  \/  ,  |/   '  \  \`--.  ,---' `--.  ,---'
 |  |\_   /|  ||___|  /  |   |   \       |   \
 |  _( )_/ |  |   _.-`   |   :_ _:       :_ _:
 | (_ o _) |  |.'   _    |   (_I_)       (_I_)
 |  (_,_)  |  ||  _( )_  |  (_(=)_)     (_(=)_)
 |  |      |  |\ (_ o _) /   (_I_)       (_I_)
 '--'      '--' '.(_,_).'    '---'       '---'

 */



public class LearningAI implements Player{
    private ArrayList<ArrayList<Integer>> buckets;
    private int[] putAside;
    private ArrayList<Integer> history;

    public void initPlayer(int startingSticks){
        history = new ArrayList<Integer>();
        this.buckets = new ArrayList<>();
        this.putAside = new int[startingSticks+1];

        for (int j=0; j<=startingSticks; j++){
            buckets.add(new ArrayList<Integer>());
            for(int i=1; i<=j && i<=3; i++)
                this.buckets.get(j).add(i);
        }
    }

    public int takeTurn(int remainingSticks){
        ArrayList<Integer> options = this.buckets.get(remainingSticks);
        int matchstickToRemove = Matchsticks.rand.nextInt(options.size());

        this.putAside[remainingSticks] = options.remove(matchstickToRemove);
        history.add(putAside[remainingSticks]);
        return putAside[remainingSticks];
    }

    public void gameOver(boolean didPlayerWin){
        history = new ArrayList<Integer>();
        for (int i=0; i<this.putAside.length; i++){
            ArrayList<Integer> puttingBack = this.buckets.get(i);
            if (this.putAside[i]>0){
                boolean ifEmpty = (puttingBack.size()==0);
                if (didPlayerWin || ifEmpty)
                    puttingBack.add(this.putAside[i]);
                if (didPlayerWin)
                    puttingBack.add(this.putAside[i]);
            }
        }
        this.putAside = new int[this.putAside.length];
    }

    protected String bucketsDebug(){
        String bucketDebug = "Buckets: ";
        for (int i=1; i<this.buckets.size(); i++){
            bucketDebug+= i + ":" + this.buckets.get(i) + " ";
        }
        return bucketDebug+"}";
    }
    protected String putAsideDebug(){
        String marblesDebug = "Marbles put aside: ";

        for(int i=1; i<this.putAside.length; i++){
            marblesDebug+= i + ":" + this.putAside[i]+" ";
        }
        return marblesDebug + "}";
    }

    public String getDebugInfo(){
        String debug = this.bucketsDebug()+", \n";
        debug+=this.putAsideDebug()+", \n";
        for(int i = 0; i < history.size(); i++){
            debug += "Turn " + (i+1) + ": " + history.get(i);
            if (i != history.size()-1)
                debug += ", ";
        }
        return debug;
    }
    
    public String getPlayerType(){
        return "Learning AI";
    }
    
}