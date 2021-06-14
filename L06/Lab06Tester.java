/**
 * Tester file for Lab 06 - Logical Normal Forms Part 1
 *
 * Colgate University COSC 290L
 * Updated 2021
 */
public class Lab06Tester {

    public static void main(String[] args) {
        //create three sample test propositions
        PropVariable p = new PropVariable("p");
        PropVariable q = new PropVariable("q");
        PropVariable r = new PropVariable("r");

        Proposition prop1 = Build.makeDisj(r, Build.makeNeg(Build.makeConj(q, r)));
        //r | ~(q & r)
        //r | (~r | ~q)
        //(r | ~r) | ~q
        //~q

        Proposition prop2 = Build.makeNeg(Build.makeNeg(p));
        //~~p
        //double negation
        //p

        Proposition prop3 = Build.makeConj(Build.makeNeg(r), Build.makeNeg(Build.makeConj(p, q)));
        //~r & ~(p & q)
        //~r & (~p | ~q)
        //(~r & ~p) | (~r & ~q)


        Proposition prop4 = Build.makeImpl(Build.makeImpl(p, Build.makeDisj(new Neg(q), r)), Build.makeImpl(q, Build.makeDisj(p, new Neg(r))));
        // (p -> (~q | r) -> (q -> (p | ~r)
        // p * ~q

        
        System.out.println("Proposition 1: " + prop1);
        System.out.println("Proposition 2: " + prop2);
        System.out.println("Proposition 3: " + prop3);
        System.out.println("Proposition 4: " + prop4);

        //test simplify
        /*
        Proposition simprop1 = Build.simplify(prop1);
        Proposition simprop2 = Build.simplify(prop2);
        Proposition simprop3 = Build.simplify(prop3);
        Proposition simprop4 = Build.simplify(prop4);
        System.out.println("\nProp 1 simplified = " + simprop1);
        System.out.println("\nProp 1 simplified = " + simprop2);
        System.out.println("\nProp 1 simplified = " + simprop3);
        System.out.println("\nProp 1 simplified = " + simprop4);

        //test NNF
        Proposition NNFprop1 = Build.toNNF(simprop1);
        Proposition NNFprop2 = Build.toNNF(simprop2);
        Proposition NNFprop3 = Build.toNNF(simprop3);
        Proposition NNFprop4 = Build.toNNF(simprop4);
        System.out.println("\nProp 1 to NNF = " + NNFprop1);
        System.out.println("\nProp 2 to NNF = " + NNFprop2);
        System.out.println("\nProp 3 to NNF = " + NNFprop3);
        System.out.println("\nProp 4 to NNF = " + NNFprop4);
         */
    }
    
    
}
