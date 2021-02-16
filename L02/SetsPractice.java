import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Contains two standalone functions for working with Sets... warmup for next week
 * where we will leverage recursion to perform some PowerSet operations
 *
 * Colgate University COSC 290L
 * Updated 2021
*/
public class SetsPractice {

  
  
    /**
     * Removes and returns one element from the argument Set
     * It doesn't matter which element is removed; any will suffice
     * @param set of Strings
     * @return the String removed
     * @throws java.util.NoSuchElementException if given an empty Set
     */  
  public static String removeOne(Set<String> s){
      if(!s.isEmpty()){
          Iterator<String> itr = s.iterator();
          String value = itr.next();
          itr.remove();
          return value;
      }
      else{
        throw new NoSuchElementException();
      }
  }
  
  
    /**
     * Given string x and a set of sets, {S1, S2, ..., Sk} return a new set of
     * sets such that it contains the same sets as the input, but with x removed.
     * Note: the original inputs should NOT be modified.
     * @param sets a set of sets
     * @param x element to remove
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> removeElementFromAll(Set<Set<String>> sets, String toRemove) {
        HashSet<Set<String>> newSets = new HashSet<>(sets);
        if(toRemove==null)
            return newSets;

        if(!sets.isEmpty()){
            Iterator<Set<String>> iter = newSets.iterator();
            while(iter.hasNext()){
                Iterator<String> itr = iter.next().iterator();
                while(itr.hasNext()){
                    if(itr.next().equals(toRemove)){
                        itr.remove();
                    }
                }
            }

        }
        return newSets;
    }



}
