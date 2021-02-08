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
    
    //initialize and print a set of {a, b, c}
    String[] s1Init =  {"a", "b", "c"};
    String[] s2Init =  {"a", "b", "e"};

    ArraySet s1 = new ArraySet(s1Init);
    ArraySet s2 = new ArraySet(s2Init);

    //System.out.println("Contents of s1 = " + s1);
    //System.out.println("Cardinality = " + s1.cardinality() + ", capacity = " + s1.capacity());


    //attempting to add additional elements
    //System.out.println("Attempting to add \"d\", successful? = " + s1.insert("d"));
    //System.out.println("Attempting to add \"b\", successful? = " + s1.insert("b"));
    //System.out.println("Contents of s1 = " + s1);

    //lookups
    //System.out.println("Does \"a\" exist in my set?: " + s1.contains("a"));
    //System.out.println("Does \"q\" exist in my set?: " + s1.contains("q"));

    //remove
    //System.out.println("Attempting to remove \"b\", successful? = " + s1.remove("b"));
    //System.out.println("Contents of s1 = " + s1);

    ArraySet s3 = ArraySet.difference(s1, s2);
    //System.out.println("Cardinality = " + s1.cardinality() + ", capacity = " + s1.capacity());
    System.out.println("Contents of s3 = " + s3);
    System.out.println("Cardinality = " + s3.cardinality() + ", capacity = " + s3.capacity());

  }

}
