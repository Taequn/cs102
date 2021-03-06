import java.util.Arrays;

/**
 * A proposition of the form alpha OP beta where alpha and beta are propositions and OP is a logical connective (AND,
 * OR, IMPLIES, etc).
 *
 * Colgate University COSC 290L
 * Updated 2021
*/
public class BinOp extends Proposition {

    private String op;
    private Proposition alpha;
    private Proposition beta;


    public BinOp(String op, Proposition p, Proposition q) {
        if (Arrays.asList(Proposition.ALL_OPERATORS).indexOf(op) < 0 || op.equals(Proposition.NEG_OPERATOR))
          throw new IllegalArgumentException("'" + op + "' is not a valid operator!");
        this.op = op;
        this.alpha = p;
        this.beta = q;
    }

    public String getOperator() {
        return op;
    }

    public Proposition getLeft() {
        return alpha;
    }

    public Proposition getRight() {
        return beta;
    }

    public String toString() {
        return getSubstring(alpha) + " " + op.toString() + " " + getSubstring(beta);
    }

    
    // be clever about parentheses: ((p & q) & r) will be written simply as (p & q & r)
    private String getSubstring(Proposition q) {
        String pStr = "";
        if (q.isBinOp()) {
            if(q.isImpl())
                pStr = "(" + q.toString() + ")";
            else if (((BinOp)q).op.equals(this.op)) 
                pStr = q.toString();
            else 
                pStr = "(" + q.toString() + ")";
        } 
        else 
            pStr = q.toString();
        return pStr;
    }
    

    public Proposition copy() {
        return new BinOp(op, alpha.copy(), beta.copy());
    }
}
