import java.util.*;

public class Lab00Tester{
    
        
   
 
    public static void main(String[] args){
      
        //Delete the below line when you're ready to start working!
        //throw new IllegalArgumentException("Implement me!");
      
        //I've given you some test cases, but add more of your own!
        //Uncomment each block as you implement its respective function
        
        /*
        System.out.println("****  STRING REVERSE  ***");
        System.out.println(Lab00.stringReverse("cat"));
         */
        
                
        
        /*
        System.out.println("\n\n****  STRING MIRROR  ***");
        System.out.println(Lab00.stringMirror("cat"));
         */
        
        
        
        /*
        System.out.println("\n\n****  STACK SUM  ***");        
        Stack<Integer> s = new Stack<Integer>();
        s.push(7);
        s.push(4);
        s.push(3);
        s.push(1);
        System.out.println("Before: " + s);
        System.out.println("Sum: " + Lab00.stackSum(s));
        System.out.println("After: " + s);
         */

        
        
        /*
        System.out.println("\n\n****  QUEUE REVERSE  ***");  
        Queue<String> q = new LinkedList<String>();
        q.add("cat");
        q.add("dog");
        q.add("bird");
        q.add("frog");
        System.out.println("Before: " + q);
        Lab00.queueReverse(q);
        System.out.println("After: " + q);
         */
        

        System.out.println("\n\n****  MAP INVERT  ***");  
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("cat", "BigCat");
        hm.put("dog", "Brownie");
        hm.put("parrot", "Polly");
        System.out.println("Before: " + hm);
        Lab00.mapInverter(hm);
        System.out.println("After: " + hm);
    }
    
    
    
    
}