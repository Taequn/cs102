/**
 * Static methods to help write complex propositions, as well as convert
 * propsition between forms
 *
 * Colgate University COSC 290L Updated 2021
 *
 *
 *   _  _              _       _               _
 *  | || |    ___     | |     | |     ___     | |
 *  | __ |   / -_)    | |     | |    / _ \    |_|
 *  |_||_|   \___|   _|_|_   _|_|_   \___/   _(_)_
 * _|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_| """ |
 * "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
 *
 */

public class Build {

    /**
     * Returns proposition that is negation of p
     * 
     * @param p
     * @return proposition that is ~p
     */
    public static Proposition makeNeg(Proposition p) {
        return new Neg(p);
    }

    /**
     * Returns proposition that is conjunction of p and q
     * 
     * @param p
     * @param q
     * @return proposition that is p & q
     */
    public static Proposition makeConj(Proposition p, Proposition q) {
        return new BinOp(Proposition.CONJ_OPERATOR, p, q);
    }

    /**
     * Returns proposition that is disjunction of p and q
     * 
     * @param p
     * @param q
     * @return proposition that is p | q
     */
    public static Proposition makeDisj(Proposition p, Proposition q) {
        return new BinOp(Proposition.DISJ_OPERATOR, p, q);
    }

    /**
     * Returns proposition that is p implies q
     * 
     * @param p
     * @param q
     * @return proposition that is p => q
     */
    public static Proposition makeImpl(Proposition p, Proposition q) {
        // look at the other examples above for tips on how to write this
        return new BinOp(Proposition.IMPL_OPERATOR, p, q);
    }

    /**
     * Constructs a proposition psi such that (1) psi contains only the logical
     * connectives in {&, ~} and (2) psi is logically equivalent to phi.
     *
     * Proofs that I'm going to use to implement simplify() method:
     *
     * DISJUNCTION:
     * De Morgan's law:
     * ~(p | q) ≡ (~p & ~q)
     * Let's set p=~A and q=~B.
     * Therefore, ~p would be A and ~q would be B.
     *
     * This would get us the following:
     * (A | B) ≡ (~p | ~q) ≡ ~(p & q) ≡ ~(~A & ~B)
     *
     * IMPLICATION:
     * Definition of implication:
     * p->q ≡ ~p | q
     * De Morgan's law:
     * ~(p | q) ≡ (~p & ~q)
     *
     * Let's set A=~p and B=q (with ~A=p and ~B=~q)
     * p->q ≡ (~p | q) ≡ (A | B)
     * As we have showed above this expression turns into:
     * (A | B) ≡ ~(~A & ~B) ≡ ~(p & ~q)
     *
     *
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically
     *         equivalent to phi
     */
    public static Proposition simplify(Proposition phi) {
        if (phi.isVar())
            return phi;

        else {
            Proposition leftOne = simplify(phi.getLeft());
            Proposition rightOne = phi.isNeg() ? null : simplify(phi.getRight());

            if (phi.isNeg())
                return makeNeg(leftOne);

            if (phi.isConj())
                return makeConj(leftOne, rightOne);

            if (phi.isDisj())
                return makeNeg(makeConj(makeNeg(leftOne), makeNeg(rightOne)));

            if (phi.isImpl())
                return makeNeg(makeConj(leftOne, makeNeg(rightOne)));

            return phi;
        }
    }

    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form and
     * (2) psi is logically equivalent to phi. Expects a proposition that has only &
     * and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied
     * only to atomic propositions (i.e. variables) and not to more complex
     * expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in NNF and logically equivalent to phi
     * @throws IllegalArgumentException if phi contains a connective that is not in
     *                                  the sets {&, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
        if (phi.isVar()) {
            return phi;
        }
        if (phi.isNeg()) {
            Proposition inner = phi.getLeft();
            if (inner.isVar()) {
                // ~p
                return makeNeg(inner);
            }
            if (inner.isNeg()) {
                // ~(~p) ≡ p
                return toNNF(inner.getLeft());
            }
            if (inner.isConj()) {
                // ~(p & q) ≡ (~p | ~q)
                return makeDisj(toNNF(makeNeg(inner.getLeft())), toNNF(makeNeg(inner.getRight())));
            }
        }
        if (phi.isConj()) {
            return makeConj(toNNF(phi.getLeft()), toNNF(phi.getRight()));
        } else {
            // Illegal Argument: if there is a sign other than & or ~
            throw new IllegalArgumentException("phi contains a connective that is not & or ~");
        }
    }
}
