import java.util.*;

public class Lab10 {
    /**
    That one-line looks so good, right, Matt?
     Please, don't take off my points for this masterpiece!
    It's the end of the semester. We are all losing it a little bit.........

    Here's a crab for you:
    (\/)_i_i_(\/)
     **/
    public static void destroyStack(Stack<String> s){ while(!s.empty()) s.pop(); }

    public static <E> int count(Stack<E> s, E item){
        int count=0;
        if (s.empty())
            return count;

        for (E thing: s)
            if (thing.equals(item))
                count++;
        return count;
    }

    public static <E> Queue makeZip(Queue<E> q1, Queue<E> q2){
        if(q1.isEmpty())
            return q2;
        if(q2.isEmpty())
            return q1;

        int x=0;
        int y=0;
        int newSize = q1.size()+q2.size();

        Queue<E> finalQueue = new LinkedList<>();

        while(finalQueue.size()!=newSize){
            if (x!=q1.size()){
                finalQueue.add(q1.peek());
                q1.add(q1.poll());
                x++;
            }
            if (y!=q2.size()){
                finalQueue.add(q2.peek());
                q2.add(q2.poll());
                y++;
            }
        }

        return finalQueue;
    }

    public static Queue insertIntoSorted(Queue<String> qs, String into){
        boolean added = false;

        for (int i=0; i<=qs.size(); i++){
            if (into.compareTo(qs.peek())>0)
                qs.add(qs.poll());
            else if (!added){
                qs.add(into);
                added=true;
            }
            else if (added)
                qs.add(qs.poll());
        }
        return qs;
    }


    public static void main(String[] args){
        //2.1
        /*
        Stack<String> test = new Stack<>();
        String[] testArray = {"t", "e", "s", "t"};

        for (String str:testArray)
            test.push(str);

        System.out.println(test);
        destroyStack(test);
        System.out.println(test);
         */

        //2.2
        /*
        Stack<Integer> test = new Stack<>();
        System.out.println(count(test, 2));
        for (int i=1; i<=10; i++){
            test.push(i);
        }
        System.out.println(test);
        System.out.println(count(test, 2));
         */

        //3.1
        /*
        Queue<String> test1 = new LinkedList<>();
        test1.add("Test1");
        test1.add("Test3");

        Queue<String> test2 = new LinkedList<>();
        test2.add("Test2");
        test2.add("Test4");
        test2.add("Test5");
        test2.add("Test6");
        test2.add("Test7");
        test2.add("Test8");
         */

        /*
        Queue<String> test1 = new LinkedList<>();
        Queue<String> test2 = new LinkedList<>();
        test2.add("Test2");
        test2.add("Test4");
        test2.add("Test5");
        test2.add("Test6");
        test2.add("Test7");
        test2.add("Test8");
         */

        /*
        Queue<String> test1 = new LinkedList<>();
        test1.add("Test1");
        test1.add("Test2");
        Queue<String> test2 = new LinkedList<>();
         */
        /*
        Queue<String> test1 = new LinkedList<>();
        Queue<String> test2 = new LinkedList<>();
         */

        //System.out.println(makeZip(test1, test2));

        //3.2
        /*
        String test1 = "Emu";
        Queue<String> test2 = new LinkedList<>();
        test2.add("Bird");
        test2.add("Cat");
        test2.add("Dog");
        test2.add("Emu");
        test2.add("Tiger");
        test2.add("Wolf");
        test2.add("Zebra");
         */

        //System.out.println(insertIntoSorted(test2, test1));

    }
}





