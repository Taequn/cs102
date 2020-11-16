
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
    
    
    public void add(E toAdd){
        add(size, toAdd);
    }
    
        
    //***************************************************************
    //**                                                           **
    //**     YOU DON"T NEED TO MODIFY ANYTHING ABOVE THIS LINE     **
    //**                     YOUR TASKS ARE BELOW                  **
    //**                                                           **    
    //***************************************************************
    
   
    
    public void add(int index, E toAdd){
        if (index>size || index<0)
            throw new IllegalArgumentException("Index out of range! Requested idx: " + index + ", size = " + size);

        if(this.size==0){
            Node<E> addNew = new Node<E>(toAdd);
            this.front=addNew;
            this.back=addNew;
        }

        else if (index==size && size>0){
            Node<E> addNew = new Node<E>(toAdd);
            getNode(index-1).next=addNew;
            this.back=addNew;
        }

        else if (index==0 && size>0){
            Node<E> addNew = new Node<E>(toAdd);
            addNew.next=this.front;
            this.front = addNew;
        }

        else{
            Node<E> addNew = new Node<E>(toAdd, getNode(index));
            getNode(index-1).next=addNew;
        }
        size++;
    }
    
       
    
    //Returns index of the first occurence of element
    public int indexOf(E element){
        for (int i=0; i<size; i++){
            if (getNode(i).data.equals(element))
                return i;
        }
        return -1; //placeholder                    
    }
    
    
    
    //removes element at argument index 
    public void remove(int idx){
        if (idx >= this.size || idx < 0)
            throw new IllegalArgumentException("Index out of range! Requested idx: " + idx + ", size = " + size);

        if (this.size==1){
            this.front=null;
            this.back=null;
        }
        else if (idx==0){
            this.front=getNode(1);
        }

        else if (idx==this.size-1 && size>1){
            this.back=getNode(this.size-1);
        }

        else {
            getNode(idx-1).next=getNode(idx+1);
        }
        size--;
    }
    
    

    //removes first occurence of argument element
    public boolean remove(E toRemove){
        for (int i=0; i<this.size; i++){
            if (getNode(i).data==toRemove) {
                remove(i);
                return true;
            }
        }
        return false; //placeholder
    }

    public void concat(FrontBackList<E> sL){
        concat(this, sL);
    }

    public static <E> void concat(FrontBackList<E> fL, FrontBackList<E> sL){
        if (fL.getSize()==0){
            fL.front=sL.front;
            fL.back=sL.back;
            fL.size=sL.size;
        }
        else {
            fL.back.next = sL.front;
            fL.back = sL.back;
            fL.size += sL.getSize();
        }
        sL.back=null;
        sL.front=null;
        sL.size=0;

    }

    
    


    
    
}
