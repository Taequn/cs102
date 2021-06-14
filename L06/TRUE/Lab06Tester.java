import java.util.*;
/**
 * Tester file for Lab 06 - Logical Normal Forms Part 1
 *
 * Colgate University COSC 290L Updated 2021
 *
 *
 *   _  _
 *  | || |   __ _    __ __    ___
 *  | __ |  / _` |   \ V /   / -_)
 *  |_||_|  \__,_|   _\_/_   \___|
 * _|"""""|_|"""""|_|"""""|_|"""""|
 * "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
 *     o O O  __ _      o O O
 *    o      / _` |    o
 *   TS__[O] \__,_|   TS__[O]
 *  {======|_|"""""| {======|
 * ./o--000'"`-0-0-'./o--000'
 *    __ _                      _
 *   / _` |   ___     ___    __| |
 *   \__, |  / _ \   / _ \  / _` |
 *   |___/   \___/   \___/  \__,_|
 * _|"""""|_|"""""|_|"""""|_|"""""|
 * "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
 *      _             _  _
 *   __| |   __ _    | || |
 *  / _` |  / _` |    \_, |
 *  \__,_|  \__,_|   _|__/
 * _|"""""|_|"""""|_| """"|
 * "`-0-0-'"`-0-0-'"`-0-0-'
 *
 *
 */
public class Lab06Tester {

    public static void main(String[] args) {
        // create three sample test propositions
        PropVariable p = new PropVariable("p");
        PropVariable q = new PropVariable("q");
        PropVariable r = new PropVariable("r");

        /**
         * Basic testing that was in the lab by default
         */
        Proposition prop1 = Build.makeDisj(r, Build.makeNeg(Build.makeConj(q, r)));
        Proposition prop2 = Build.makeNeg(Build.makeNeg(p));
        Proposition prop3 = Build.makeConj(Build.makeNeg(r), Build.makeNeg(Build.makeConj(p, q)));
        Proposition prop4 = Build.makeImpl(Build.makeImpl(p, Build.makeDisj(new Neg(q), r)),
                Build.makeImpl(q, Build.makeDisj(p, new Neg(r))));

        /**
         * Test for all possible scenarios, including the default test cases
         */
        Set <Proposition> colOfProps = new HashSet<>();
        colOfProps.add(prop1);
        colOfProps.add(prop2);
        colOfProps.add(prop3);
        colOfProps.add(prop4);
        // Disjoint prop
        colOfProps.add(Build.makeDisj(p, q));
        // Conjuction prop
        colOfProps.add(Build.makeConj(p, q));
        // Negation prop
        colOfProps.add(Build.makeNeg(p));

        // Implication series
        colOfProps.add(Build.makeImpl(p, q));
        // Cojunction of implication
        colOfProps.add(Build.makeConj(Build.makeImpl(p, q), Build.makeImpl(q, r)));
        // Negation of implication
        colOfProps.add(Build.makeNeg(Build.makeImpl(p, q)));
        // Disjuction of implication
        colOfProps.add(Build.makeDisj(Build.makeImpl(p, q), Build.makeImpl(q, r)));

        for(Proposition prop: colOfProps){
            System.out.println("*************");
            System.out.println("INITIAL PROPOSITION: " + prop);
            System.out.println("SIMPLIFIED PROPOSITION:" + Build.simplify(prop));
            System.out.println("*************");
        }



        // test NNF
        Set<Proposition> colOfNNF = new HashSet<>();

        for(Proposition prop: colOfNNF){
            System.out.println("*************");
            System.out.println("INITIAL PROPOSITION: " + prop);
            System.out.println("NNF:" + Build.toNNF(prop));
            System.out.println("*************");
        }
        /**
         * Default examples + my own test examples
         */
        colOfNNF.add(prop1);
        colOfNNF.add(prop2);
        colOfNNF.add(prop3);
        colOfNNF.add(prop4);
        colOfNNF.add(Build.makeNeg(p));
        colOfNNF.add(Build.makeNeg(Build.makeNeg(p)));
        colOfNNF.add(Build.makeNeg(Build.makeNeg(Build.makeNeg(p))));
        colOfNNF.add(Build.makeNeg(Build.makeNeg(Build.makeNeg(Build.makeNeg(p)))));
        colOfNNF.add(Build.makeConj(Build.makeNeg(Build.makeConj(p, q)), Build.makeNeg(Build.makeConj(q, r))));
        colOfNNF.add(Build.makeNeg(Build.makeConj(p, q)));


        //Illegal arg
        Proposition prop5 = Build.makeImpl(p, q);
        System.out.println("*************");
        System.out.println("INITIAL PROPOSITION: " + prop5);
        System.out.println("TESTING FOR ILLEGAL ARGUMENT FOR TONNF FUNCTION");
        System.out.println("NNF:" + Build.toNNF(prop5));
        System.out.println("*************");

    }

}
