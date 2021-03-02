import java.util.*;
import java.util.stream.Collectors;

/**
 * A CNFProposition object represents a proposition in conjunctive normal form (CNF).  A CNF proposition is a conjunction of one or more
 * clauses, where each clause a disjunction of one or more literals.
 * 
 * Colgate University COSC 290L
 * Updated 2021
 */

public class CNFProposition {
    
    //Collection of the Propositions clauses
    private Set<Clause> clauses = new HashSet<>();
    
    //tracks recursive calls of previous search
    //For testing -- please don't modify
    private int numRecursiveCalls = -1; 
    
    public CNFProposition(Collection<Clause> clauses) {
        if (clauses.size() == 0) {
            throw new RuntimeException("Must contain at least one clause!");
        }
        this.clauses.addAll(clauses);
    }
    
    //Retrieves the clauses that construct the CNF proposition
    public Set<Clause> getClauses() {
        return clauses;
    }
    
    
    /**
     * Returns a Set that contains all of the variables that appear in any
     * clause in this proposition.
     * @return a set of Variable objects
     */
    public Set<Variable> getAllVariables() {
        Set<Variable> allVariables = new HashSet<>();
        for(Clause cls: getClauses())
            allVariables.addAll(cls.getAllVariables());
        return allVariables;
    }
    
    
    
    /**
     * Returns the number of recursive calls made during the previous execution of isSatisfiable.
     * @return number of recursive calls made in execution of isSatisfiable.
     */
    public int getLastSearchCost() {
        return numRecursiveCalls;
    }
    
    
    /**
     * Checks whether the proposition is satisfiable.
     * @param phi the proposition in conjunctive normal form
     * @return true if proposition is satisfiable, false otherwise
     */
    public boolean isSatisfiable() {
        numRecursiveCalls = 0;  //please leave this line
        
        Set<Variable> variables = new HashSet<>();
        variables.addAll(this.getAllVariables());
        Model model = new Model(variables);
        return isSatHelper(model);
    }

    /**
     * A recursive method that checks the satisfiability of the proposition
     * @param takes Model m as its parameter
     * @return true if model is satisfactory, false otherwise
     *
     * It was not in the original pseudocode professor Lyboult shared with us
     * But I decided to allow method to stop early as soon as it finds that
     * the statement is satisfactory. It will allow us to avoid iterations that
     * we don't really need.
     */

    private boolean isSatHelper(Model m) {
        numRecursiveCalls++;
        if(m.full())
            return checkingProposition(m);

        Variable variable = pullUnassigned(m);
        m.assign(variable, true);
        if(checkingProposition(m))
            return true;

        if(isSatHelper(m))
            return true;
        m.unassign(variable);
        m.assign(variable, false);
        if(checkingProposition(m))
            return true;

        if(isSatHelper(m))
            return false;
        m.unassign(variable);

        return false;
    }

    /**
     * Helper function: pulls unassigned vars.
     * @param m — model
     * @return unassigned variable or null if there are no unassigned variables
     */

    private Variable pullUnassigned(Model m){
        for(Variable variable: getAllVariables())
            if(m.isUnassigned(variable))
                return variable;
        return null;
    }


    /**
     * Helper function
     * @param cls — clause
     * @param m — model
     * @return true if clause has at least 1 true value, false otherwise
     */

    private boolean checkingClause(Clause cls, Model m){
        Set<Variable> variables = cls.getAllVariables();
        boolean truthValue = false;

        for(Variable variable: variables){
            boolean negated = cls.getNegatedVariables().contains(variable);
            if(m.isAssigned(variable)){
                if(m.getTruthValue(variable))
                    truthValue = !negated ? true: false;
                else
                    truthValue = negated ? true: false;
            }
            if(truthValue)
                return true;
        }
        return truthValue;
    }

    /**
     * Helper function
     * @param m — model
     * @return returns true if the entire proposition is true;
     * If at least one of the clauses if false, it'll return false;
     */

    private boolean checkingProposition(Model m){
        for(Clause cls: clauses)
            if(!checkingClause(cls, m))
                return false;
        return true;
    }
    
    /**
     * Factory method for CNFProposition objects.  See its usage in the main method.
     * Builds a CNFProposition by parsing the given string.  Expects proposition of the form "(p | ~q) & (p) & (~q | r)"  In other
     * words, clauses combined with & and surrounded by parenthesis.  Literals within clause are combined with | and
     * negated literals preceded by ~.
     * @param cnfStr
     * @return
     */
    public static CNFProposition fromString(String cnfStr) {
        String[] clauseStrs = cnfStr.split("\\s*&\\s*");
        List<Clause> clauses = new LinkedList<>();
        for (String clauseStr : clauseStrs) {
            if (!(clauseStr.startsWith("(") & clauseStr.endsWith(")"))) {
                throw new RuntimeException("invalid clause: expected open and close parens: '" + clauseStr + "'");
            }
            clauseStr = clauseStr.substring(1, clauseStr.length() - 1);
            if (clauseStr.contains("(") || clauseStr.contains(")")) {
                throw new RuntimeException("Invalid clause: '" + clauseStr + "'.  Clauses should not contain " +
                                           "parentheses");
            }
            String[] literalStrs = clauseStr.split("\\s*\\|\\s*");
            List<Literal> literals = new LinkedList<>();
            for (String literalStr : literalStrs) {
                boolean isNegated;
                isNegated = literalStr.startsWith("~");
                String variableStr = literalStr;
                if (isNegated) {
                    variableStr = literalStr.substring(1);
                }
                Variable v = new Variable(variableStr);
                Literal literal = new Literal(v, !isNegated);
                literals.add(literal);
            }
            Clause clause = new Clause(literals);
            clauses.add(clause);
        }
        return new CNFProposition(clauses);
    }
    
    
    public String toString() {
        return clauses.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(" & "));
    }
    
}
