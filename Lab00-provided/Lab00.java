import java.util.*;

public class Lab00{
    
        
    public static String stringReverse(String str){
        if (str == null || str.length()==0)
            return str;
        return stringReverse(str.substring(1)) + str.charAt(0);
    }
    
    
    
    public static String stringMirror(String str){
        if (str == null || str.length()<=1)
            return str;
        return str.substring(0, str.length()-1)+stringReverse(str.substring(1)) + str.charAt(0);
    }    
    
    
    
    public static int stackSum(Stack<Integer> s){
        if (s.empty())
            return 0;
        if (s.size()==1)
            return s.peek();

        int checking = s.pop();
        int sum = stackSum(s);

        s.push(checking);
        return sum + checking;
    }        
    
    
    
    public static <E> void queueReverse(Queue<E> q){
        if (!q.isEmpty()){
            E thingToSave = q.peek();
            q.remove();
            queueReverse(q);
            q.add(thingToSave);
        }
    }
    
    
    
    public static void mapInverter(HashMap<String, String> hm){
        boolean hasDupeValues = new HashSet<>(hm.values()).size() != hm.size();
        if (hasDupeValues){
            throw new IllegalArgumentException("Sorry, but that's a no-go");
        }
        if (!hm.isEmpty()){
            String newValue=getFirstKey(hm);
            String newKey=hm.get(newValue);
            hm.remove(newValue);
            mapInverter(hm);
            hm.put(newKey, newValue);
        }
    }
    
    
    
    //Helper function, what does this do??
    //Experiment with it and check out the API online
    private static String getFirstKey(HashMap<String, String> hm){                       
        return hm.keySet().iterator().next();
    }
   
    
    
    
    
}