import java.util.*;
/**
 * Tester file for Lab 07 - Logical Normal Forms Part 2
 *
 * Colgate University COSC 290L Updated 2021
 *
 *
 *  Sorry for the delay with submission!
 * But this time I also added null exceptions, like you
 * suggested for my previous lab! :)
 *
 */
public class Lab07Tester {

  public static void main(String[] args) {
    // create two sample test propositions
    PropVariable p = new PropVariable("p");
    PropVariable q = new PropVariable("q");
    PropVariable r = new PropVariable("r");
    PropVariable s = new PropVariable("s");
    PropVariable t = new PropVariable("t");

    Proposition prop1 = Build.makeDisj(t, Build.makeConj(p, q));
    Proposition prop2 = Build.makeDisj(Build.makeConj(p, q), t);
    Proposition prop3 = Build.makeDisj(Build.makeConj(p, q), Build.makeNeg(t));
    Proposition prop4 = Build.makeDisj(Build.makeConj(p, q), Build.makeConj(r, s));
    Proposition prop5 = Build.makeNeg(Build.makeImpl(p, Build.makeDisj(r, Build.makeNeg(s))));

    System.out.println("****   From NNF to CNF Tests ****");
    System.out.println(prop1 + "  -->  " + Build.fromNNFtoCNF(prop1));
    System.out.println(prop2 + "  -->  " + Build.fromNNFtoCNF(prop2));
    System.out.println(prop3 + "  -->  " + Build.fromNNFtoCNF(prop3));
    System.out.println(prop4 + "  -->  " + Build.fromNNFtoCNF(prop4));
    System.out.println(prop5 + "  -->  " + Build.toCNF(prop5));

    System.out.println("****   To CNF Tests ****");

    Set <Proposition> propSet = new HashSet<>();

    propSet.add(Build.makeDisj(Build.makeConj(p, q), Build.makeConj(q, r)));
    propSet.add(Build.makeDisj(prop4, Build.makeConj(s, t)));
    propSet.add(Build.makeConj(Build.makeNeg(Build.makeConj(p, q)), Build.makeNeg(r)));
    propSet.add(Build.makeNeg(Build.makeNeg(p)));
    propSet.add(Build.makeDisj(r, Build.makeNeg(Build.makeConj(q, r))));
    propSet.add(Build.makeDisj(Build.makeConj(p, q), Build.makeDisj(q, r)));

    for(Proposition prop: propSet)
      System.out.println(prop + " -- TRANSFORMS INTO --> " + Build.toCNF(prop));

    Proposition nullProp = Build.makeDisj(Build.makeConj(null, null), Build.makeDisj(null, null));
    System.out.println("****    TESTING FOR NULL  ****");
    System.out.println(nullProp + " -- TRANSFORMS INTO --> " + Build.toCNF(nullProp));

  }
}
