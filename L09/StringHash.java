/**
 * Interface for hashing algorithms to be used with the included BloomFilter class
 *
 * Colgate University COSC 290L
 * Updated 2021
 */
public interface StringHash {

    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use
     * @return hash value
     */
    public int hash(String item, int i);
    


}
