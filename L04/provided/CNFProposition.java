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
        // to implement this method, loop through the clauses, get their variables,
        // and add those variables to a set that you return
        Set<Variable> variables = new HashSet<Variable>();
        for (Clause c : clauses)
            variables.addAll(c.getAllVariables());
        return variables;
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
    
    //Recursive helper function for isSatisfiable
    //Implement me last!!
    private boolean isSatHelper(Model m) {
        numRecursiveCalls++;   // please leave this line
        
        Variable someUnassigned = null;
        Set<Variable> allVariables = this.getAllVariables();
        for (Variable v : allVariables){
            if (m.isUnassigned(v)){
                someUnassigned = v;
                break;
            }
        }
        if (someUnassigned == null)
            return solveProposition(m);
        m.assign(someUnassigned, true);
        if (isSatHelper(m))
            return true;
        m.unassign(someUnassigned);
        m.assign(someUnassigned, false);
        if (isSatHelper(m))
            return true;
        m.unassign(someUnassigned);
        return false;
    }
    
    
    private boolean solveProposition(Model m){
        for (Clause c : clauses){
            if (!solveClause(c, m))
                return false;
        }
        return true;
    }
    
    
    private boolean solveClause(Clause c, Model m){
        Set<Variable> posVars = c.getNonNegatedVariables();
        for (Variable v : posVars){
            if (m.getTruthValue(v))
                return true;
        }
        Set<Variable> negVars = c.getNegatedVariables();
        for (Variable v : negVars){
            if (!m.getTruthValue(v))
                return true;
        }        
        
        return false;
        
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
