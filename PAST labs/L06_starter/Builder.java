import java.util.*;

public class Builder extends Worker {
    private int slowSpeed;
    private int medSpeed;
    private int highSpeed;

    public Builder(String name, int low, int med, int high){
        super(name);
        if (low<0 || med<0 || high<0){
            this.slowSpeed=50;
            this.medSpeed=100;
            this.highSpeed=150;
        }
        else if (low>med || med>high){
            this.slowSpeed=50;
            this.medSpeed=100;
            this.highSpeed=150;
        }
        else {
            this.slowSpeed=low;
            this.medSpeed=med;
            this.highSpeed=high;
        }
    }

    public Builder(String name){
        this(name, 50, 100, 150);
    }
    public Builder(){
        this("John Doe", 50, 100, 150);
    }

    public String toString(){
        return "[Builder] "+super.toString() + " (sq ft/day: " +
                this.slowSpeed+"/"+this.medSpeed+"/"+
                this.highSpeed+")";
    }


    public int workNextDay(){
        if (super.getCurrentMood()==0){
            return slowSpeed;
        }
        else if (super.getCurrentMood()==1){
            return medSpeed;
        }
        else if (super.getCurrentMood()==2){
            return highSpeed;
        }
        return 0;
    }
}

