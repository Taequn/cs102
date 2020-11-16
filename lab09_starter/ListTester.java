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

        /** inserting to the end with the index */
        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        test3.add("dog");
        test3.add(2, "frog");
        test3.add(3, "animal");
        System.out.println("Test1.3 (append to the end with index):\n\t" + test3);

        /** inserting in the beginning */
        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("cat");
        test4.add("dog");
        test4.add(0, "frog");
        test4.add(0, "animal");
        System.out.println("Test1.4 (insert in the very beginning):\n\t" + test4);

        /** inserting all over the place */
        FrontBackList<String> test5 = new FrontBackList<String>();
        test5.add("cat");
        test5.add("dog");
        test5.add(1, "frog");
        test5.add(2, "horse");
        test5.add(0, "bird");
        System.out.println("Test1.5 (insert all over):\n\t" + test5);

        /** testing for error */
        FrontBackList<String> test6 = new FrontBackList<String>();
        test6.add("cat");
        test6.add("dog");
        test6.add(4, "dog");
        System.out.println("Test1.5 (error test):\n\t" + test6);
    }
    
    
    private static void testIndexOf(){
        System.out.println("*****    INDEX OF TESTS    *****");

        /** test the middle **/
        FrontBackList<String> test1 = new FrontBackList<String>();
        test1.add("cat");
        test1.add("dog");
        test1.add("bird");
        System.out.println("Test 2.1 (middle element):\n\t" +
                             "index of 'dog' in " + test1 + ": " + test1.indexOf("dog"));

        /** test non-existant **/
        FrontBackList<String> test2 = new FrontBackList<String>();
        test2.add("cat");
        test2.add("dog");
        test2.add("bird");
        System.out.println("Test 2.2 (lacking element):\n\t" +
                "index of 'croc' in " + test2 + ": " + test2.indexOf("croc"));

        /** test the beginning **/
        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        System.out.println("Test 2.3 (starting element):\n\t" +
                "index of 'cat' in " + test3 + ": " + test3.indexOf("cat"));

        /** test the end **/
        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("cat");
        test4.add("dog");
        test4.add("bird");
        System.out.println("Test 2.4 (end element):\n\t" +
                "index of 'bird' in " + test4 + ": " + test4.indexOf("bird"));

        /** test the empty list **/
        FrontBackList<String> test5 = new FrontBackList<String>();
        System.out.println("Test 2.5 (emprty linked list):\n\t" +
                "index of 'croc' in " + test5 + ": " + test5.indexOf("bird"));
    }    
    
    private static void testRemove(){
        System.out.println("*****    REMOVE TESTS    *****");

        FrontBackList<String> test1 = new FrontBackList<String>();
        test1.add("cat");
        test1.add("dog");
        test1.add("bird");
        System.out.println("Test 3.1 (remove index 1):\n\t Before: " + test1);
        test1.remove(1);
        System.out.println("\t After: " + test1);

        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        test3.add("dog");
        test3.add("bird");
        System.out.println("Test 3.3 (remove index 0):\n\t Before: " + test3);
        test3.remove(0);
        System.out.println("\t After: " + test3);

        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("cat");
        test4.add("dog");
        test4.add("bird");
        System.out.println("Test 3.4 (remove index 2 (end)):\n\t Before: " + test4);
        test4.remove(2);
        System.out.println("\t After: " + test4);

        //Test 3.2
        FrontBackList<String> test2 = new FrontBackList<String>();
        test2.add("cat");
        test2.add("dog");
        test2.add("bird");
        System.out.println("Test 3.5 (remove middle element):\b\t Before: " + test2);
        System.out.println("\t  result of removing \"dog\": " + test2.remove("dog"));
        System.out.println("\t After: " + test2);

        FrontBackList<String> test5 = new FrontBackList<String>();
        test5.add("cat");
        test5.add("dog");
        test5.add("bird");
        System.out.println("Test 3.6 (remove starting element):\b\t Before: " + test5);
        System.out.println("\t  result of removing \"cat\": " + test5.remove("cat"));
        System.out.println("\t After: " + test5);

        FrontBackList<String> test6 = new FrontBackList<String>();
        test6.add("cat");
        test6.add("dog");
        test6.add("bird");
        System.out.println("Test 3.7 (remove back element):\b\t Before: " + test6);
        System.out.println("\t  result of removing \"bird\": " + test6.remove("bird"));
        System.out.println("\t After: " + test6);

        FrontBackList<String> test7 = new FrontBackList<String>();
        test7.add("cat");
        test7.add("dog");
        test7.add("bird");
        System.out.println("Test 3.8 (remove non-existent element):\b\t Before: " + test7);
        System.out.println("\t  result of removing \"croc\": " + test7.remove("croc"));
        System.out.println("\t After: " + test7);
        

    }      
    
    
    private static void testConcat(){
        System.out.println("*****    CONCAT TESTS    *****");

        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        test3.add("dog");
        test3.add("bird");

        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("parrot");
        test4.add("horse");
        test4.add("mouse");
        //Merging 2 linked lists of the same length

        /*
        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        test3.add("dog");
        test3.add("bird");

        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("parrot");

        //Merging linked lists of different length
         */

        /*
        FrontBackList<String> test3 = new FrontBackList<String>();
        test3.add("cat");
        test3.add("dog");
        test3.add("bird");

        FrontBackList<String> test4 = new FrontBackList<String>();
        //Merging non-empty list with an empty one
         */

        /*
        FrontBackList<String> test3 = new FrontBackList<String>();

        FrontBackList<String> test4 = new FrontBackList<String>();
        test4.add("parrot");
        test4.add("horse");
        test4.add("mouse");

        //Merging an empty list with non-empty list
         */

        /*
        FrontBackList<String> test3 = new FrontBackList<String>();

        FrontBackList<String> test4 = new FrontBackList<String>();
        //Merging two empty lists
         */


        FrontBackList.concat(test3, test4);
        //test3.concat(test4);
        System.out.println(test3);
        System.out.println(test4);

        
    }
    
    
    
    public static void main(String[] args){
        
        
        //testAdd();         //uncomment me for Part 2.3!
        //testIndexOf();     //uncomment me for Part 2.5!
        //testRemove();      //uncomment me for Part 2.7!
        //testConcat();      //uncomment me for Part 2.8!
                
    }
    
    
   
    
    
    
    
    
    
}