/**
 * Tester file for Lab 09 - Bloom Filters
 *
 * Colgate University COSC 290L Updated 2021
 */
public class Lab09Tester {

    public static void main(String[] args) {

        //System.out.println("createUniverse example: " + BloomFilter.createUniverse(1));

        //System.out.println("\n*** False Positivity Test: ****");
        //StringHash SIMPLE_HASH = new SimpleHash();
        StringHash SMART_HASH = new SmartHash();

        //System.out.print("Simple Hash test: ");
        //System.out.println(BloomFilter.calculateFalsePositiveRate(SIMPLE_HASH, 100000, 7, 10000));

        System.out.print("Smart Hash test: \n");
        System.out.println("k=21, m=100,000, n=10,000");
        System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 100000, 21, 10000));
        System.out.println("k=7, m=100,000, n=10,000");
        System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 100000, 7, 10000));

        System.out.println("k=7, m=32,333, n=10,000");
        System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 32333, 7, 10000));
        System.out.println("k=2, m=32,333, n=10,000");
        System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 32333, 2, 10000));
        //System.out.println("k=3, m=32,333, n=10,000");
        //System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 32333, 2, 10000));



        //System.out.println(BloomFilter.calculateFalsePositiveRate(SMART_HASH, 100000, 7, 10000));

        //System.out.println("\n*** Recreating Lab Example with added tests: ***");
        //BloomFilter.recreateLabExample();
    }

}
