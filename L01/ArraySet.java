import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A Set that holds a set of strings, backed by a fixed-length array.
 *
 * Colgate University COSC 290L
 * Updated 2021 
 */

public class ArraySet {
    
    //min and max load factors, used to determine when Set needs to resize
    //(load factor = cardinality / capacity)
    private static final double MIN_LOAD_FACTOR = 0.25;
    private static final double MAX_LOAD_FACTOR = 1.0;    
    
    private static final int MIN_CAPACITY = 1;
    
    private String[] elements;             // where elements should be stored (order does not matter)
    private int cardinality;               // the number of elements in the set
    
    /**
     * Create a set via enumeration.  Each element in elementsToAdd should be added to the set.  Note that
     * elementsToAdd may contain duplicates but the created set should not have any duplicates.
     * @param elementsToAdd elements to add to the set
     */
    //Check if initial array has null elements, so we can avoid them
    //use insert function instead
    public ArraySet(String[] elementsToAdd) {
        elements = new String[MIN_CAPACITY];
        cardinality = 0;
        if(elementsToAdd!=null)
            for (String element:elementsToAdd)
                insert(element);

    }
    
    /**
     * Create an empty set.
     */
    public ArraySet() {        
        this(new String[0]);
    }
    
    /**
     * Check for membership in set.
     * @param element element to be checked
     * @return true if element is in the set, false otherwise
     */
    public boolean contains(String element) {
        if(cardinality!=0){
            for(int i=0; i<cardinality; i++)
                if(elements[i].equals(element))
                    return true;
        }

        return false;
    }
    

    //ИСПРАВИТЬ НУЖНО, ЧТОБЫ НЕ ИСПОЛЬЗОВАТЬ НЕСКОЛЬКО РАЗ КОД
    private void resize(){
        float factor = ((float)cardinality/(float)elements.length);

        if (factor==MAX_LOAD_FACTOR){
            String[] resizedArray = new String[elements.length*2];
            for (int i=0; i<elements.length; i++)
                resizedArray[i]=elements[i];
            elements=resizedArray;
        }

        else if (factor==MIN_LOAD_FACTOR){
            String[] resizedArray = new String[elements.length/2];
            for (int i=0; i<resizedArray.length; i++)
                resizedArray[i]=elements[i];
            elements=resizedArray;
        }

    }

    /**
     * Adds one element to set, modifying the set.
     * @param element the element to add
     * @return true if element is succesfully added, false otherwise
     */

    public boolean insert(String element) {
        if (!contains(element) && element!=null){
            elements[cardinality++]=element;
            resize();
            return true;

        }
        return false;
        
    }
    
    /**
     * Removes one element from the set, modifying the set.
     * @param element the element to remove
     * @return true if the element is succesfully removed, false otherwise
     */
    public boolean remove(String element) {
        if (contains(element) && element!=null && cardinality!=0){
            int elementPosition=0;

            for (int i=0; i<cardinality; i++)
                if (elements[i].equals(element))
                    elementPosition=i;

            elements[elementPosition]=null;
            for (int i=elementPosition; i<cardinality; i++)
                elements[i]=elements[i+1];
            cardinality--;
            resize();

            return true;
        }
        return false;
    }
    
    /**
     * Size (aka cardinality) of the set.
     * @return the size of the set (number of distinct elements)
     */
    public int cardinality() {
        return cardinality;
    }
    
    /**
     * Capacity of the set.
     * @return the current storage consumed by this implementation
     */
    public int capacity() {
        return this.elements.length;
    }    
    
    /**
     * Perform set union, producing a new set.
     * Does not modify either argument set
     * @param s1 and s2, the two ArraySets to be unioned
     * @return a new set equal to the union of this set with otherSet
     */
    public static ArraySet union(ArraySet s1, ArraySet s2) {
        ArraySet unionSet = new ArraySet(s1.elements);
        for(String s: s2.elements)
            unionSet.insert(s);

        return unionSet;
    }
    
    /**
     * Perform set intersection, producing a new set.
     * Does not modify either argument set
     * @param s1 and s2, the two ArraySets to be intersected
     * @return a new set equal to the intersection of this set with otherSet
     */
    public static ArraySet intersection(ArraySet s1, ArraySet s2) {
        ArraySet interSet = new ArraySet();

        for(int i=0; i<s1.capacity(); i++){
            String element = s1.elements[i];
            if (s2.contains(element))
                interSet.insert(element);
        }
        return interSet;
    }
    
    /**
     * Perform set difference, producing a new set.
     * Does not modify either argument set
     * @param s1 and s2, the two ArraySets to be differenced
     * @return a new set equal to the difference of this set with otherSet
     */
    public static ArraySet difference(ArraySet s1, ArraySet s2) {
        ArraySet difSet = new ArraySet();

        for (int i=0; i<s1.capacity(); i++){
            String element = s1.elements[i];
            if(!s2.contains(element))
                difSet.insert(element);
        }
        return difSet;
    }
    
    
    /**
     * Returns a String representation of elements in the ArraySet
     * @return String of all elements in the ArraySet
     */
    public String toString(){
        if (cardinality == 0)
            return "{}";
        String toReturn = "";
        for (int i  = 0; i < cardinality; i++){
            toReturn += ", " + elements[i];
        }
        return "{" + toReturn.substring(2) + "}";
    }
    
}
