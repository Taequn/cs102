import java.util.*;

public class Miner extends Worker {
    private int stonesMined;

    public Miner(String name, int stones){
        super(name);
        if (stones<60)
            this.stonesMined=60;
        else
            this.stonesMined=stones;
    }
    public Miner(String name){
        this(name, 60);
    }
    public Miner(){
        this("John Doe", 60);
    }

    public String toString(){
        return "[Miner] "+super.toString() + " (avg stones/day: " +
                this.stonesMined+ ")";
    }


    public int workNextDay(){
        double percent=0;

        if (super.getCurrentMood()==0)
            percent=this.stonesMined*0.5;
        else if (super.getCurrentMood()==1)
            percent=this.stonesMined;
        else if (super.getCurrentMood()==2)
            percent=this.stonesMined*1.50;
        int answer = (int) percent;
        return answer;
    }
}

