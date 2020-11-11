import java.util.*;

public class Logger extends Worker {
    private int logsCut;

    public Logger(String name, int logs){
        super(name);
        if (logs<40)
            this.logsCut=40;
        else
            this.logsCut=logs;
    }

    public Logger(String name){
        this(name, 40);
    }

    public Logger(){
        this("John Doe", 40);
    }

    public String toString(){
        return "[Logger] "+super.toString() + " (avg logs/day: " +
                this.logsCut + ")";
    }


    public int workNextDay(){
        double percent=0;
        if (super.getCurrentMood()==0){
            percent=this.logsCut*0.75;
        }
        else if (super.getCurrentMood()==1){
            percent=this.logsCut;
        }
        else if (super.getCurrentMood()==2){
            percent=this.logsCut*1.25;
        }
        int answer = (int) percent;
        return answer;
    }
}

