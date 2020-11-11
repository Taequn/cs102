public class ListTester{
    
    
    
    private static void testAdd(){
        System.out.println("*****    ADD TESTS    *****");
        
        //Test 1.1
        FrontBackList<String> test1 = new FrontBackList<String>();
        test1.add("cat");
        test1.add("dog");
        test1.add("bird");
        System.out.println("Test1.1 (append to end):\n\t" + test1);
        
        
        //Test 1.2
        FrontBackList<String> test2 = new FrontBackList<String>();
        test2.add("cat");
        test2.add("dog");
        test2.add(1, "frog");
        System.out.println("Test1.2 (insert to beginning):\n\t" + test2);        
        

    }
    
    
    private static void testIndexOf(){
        System.out.println("*****    INDEX OF TESTS    *****");
        
        //Test 2.1
        FrontBackList<String> test1 = new FrontBackList<String>();
        test1.add("cat");
        test1.add("dog");
        test1.add("bird");
        System.out.println("Test 2.1 (middle element):\n\t" +
                             "index of 'dog' in " + test1 + ": " + test1.indexOf("dog"));
        

    }    
    
    private static void testRemove(){
        System.out.println("*****    REMOVE TESTS    *****");

        //Test 3.1
        FrontBackList<String> test1 = new FrontBackList<String>();
        test1.add("cat");
        test1.add("dog");
        test1.add("bird");
        System.out.println("Test 3.1 (remove index 1):\n\t Before: " + test1);
        test1.remove(1);
        System.out.println("\t After: " + test1);        
        
        
        //Test 3.2
        FrontBackList<String> test2 = new FrontBackList<String>();
        test2.add("cat");
        test2.add("dog");
        test2.add("bird");
        System.out.println("Test 3.2 (remove middle element):\b\t Before: " + test2);
        System.out.println("\t  result of removing \"dog\": " + test2.remove("dog"));
        System.out.println("\t After: " + test2);
        

    }      
    
    
    private static void testConcat(){
        System.out.println("*****    CONCAT TESTS    *****");
        
        //come up with test cases of your own!
        
    }
    
    
    
    public static void main(String[] args){
        
        //Come up with test cases of your own
        //the provided tests are not sufficient!
        
        
        //testAdd();         //uncomment me for Part 2.3!     
        //testIndexOf();     //uncomment me for Part 2.5!
        //testRemove();      //uncomment me for Part 2.7!
        //testConcat();      //uncomment me for Part 2.8!
                
    }
    
    
   
    
    
    
    
    
    
}