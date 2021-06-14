/**
 * A proposition of the form NOT(alpha) where alpha is proposition.
 *
 * Colgate University COSC 290L
 * Updated 2021
*/

public class Neg extends Proposition {

    private Proposition alpha;

    public Neg(Proposition p) {
        this.alpha = p;
    }

    public String getOperator() {
       return Proposition.NEG_OPERATOR;
    }

    public Proposition getLeft() {
        return this.alpha;
    }
    
    
    public String toString() {
        String str = alpha.isVar() ? "~"+alpha:"~("+alpha+")";
        return str;
         }

    
    public Proposition copy() {
        return new Neg(alpha.copy());
    }
}
