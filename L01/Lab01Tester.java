import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Tester file for Lab 01 - ArraySet
 *
 * Colgate University COSC 290L
 * Updated 2021
*/

public class Lab01Tester {

  
  public static void main(String[] args) {
    /**
    //INIT test
    */

    //String[] array1 = {"a", "b", "c"};
    //String[] array1 = {"a", null, "c"};
    //String[] array1 = {};
    //String[] array1 = {null};
    //String[] array1 = null;
    //String[] array1 = {"a"};

    //ArraySet AStest1 = new ArraySet(array1);
    //ArraySet AStest1 = new ArraySet();
    /*
    System.out.println(AStest1);
     */

    /**
    //CONTAINS test
     */
    //String[] array1 = {"a", "b", "c"};
    //ArraySet AStest1 = new ArraySet(array1);
    //System.out.println(AStest1.contains("a"));

    //String[] array1 = {"a", "b", "c"};
    //ArraySet AStest1 = new ArraySet(array1);
    //System.out.println(AStest1.contains("d"));

    //String[] array1 = {"a", null, "c"};
    //ArraySet AStest1 = new ArraySet(array1);
    //System.out.println(AStest1.contains(null));

    //ArraySet AStest1 = new ArraySet(array1);
    //System.out.println(AStest1.contains("a"));

    /**
    //INSERT test
     */

    /*
    String[] array1 = {"a", "b", "c"};
    ArraySet AStest1 = new ArraySet(array1);
    //repeated value
    AStest1.insert("a");
    System.out.println(AStest1);
    System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //resizing with extra capacity
    AStest1.insert("d");
    System.out.println(AStest1);
    System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //null entry
    AStest1.insert(null);
    System.out.println(AStest1);
    System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());
     */

    /**
     REMOVING tests
     */
    //String[] array1 = {"a", "b", "c", "d"};
    //ArraySet AStest1 = new ArraySet(array1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //removing from beginning
    //AStest1.remove("a");
    //System.out.println(AStest1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //removing from the end
    //AStest1.remove("d");
    //System.out.println(AStest1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //removing null
    //AStest1.remove(null);
    //System.out.println(AStest1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //resizing
    //AStest1.remove("d");
    //AStest1.remove("b");
    //System.out.println(AStest1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    //removing a value that's not in the set
    //AStest1.remove("z");
    //System.out.println(AStest1);
    //System.out.println(AStest1.cardinality()+"/"+AStest1.capacity());

    /**
    UNION tests
     */

    //Regular union
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"e", "f", "g"};

    //s1 empty union
    //String[] array1 = {};
    //String[] array2 = {"e", "f", "g"};

    //s2 empty union
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {};

    //s1 is null
    //String[] array1 = null;
    //String[] array2 = {"e", "f", "g"};

    //s2 is null
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = null;

    //both empty
    //String[] array1 = {};
    //String[] array2 = {};

    /*
    ArraySet AStest1 = new ArraySet(array1);
    ArraySet AStest2 = new ArraySet(array2);
    ArraySet unionTest = ArraySet.union(AStest1, AStest2);
    System.out.println(unionTest);
     */

    /**
    INTERSECTION tests
     */

    //no int
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"e", "f", "g"};

    //regular intersection
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"a", "b", "g"};

    //with nulls
    //String[] array1 = {"a", "b", "c", null};
    //String[] array2 = {"a", "b", "g", null};

    //empty set
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {};

    /*
    ArraySet AStest1 = new ArraySet(array1);
    ArraySet AStest2 = new ArraySet(array2);
    ArraySet intTest = ArraySet.intersection(AStest1, AStest2);
    System.out.println(intTest);
     */

    /**
    DIFFERENCE tests
     */

    //no repition
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"e", "f", "g"};

    //1 repition
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"e", "f", "d"};

    //same sets
    //String[] array1 = {"a", "b", "c", "d"};
    //String[] array2 = {"a", "b", "c", "d"};

    //s1 is empty
    //String[] array1 = {};
    //String[] array2 = {"a", "b", "c", "d"};

    /*
    ArraySet AStest1 = new ArraySet(array1);
    ArraySet AStest2 = new ArraySet(array2);
    ArraySet difTest = ArraySet.difference(AStest1, AStest2);
    System.out.println(difTest);
     */

  }

}
