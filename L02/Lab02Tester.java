import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Tester file for Lab 02 - Efficiently Enumerating Subsets, Part 1
 *
 * Colgate University COSC 290L
 * Updated 2021
*/
public class Lab02Tester {



    public static void main(String[] args) {
        Set<String> s1 = new HashSet<String>();
        Collections.addAll(s1, "a", "b");
        Set<String> s2 = new HashSet<String>();
        Collections.addAll(s2, "a", "b", "c");
        Set<String> s3 = new HashSet<String>();
        Collections.addAll(s3, "a", "c", "e", "g");


        /**
         * TEST CASES FOR REMOVE ONE
         */
        /*
        Set<String> s4Test = new HashSet<>();
        //3 elements
        //Collections.addAll(s4Test, "a", "b", "c");
        //1 element
        //Collections.addAll(s4Test, "a");
        //empty
        //Collections.addAll(s4Test);

        SetsPractice.removeOne(s4Test);
        System.out.println(s4Test);
         */



/*
        Set<Set<String>> sets = new HashSet<Set<String>>();
        Collections.addAll(sets, s1, s2, s3);
        System.out.println("sets = " + sets);

        //Remove 'a' from all sets
        Set<Set<String>> setsAfterRemove = SetsPractice.removeElementFromAll(sets, "a");
        System.out.println("setsAfterRemove = " + setsAfterRemove);

 */

    }

}
