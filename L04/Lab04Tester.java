/**
 * Tester file for Lab 04 - SAT Solver
 *
 * Colgate University COSC 290L
 * Updated 2021
 */
public class Lab04Tester {
    
    
    public static void main(String[] args) {
        
        //Create a proposition and retrieve its variables
        //CNFProposition proposition = CNFProposition.fromString("(p | q) & (~p | ~q) & (p | ~q) & (~p | q)");
        CNFProposition proposition = CNFProposition.fromString("(p | q) & (r | y)");
        //(p)∧ (q∨ r)∧ (s∨ ¬ q)∧ (t∨ r) ∧( ¬ q∨ t)
        System.out.println("The CNFProposition is " + proposition);
        //System.out.println("It contains these variables " + proposition.getAllVariables());
        //System.out.println(proposition.getClauses());
        
        //This proposition SHOULD be satisfiable (ex: if p and r are true)
        boolean satisfiable = proposition.isSatisfiable();
        System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");
        
        
        //Is this proposition satisfiable?
        //proposition = CNFProposition.fromString("(p | q) & (~p | ~q) & (p | ~q) & (~p | q)");
        //satisfiable = proposition.isSatisfiable();
        //System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");


        //tests
        //two clauses
        //proposition = CNFProposition.fromString("(~p | ~q) & (p | q)");
        //proposition = CNFProposition.fromString("(p | q) & (~p | ~q)");

        //one clause
        //proposition = CNFProposition.fromString("(p | ~q)");

        //three clauses
        //proposition = CNFProposition.fromString("(p | ~q | r)");
        //proposition = CNFProposition.fromString("(p | q | r) & (~p | ~q | ~r)");

        //different variables
        //proposition = CNFProposition.fromString("(p | q) & (r | y)");

        //a lot of different variables to test recurssive calls
        //proposition = CNFProposition.fromString("(p | q) & (j | i) & (a | b | c) & (z | y)");


        //boolean satisfiable = proposition.isSatisfiable();
        //System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");
        System.out.println("Number of recurssive calls: " + proposition.getLastSearchCost());
    }
    
    
}
