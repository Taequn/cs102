/**
 * A proposition of the form NOT(leftChild) where leftChild is proposition.
 *
 * Colgate University COSC 290L
 * Updated 2021
*/

public class Neg extends Proposition {

    private Proposition leftChild;

    public Neg(Proposition p) {
        this.leftChild = p;
    }

    public String getOperator() {
       return super.NEG_OPERATOR;
    }

    public Proposition getLeft() {
        // this overrides a method in parent; see javadocs of Proposition
        return leftChild;
    }
    
    
    public String toString() {
        // when phi is the negation of a variable, should return: "~x"
        // when phi anything else (including another negatioN): "~(p & q)"  -- include parenthesis
        if (leftChild.isVar())
          return super.NEG_OPERATOR + leftChild;
        return super.NEG_OPERATOR + "(" + leftChild + ")";
    }

    
    public Proposition copy() {
        return new Neg(leftChild.copy());
    }
}
