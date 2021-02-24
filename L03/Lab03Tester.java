import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Tester file for Lab 02 - Efficiently Enumerating Subsets, Part 1
 *
 * Colgate University COSC 290L
 * Updated 2021
*/
public class Lab03Tester {

    
    public static void main(String[] args) {
        // a little demonstration of the methods
        Set<String> s1 = new HashSet<String>();

        
        //test powerset
        //Collections.addAll(s1, "a", "b", "c", "d");
        //Collections.addAll(s1);
        //Collections.addAll(s1, "a");
        //Collections.addAll(s1, "a", null);
        /**
         * I was not sure if we're allowed or not allowed nulls in the set, so
         * I decided to leave it be because Lab description doesn't mention
         * anything about it!
         */

        //System.out.println("s1 = " + s1);
        //System.out.println("powerset of s1 = " + EnumeratingSubsets.powerset(s1));
        
        //test all subsets of cardinality

        Set<String> s2 = new HashSet<String>(s1);

        //Negative cardinality (illegal argument)
        //Collections.addAll(s2, "a", "b", "c", "d");
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, -5));

        //Cardinality zero
        //Collections.addAll(s2, "a", "b", "c", "d");
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 0));

        //Empty set (illegal argument)
        //Collections.addAll(s2);
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 3));

        //Empty set (legal argument)
        //Collections.addAll(s2);
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 0));

        //Cardinality>set.size()
        //Collections.addAll(s2, "a", "b", "c", "d");
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 10));

        //Cardinality==set.size()
        //Collections.addAll(s2, "a", "b", "c", "d", null);
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 5));

        //testing for null
        //Collections.addAll(s2, "a", "b", "c", null);
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 2));

        //1 element in the set
        //Collections.addAll(s2, "a");
        //System.out.println(EnumeratingSubsets.allSubsetsOfCardinality(s2, 1));
    }

}
