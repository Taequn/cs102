/**
 * Static methods to help write complex propositions, as well as convert
 * propsition between forms
 *
 * Colgate University COSC 290L Updated 2021
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
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically
     *         equivalent to phi
     */
    public static Proposition simplify(Proposition phi) {
        if (phi==null)
            throw new IllegalArgumentException("Proposition contains null!");

        if (phi instanceof PropVariable)
            return phi.copy();
        else if (phi instanceof Neg)
            return new Neg(simplify(phi.getLeft()));
        Proposition newLeft = simplify(phi.getLeft());
        Proposition newRight = simplify(phi.getRight());
        if (phi.isDisj()) {
            Proposition temp = Build.makeConj(Build.makeNeg(newLeft), Build.makeNeg(newRight));
            return new Neg(temp);
        } else if (phi.isImpl()) {
            Proposition temp = Build.makeConj(newLeft, Build.makeNeg(newRight));
            return new Neg(temp);
        }
        return Build.makeConj(newLeft, newRight);
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
        if (phi instanceof PropVariable)
            return phi.copy();
        else if (phi instanceof Neg) {
            Proposition negProp = phi.getLeft();
            if (negProp instanceof PropVariable)
                return new Neg(negProp);
            else if (negProp.isConj())
                return Build.makeDisj(toNNF(Build.makeNeg(negProp.getLeft())),
                        toNNF(Build.makeNeg(negProp.getRight())));
            else if (negProp instanceof Neg)
                return toNNF(negProp.getLeft());
        } else if (phi.isConj()) {
            Proposition newLeft = toNNF(phi.getLeft());
            Proposition newRight = toNNF(phi.getRight());
            return Build.makeConj(newLeft, newRight);
        }
        throw new IllegalArgumentException("Proposition contains invalid operators!: " + phi);
    }

    /**
     * Constructs a proposition psi such that (1) psi is in CNF and (2) psi is
     * logically equivalent to phi. Expects a proposition phi that is in NNF.
     *
     * @param phi a proposition in NNF to transform to CNF
     * @return psi, a proposition in CNF and logically equivalent to phi
     * @throws IllegalArgumentException if phi is not in NNF
     */
    public static Proposition fromNNFtoCNF(Proposition phi) {
        if (phi.isVar() || (phi.isNeg() && phi.getLeft().isVar()))
            return phi;
        else if (!phi.isBinOp() || phi.isImpl())
            throw new IllegalArgumentException("Proposition contains invalid operators!");
        Proposition alpha = fromNNFtoCNF(phi.getLeft());
        Proposition beta = fromNNFtoCNF(phi.getRight());
        if (phi.isConj())
            return Build.makeConj(alpha, beta);
        return fromPartialCNFtoCNF(Build.makeDisj(alpha, beta));
    }

    /**
     * Recursive helper for fromNNFtoCNF -- converts the argument tree to CNF
     * equivalent. Argument phi is partially in CNF (see below)
     *
     * Given how this functions is utilzed in fromNNFtoCNF, what assumptions can we
     * make about phi?:
     *
     * You can assume phi is in ___________ form. You can assume both of phi's child
     * subtrees are in ___________ form.
     *
     * In this method, we'll be using the principle of distributivity:
     * A | (B & C) == (A | B) & (A | C)
     *
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF and logically equivalent to phi
     */
    private static Proposition fromPartialCNFtoCNF(Proposition phi) {
        if (phi.isVar() || (phi.isNeg() && phi.getLeft().isVar())) {
            return phi;
        }
        if (phi.isNeg() && phi.getLeft().isNeg()) {
            return fromPartialCNFtoCNF(phi.getLeft().getLeft());
        }


        if (phi.isDisj()) {
            if (phi.getRight().isConj()) {
                Proposition A = phi.getLeft();
                Proposition B = phi.getRight().getLeft();
                Proposition C = phi.getRight().getRight();
                Proposition p = Build.makeDisj(fromPartialCNFtoCNF(A), fromPartialCNFtoCNF(B)); // A or B
                Proposition q = Build.makeDisj(fromPartialCNFtoCNF(A), fromPartialCNFtoCNF(C)); // A or C
                return Build.makeConj(fromPartialCNFtoCNF(p), fromPartialCNFtoCNF(q)); // (A or B) & (A or C)
            }

            if (phi.getLeft().isConj()) {
                Proposition A = phi.getLeft().getLeft();
                Proposition B = phi.getLeft().getRight();
                Proposition C = phi.getRight();
                Proposition p = Build.makeDisj(fromPartialCNFtoCNF(A), fromPartialCNFtoCNF(C)); // A or C
                Proposition q = Build.makeDisj(fromPartialCNFtoCNF(B), fromPartialCNFtoCNF(C)); // B or C
                return Build.makeConj(fromPartialCNFtoCNF(p), fromPartialCNFtoCNF(q)); // (A or C) & (B or C)
            }
        }
        return phi;
    }

    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and
     * (2) psi is logically equivalent to phi.
     * 
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Proposition toCNF(Proposition phi) { return fromNNFtoCNF(toNNF(simplify(phi))); }
}
