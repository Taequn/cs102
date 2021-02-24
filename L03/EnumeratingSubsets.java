import java.util.*;

/**
 * Contains two standalone functions for working with Sets... warmup for next week
 * where we will leverage recursion to perform some PowerSet operations
 *
 * Colgate University COSC 290L
 * Updated 2021
*/
public class EnumeratingSubsets {
    
    /**
     * Given a set s, containing elements of type String, return the powerset of s
     * Must not modify the original argument Set s
     * @param s a set of elements
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> powerset(Set<String> s) {
        Set<Set<String>> result = new HashSet<>();
        //Adding an empty set to the set of sets if it's empty
        if (s.isEmpty()){
            result.add(s);
            return result;
        }
        Set<String> workingSet = new HashSet<>(s);

        //extracting an element+calling for recursion
        String removedElement = removeOne(workingSet);
        Set<Set<String>> subsetsWithoutElement = powerset(workingSet);

        for(Set<String> subsetWithoutElement: subsetsWithoutElement){
            Set<String> subsetWithElement = new HashSet<>(subsetWithoutElement);
            subsetWithElement.add(removedElement);
            result.add(subsetWithElement);
        }

        Set<Set<String>> resultPowerSet = new HashSet<>();

        resultPowerSet.addAll(subsetsWithoutElement);
        resultPowerSet.addAll(result);


        return resultPowerSet;
    }
    
    
    
    
    /**
     * Given a set s, containing elements of type String, return set of all subsets of s such that each
     * subset is of cardinality c
     * If c > cardinality of s, this function throws a RunTimeException
     * Must not modify original argument Set s
     * @param s a set of elements
     * @param c the desired cardinality (number of elements) of each subset
     * @return all subsets of size c from set s
     */

    public static Set<Set<String>> allSubsetsOfCardinality(Set<String> s, int c) {
        if (c<0)
            throw new IllegalArgumentException("Cardinality is less than 0");
        else if (s.size()<c)
            throw new IllegalArgumentException("Cardinality is more than the size of the given set");
        Set<Set<String>> result = new HashSet<>();
        if(s.size() == c){
            result.add(s);
            return result;
        }
        if(c==0){
            result.add(new HashSet<>());
            return result;
        }

        Set<String> workingSet = new HashSet<>(s);

        for(String x: s){
            workingSet.remove(x);
            Set<Set<String>> smaller = allSubsetsOfCardinality(workingSet, c-1);
            for(Set<String> small: smaller){
                small.add(x);
                result.add(small);
            }
        }

        return result;

    }
    
    
    
    /**
     * Given a set s, containing elements of type String, removes and returns 
     * one String from the set
     * @param s a set of elements
     * @return the String removed from set s
     */    
    public static String removeOne(Set<String> s){
        if (s.size() == 0) 
            throw new NoSuchElementException("Cannot remove from empty set!");
        String removed = s.iterator().next();
        s.remove(removed);
        return removed;        
    }    
    
    

    
    

    
}
