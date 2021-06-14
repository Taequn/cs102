/**
 * A (very) simply hash algorithm
 *
 * Colgate University COSC 290L
 * Updated 2021
 */
public class SimpleHash implements StringHash {


    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use
     * @return hash value
     */
    public int hash(String item, int i) {
        // hashes item using its hash code and then simply adds i to the resulting value
        return (Math.abs(item.hashCode())) + i;
    }
}
