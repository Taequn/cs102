import java.util.*;
import java.util.stream.Collectors;

/**
 * A Clause object represents a clause in a CNFProposition proposition.  Thus, it represents a disjunction of literals.
 *
 * Colgate University COSC 290L
 * Updated 2021
 */

public class Clause {

    //Collection of the unique literals that make up the Clause
    private Set<Literal> literals = new HashSet<>();

    public Clause(Collection<Literal> literals) {
        if (literals.size() == 0) {
            throw new RuntimeException("Must contain at least one literal!");
        }
        this.literals.addAll(literals);
    }


    //Retrieves all variables from the clause
    public Set<Variable> getAllVariables() {
        return getVariables(null);
    }


    //Retrieves only the negative variables from the clause
    public Set<Variable> getNonNegatedVariables() {
        return getVariables(true);
    }


    //Retrieves only the non-negated variables from the clause
    public Set<Variable> getNegatedVariables() {
        return getVariables(false);
    }

    //helper function for the above get...Variables methods
    private Set<Variable> getVariables(Boolean getPositive) {
        // if getPositive is null, it returns all variables (positive and negative)
        Set<Variable> toReturn = new HashSet<Variable>();
        for (Literal l : literals){
            if (getPositive == null || getPositive == l.isPositive())
                toReturn.add(l.getVariable());
        }
        return toReturn;
    }

    // ---- implementation of relationToString to make debugging easier

    /**
     * Produce the clause in string form.  Uses the symbol "|" to indicate the OR of literals, as in p | q, for p OR q.
     * @return the clause in string form
     */
    public String toString() {
        // if you get a compiler error here, you are not running Java 8!
        return "(" +
            literals.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(" | "))
            + ")";
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.


    public boolean equals(Object obj) {
        if (!(obj instanceof Clause)) {
            return false;
        }
        Clause other = (Clause) obj;
        return this.literals.equals(other.literals);
    }


    public int hashCode() {
        int hash = 0;
        for (Literal literal : literals) {
            hash += 31*hash + literal.hashCode();
        }
        return hash;
    }
}
