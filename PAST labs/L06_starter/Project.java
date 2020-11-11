import java.util.*;

public class Project {
    private String name;
    private int rqFeet;
    private int duration;
    private ArrayList <Worker> workers = new ArrayList<Worker>();

    public Project(String projectName, int feet, int time) {
        if (feet <= 0)
            this.rqFeet = 450;
        else
            this.rqFeet = feet;

        if (time <= 0)
            this.duration = 10;
        else
            this.duration = time;

        if (projectName == null)
            this.name = "East Hall";
        else
            this.name = projectName;
    }

    public boolean assignWorker(Worker w){
        if (w==null)
            return false;

        for (int i=0; i<workers.size(); i++){
            if (workers.get(i).equals(w))
                return false;
        }

        workers.add(w);
        return true;
    }

    public boolean unassignWorker(Worker w){
        for (int i=0; i<workers.size(); i++){
            if (workers.get(i).equals(w)){
                workers.remove(i);
                return true;
            }
        }
        return false;
    }

    public void printWorkers(){
        for (int i=0; i<workers.size(); i++){
            System.out.println(workers.get(i));
        }
    }

    public boolean runSimulation(){
        int count=0;
        int[] stash = new int[2];
        int[] gains = new int[3];
        int leftToBuild = rqFeet;
        while (count<duration){
            gains[2] = 0;
            if (leftToBuild<=0) {
                //The "West Hall" completed successfully after 3 days!
                System.out.println("\n\n The \"" + this.name + "\" project was completed "+
                        "succesfully after " + count + " days!");
                return true;
            }
            int progress=0;
            System.out.println("--- Day " + (count+1) +" ---");
            System.out.println("--- Starting Totals --- Stash: " +
                    stash[0] + " logs and " + stash[1] + " stone " +
                    leftToBuild+ " sq feet left to build");

            for (int i=0; i<workers.size(); i++){
                System.out.println(workers.get(i));

                if (workers.get(i) instanceof Logger){
                    stash[0] += workers.get(i).workNextDay();
                    gains[0] = workers.get(i).workNextDay();
                }

                else if (workers.get(i) instanceof Miner){
                    stash[1] += workers.get(i).workNextDay();
                    gains[1] = workers.get(i).workNextDay();
                }

                else if (workers.get(i) instanceof Builder){
                    int workForToday = workers.get(i).workNextDay();
                    if (count!=0){
                        if (workForToday<stash[0] && workForToday<stash[1]){
                            progress+=workForToday;
                            leftToBuild-=workForToday;
                            stash[0]-=workForToday;
                            stash[1]-=workForToday;
                            gains[2]+=workForToday;
                        }

                        else if (workForToday>Math.min(stash[0], stash[1])) {

                            progress+=Math.min(stash[0], stash[1]);
                            leftToBuild-=Math.min(stash[0], stash[1]);
                            stash[0]-=Math.min(stash[0], stash[1]);
                            stash[1]-=Math.min(stash[0], stash[1]);

                            gains[2]+=Math.min(stash[0], stash[1]);

                        }
                    }

                }


            }

            System.out.println("--- Day Totals --- " +
                    gains[0] + " logs and " + gains[1] + " stone gained today, " +
                    gains[2] + " sq feet built!");

            count++;
        }
        System.out.println("\n\nSorry! We didn't have enough time to build it!");
        return false;
    }

    public static void main(String[] arrgs){
        //CAREFUL
        //SOME MASSIVE TESTING IS HAPPENING BELOW

        //Worker test1 = new Logger("John", 100);
        //Worker test1 = new Logger("John", 30);
        //Worker test1 = new Logger("John", -10);
        //Worker test1 = new Logger("John");
        //Worker test1 = new Logger();

        //System.out.println(test1);


        //Worker test2 = new Miner("Peter", 100);
        //Worker test2 = new Miner("Peter", -10);
        //Worker test2 = new Miner("Peter", 30);
        //Worker test2 = new Miner("Peter");
        //Worker test2 = new Miner();
        //System.out.println(test2);


        //Worker test3 = new Builder("Peter", 50, 100, 150);
        //Worker test3 = new Builder("Peter", 100, 50, 150);
        //Worker test3 = new Builder("Peter", 150, 100, 50);
        //Worker test3 = new Builder("Peter", 10, 20, 30);
        //Worker test3 = new Builder("Peter", -10, -20, -30);
        //Worker test3 = new Builder("Peter", -30, -20, -10);
        //Worker test3 = new Builder("Peter");
        //Worker test3 = new Builder();

        //System.out.println(test3);

        /*
        Worker test4 = new Builder("Jonathan", 50, 100, 150);
        Worker test5 = new Builder("Rick", 50, 100, 150);


        Project testProject = new Project("West", 150, 5);
        testProject.assignWorker(test1);
        testProject.assignWorker(test2);
        testProject.assignWorker(test3);
        testProject.assignWorker(test4);

        testProject.runSimulation();
         */

    }
}

