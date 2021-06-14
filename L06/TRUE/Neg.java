/**
 * A proposition of the form NOT(alpha) where alpha is proposition.
 *
 * Colgate University COSC 290L Updated 2021
 *
 *    ___    _                 _
 *   / __|  | |_       _ _    (_)     ___      o O O
 *  | (__   | ' \     | '_|   | |    (_-<     o
 *   \___|  |_||_|   _|_|_   _|_|_   /__/_   TS__[O]
 * _|"""""|_|"""""|_|"""""|_|"""""|_|"""""| {======|
 * "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'./o--000'
 *  __ __ __ __ _     ___      o O O
 *  \ V  V // _` |   (_-<     o
 *   \_/\_/ \__,_|   /__/_   TS__[O]
 * _|"""""|_|"""""|_|"""""| {======|
 * "`=0-0-'"`-0-0-'"`-0-0-'./o--000'
 *  | |_      ___      _ _    ___
 *  | ' \    / -_)    | '_|  / -_)
 *  |_||_|   \___|   _|_|_   \___|
 * _|"""""|_|"""""|_|"""""|_|"""""|
 * "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
 *
 *
 */

public class Neg extends Proposition {

    private Proposition alpha;

    public Neg(Proposition p) {
        this.alpha = p;
    }

    public String getOperator() {
        return Proposition.NEG_OPERATOR;
    }

    public Proposition getLeft() { return this.alpha; }

    /**
     * I know that this code might look better in several lines,
     * but all the other functions were oneliners, and I simply
     * couldn't resist the urge to turn this one into a oneliner as well
     *
     * I deeply apologize for it!
     */
    public String toString() { return new String(alpha.isVar() ? "~"+alpha:"~("+alpha+")"); }

    public Proposition copy() { return new Neg(alpha.copy()); }
}
