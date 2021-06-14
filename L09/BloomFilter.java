
/**
 * Abstraction of a bloom filter
 * Methods for inserting/looking up String elements utilizing a variable
 * hashing algorithm (an implementation the included StringHash interface).
 *
 * Colgate University COSC 290L
 * Updated 2021
 */

import java.util.*;

public class BloomFilter {

    private final int k; // number of hash functions
    private StringHash hf; // hash family from which hash functions are obtained
    private boolean[] bitArray;

    private static final StringHash SIMPLE_HASH = new SimpleHash();
    private static final StringHash SMART_HASH = new SmartHash();

    // used in "create universe", but might be useful to you too!
    private static final Random rand = new Random();

    /**
     * Makes a new bloom filter with m slots and k hash functions from the specified
     * hash family.
     * 
     * @param m  number of slots in array
     * @param k  number of hash functions to use
     * @param hf hash family from which to draw hash functions
     */
    public BloomFilter(int m, int k, StringHash hf) {
        this.k = k;
        this.hf = hf;
        this.bitArray = new boolean[m];
    }

    /**
     * Item will go through hashing and be added to the filter
     *
     * @param item  the item you want to add
     */
    public void add(String item) {
        if(item==null)
            throw new RuntimeException("You can't add null!");

        for (int i = 0; i < this.k; i++) {
            int hash = Math.abs(hf.hash(item, i));
            int index = hash % this.bitArray.length;
            this.bitArray[index] = true;
        }
    }

    /**
     * lookUp checks if an item exists in the set
     *
     * @param item  the item you want to add
     * @return boolean:
     * true if the item either definitely exists or might be in the set
     * false if the item is definitely not in the set
     */
    public boolean lookUp(String item) {
        if(item==null)
            throw new RuntimeException("The item you're trying to lookup is null!");

        for (int i = 0; i < this.k; i++) {
            int hash = Math.abs(hf.hash(item, i));
            int index = hash % this.bitArray.length;
            if (!this.bitArray[index]) {
                return false;
            }
        }
        return true;
    }

    // Finish me for Part 4.4
    // Recreate the example from Part 2.3 of the lab writeup here
    // Use print statements each step of the way to track your progress
    // Some code is provided for you, you'll need to fill in the rest
    public static void recreateLabExample() {

        int m = 10;
        int k = 3;

        BloomFilter testFilter = new BloomFilter(m, k, SIMPLE_HASH);

        testFilter.add("dog");
        System.out.println("add \"dog\": " + testFilter);

        testFilter.add("cat");
        System.out.println("add \"cat\": " + testFilter);

        System.out.println("lookup \"dog\"?: " + testFilter.lookUp("dog"));
        System.out.println("lookup \"cat\"?: " + testFilter.lookUp("cat"));
        System.out.println("lookup \"goose\"?: " + testFilter.lookUp("goose"));
        System.out.println("lookup \"ground hog\"?: " + testFilter.lookUp("ground hog"));
        System.out.println("lookup null?: " + testFilter.lookUp(null));
    }

    // Creates a randomly generated universe of cardinality size
    public static Set<String> createUniverse(int size) {
        if (size > Integer.MAX_VALUE)
            throw new RuntimeException("Requested universe size too big per the max string length!");

        Set<String> universe = new HashSet<String>();
        while (universe.size() < size) {
            int toInsert = rand.nextInt(Integer.MAX_VALUE);
            universe.add(toInsert + "");
        }
        return universe;
    }

    // Implement me for Part 4.5
    // returns false positivity rate for a bloom filter with the provided
    // attributes/hash function
    public static double calculateFalsePositiveRate(StringHash hf, int m, int k, int n) {
        BloomFilter bloomFilter = new BloomFilter(m, k, hf);
        HashSet<String> uniqueElements = new HashSet<>();
        double positives = 0;

        for (int i = 0; i < n; i++) {
            String item = String.valueOf(rand.nextInt(Integer.MAX_VALUE));
            while (uniqueElements.contains(item)) {
                item = String.valueOf(rand.nextInt(Integer.MAX_VALUE));
            }
            uniqueElements.add(item);
            bloomFilter.add(item);
        }

        Iterator<String> itr = uniqueElements.iterator();
        while (itr.hasNext()) {
            String item = "Unique" + itr.next();
            if (bloomFilter.lookUp(item)) {
                positives++;
            }
        }

        double optimalK = (m/n)*(Math.log(2));
        if (k==Math.round(optimalK))
            System.out.println("You're using the optimal k value!");
        else
            System.out.println("Optimal number of hashfunctions for this size is " + Math.round(optimalK));

        return positives / n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitArray.length; i++) {
            if (bitArray[i])
                sb.append(i + ",");
        }
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);
        return "Bloom Filter Flipped Bits (0-" + bitArray.length + "): {" + sb + "}";
    }

}
