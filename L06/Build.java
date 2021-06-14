/**
 * Static methods to help write complex propositions, as well as
 * convert propsition between forms
 *
 * Colgate University COSC 290L
 * Updated 2021
*/

public class Build {

    /**
     * Returns proposition that is negation of p
     * @param p
     * @return proposition that is ~p
     */
    public static Proposition makeNeg(Proposition p) {
        return new Neg(p);
    }

    /**
     * Returns proposition that is conjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p & q
     */
    public static Proposition makeConj(Proposition p, Proposition q) {
        return new BinOp(Proposition.CONJ_OPERATOR, p, q);
    }

    /**
     * Returns proposition that is disjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p | q
     */
    public static Proposition makeDisj(Proposition p, Proposition q) {
        return new BinOp(Proposition.DISJ_OPERATOR, p, q);
    }

    /**
     * Returns proposition that is p implies q
     * @param p
     * @param q
     * @return proposition that is p => q
     */
    public static Proposition makeImpl(Proposition p, Proposition q) {
        return new BinOp(Proposition.IMPL_OPERATOR, p, q);
    }



    /**
     * Constructs a proposition psi such that (1) psi contains only the logical connectives in {&, ~} and (2)
     * psi is logically equivalent to phi.
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically equivalent to phi
     */
    public static Proposition simplify(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form  and (2) psi is logically
     * equivalent to phi.  Expects a proposition that has only & and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied only to atomic propositions (i.e.
     * variables) and not to more complex expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in NNF and logically equivalent to phi
     * @throws IllegalArgumentException if phi contains a connective that is not in the sets {&, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }
}
