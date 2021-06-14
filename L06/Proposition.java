/**
 * An abstract class representing a logical proposition.
 *
 * Colgate University COSC 290L
 * Updated 2021
*/


public abstract class Proposition {
  
    //Strings represnting the various operators
    public static final String NEG_OPERATOR = "~";
    public static final String CONJ_OPERATOR = "&";
    public static final String DISJ_OPERATOR = "|";
    public static final String IMPL_OPERATOR = "->";
    
    //collection of all possible operators
    protected static final String[] ALL_OPERATORS = {NEG_OPERATOR, CONJ_OPERATOR, DISJ_OPERATOR, IMPL_OPERATOR};

    
    /**
     * Returns a string representation of a proposition.  The string representation should include parentheses to
     * indicate the operator precedence.  Example:
     * (p & q) | r
     * As a challenge problem, implement this method such that parens are dropped if the operators are of the same
     * type. Example:
     * (p & q & r) | (q | ~r)
     * @return a string representation of proposition
     */
    public abstract String toString();

    /**
     * @return a deep copy of this proposition
     */
    public abstract Proposition copy();

    // --- HELPER METHODS FOR CHECKING THE STRUCTURE OF A COMPLEX PROPOSITION ---

    /**
     * @return true is the proposition is of the form p for some variable p.
     */
    public boolean isVar() {
        return false;
    }

    /**
     * @return true is the proposition is of the form ~phi for some proposition phi.
     */
    public boolean isNeg() {
      return hasConnective(NEG_OPERATOR);
    }

    /**
     * @return true is the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is
     * some logical connective.
     */
    public boolean isBinOp() {
        return isDisj() || isConj() || isImpl();
    }

    /**
     * @return true is the proposition is of the form phi1 | phi2.
     */
    public boolean isDisj() {
        return hasConnective(DISJ_OPERATOR);
    }

    /**
     * @return true is the proposition is of the form phi1 & phi2.
     */
    public boolean isConj() {
        return hasConnective(CONJ_OPERATOR);
    }

    /**
     * @return true is the proposition is of the form phi1 => phi2.
     */
    public boolean isImpl() {
        return hasConnective(IMPL_OPERATOR);
    }

    /**
     * If the proposition is binary -- i.e., it is of the form  phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective, the respective operator is returned (see finals above).
     * 
     * Example: if proposition is p & q, it returns &.
     * 
     * Same logic applies if the propsition is unary -- i.e., OPERATOR phi1
     *
     * Example: if proposition is ~p, it returns ~.
     * 
     * If the proposition ahs no operator, null is returned
     *
     * @return the logical connective associated with this sentence if it has one, otherwise null
     */
    public String getOperator() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 -- then phi1 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns (p | r).
     *
     * If the proposition is unary -- i.e., it is of the form OPERATOR phi1 where OPERATOR is some logical
     * connective -- then phi1 is returned.
     *
     * Example: if proposition is ~p, it returns p.
     *
     * @return the left argument if it is binary, the argument if it is unary, otherwise null
     */
    public Proposition getLeft() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi2 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns q.
     *
     * @return the right argument if this sentence is a binary proposition, otherwise null
     */
    public Proposition getRight() {
        return null;
    }

    /**
     * Checks whether the sentence has connective c at its root.
     *
     * Example: if proposition is (p | r) & q, then hasConnective(&) is true, but hasConnective(|) is false.
     *
     * @param c the connective type
     * @return true if the root of the sentence is equal to c
     */
    public boolean hasConnective(String op) {
        return getOperator() != null && getOperator().equals(op);
    }

}
