/**
 * An atomic proposition.
 *
 * Colgate University COSC 290L
 * Updated 2021
*/

public class PropVariable extends Proposition {
    private String symbol;

    public PropVariable(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }

    public boolean isVar() {
        return true;
    }

    public boolean equals(Object other) {
        return (other instanceof PropVariable) && symbol.equals(((PropVariable)other).symbol);
    }

    public int hashCode() {
        return symbol.hashCode();
    }

    public Proposition copy() {
        return new PropVariable(symbol);
    }
}
