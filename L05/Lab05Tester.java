/**
 * Tester file for Lab 05 - SAT Solver Part 2
 *
 * Colgate University COSC 290L
 * Updated 2021
 */
public class Lab05Tester {



    static final int TIMEOUT = 5 * 60 * 1000; // 5 minutes in milliseconds

    
    
    
    public static void main(String[] args) {
        //Verify correctness before running benchmark
        //Create a proposition and retrieve its variables
        CNFProposition proposition = CNFProposition.fromString("(p | q) & (~p | r)");
        boolean satisfiable = proposition.isSatisfiable();
        System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");
        
        
        //Is this proposition satisfiable?
        proposition = CNFProposition.fromString("(p | q) & (~p | ~q) & (p | ~q) & (~p | q)");
        satisfiable = proposition.isSatisfiable();
        System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");
           
        //Run benchmark
        runBenchmark();
    }
        
    /**
     * Runs SatSolver on a collection of randomly generated CNF propositions.  See detailed comment below.
     */
    public static void runBenchmark() {
        long totalTime = 0;
        int totalCost = 0;        
        int n = 20;  // number of variables
        int k = 3;   // variables per clause
        int numTrials = 10;    // how many random CNF propositions to generate (at each ratio)
        double[] ratios = new double[] { 1, 2, 3, 4, 5, 6, 7, 8};  // ratio r = # clauses / # variables


        // for each ratio r in ratios:
        //     repeat numTrials times:
        //         - generate a random CNF proposition with given n variables, having k variables per clause, and m
        //           clauses where m = r*n
        //         - check whether the proposition is satisfiable
        //         - record how many recursive calls were made (search cost)
        //         - record how long it took (milliseconds)
        //     print out: results for each ratio
        //     Example: "Ratio r=8.0 fraction sat=0.0 avg. cost=212.3 avg. time=98.1"

        try {

            int seed = 0;
            for (double ratio : ratios) {
                double sats = 0;
                double cost = 0;
                double time = 0;

                int numClauses = (int) Math.round(ratio*n);
                PropositionGenerator generator = new PropositionGenerator(n, numClauses, k, seed++);
                for (int i = 0; i < numTrials; i++) {
                    CNFProposition proposition = generator.generateRandomInstance();
                    long start = System.currentTimeMillis();
                    boolean satisfiable = proposition.isSatisfiable();
                    sats += satisfiable? 1 : 0;
                    cost += proposition.getLastSearchCost();
                    time += (System.currentTimeMillis() - start);
                    if (totalTime + time > TIMEOUT) {
                        throw new TimeOutException();
                    }
                }
                totalTime += time;
                totalCost += cost;
                System.out.println("Ratio r=" + ratio + " fraction sat=" + sats/numTrials + " avg. cost=" + cost/numTrials + " avg. time=" + time/numTrials);
            }
            System.out.println("Your implementation took " + totalTime + " milliseconds to complete.");
            System.out.println("Your implementation made " + totalCost + " recursive calls.");
        } catch (TimeOutException e) {
            System.out.println("Implementation timed out!");
            totalTime = TIMEOUT;
            totalCost = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("An exception occurred!");
            e.printStackTrace();
            totalTime = TIMEOUT;
            totalCost = Integer.MAX_VALUE;
        }
    }



    private static class TimeOutException extends RuntimeException { }
}
