
public class FrontBackList<E> {
    
    private int size;
    private Node<E> front, back;
    
    
    //Inner Node class
    private static class Node<E> {
        
        private E data;                 /** The data value. */
        private Node<E> next = null;    /** The link to the next Node in the list*/
        
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
        
        public Node(E data) {
            this(data, null);
        }
    }
    
    
    //Returns the number of items in the list
    public int getSize(){
        return size;
    }
    

    public E get(int idx){
        if (idx >= size || idx < 0)
            throw new IllegalArgumentException("Index out of range! Requested idx: " + idx + ", size = " + size);
        return getNode(idx).data;
    }
    
    
    private Node<E> getNode(int idx){
        Node<E> current = front;
        for (int i = 0; i < idx; i++)
            current = current.next;
        return current;
    }
        
    
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        if (size == 0)
            return "[]";
        Node<E> current = front;
        for (int i = 0; i < size - 1; i++){
            sb.append(current.data + " ==> ");
            current = current.next;
        }
        sb.append(current.data + "]");
        return sb.toString();
    }    
    
    
    public void add(String toAdd){
        add(size, toAdd);
    }
    
        
    //***************************************************************
    //**                                                           **
    //**     YOU DON"T NEED TO MODIFY ANYTHING ABOVE THIS LINE     **
    //**                     YOUR TASKS ARE BELOW                  **
    //**                                                           **    
    //***************************************************************
    
   
    
    public void add(int index, String toAdd){
        //implement me in Part 2.3!
    }
    
       
    
    //Returns index of the first occurence of element
    public int indexOf(String element){
        //implement me in Part 2.5!
        return -1; //placeholder                    
    }
    
    
    
    //removes element at argument index 
    public void remove(int idx){
        //implement me in Part 2.7!
    }
    
    

    //removes first occurence of argument element
    public boolean remove(String toRemove){
        //implement me in Part 2.7!
        return false; //placeholder
    }
    
    
    
    //*** FOR PART 2.8 ***
    //implement TWO functions: a static and non-static implementation of 
    //the concat operation described in Part 2.8.
    //Think about what argument(s) each of these functions need!
    
    
    


    
    
}
