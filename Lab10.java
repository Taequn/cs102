import java.util.*;
public class ArrayListInt {

    private int[] elems;

    public ArrayListInt(int[] data) {
        this.elems = new int[data.length];
        for (int i=0; i<data.length; i++)
            elems[i] = data[i];
    }


    public void removeRepeat(){
        int[] middle = new int[elems.length];
        int position=0;
        int newLength = elems.length;

        for (int i=0; i<elems.length; i++){
            int checking;

            for (checking=0; checking<i; checking++){
                if (elems[i]==elems[checking]){
                    newLength-=1;
                    break;
                }

            }
            if (i==checking) {
                middle[position++] = elems[i];
            }
        }

        this.elems = Arrays.copyOf(middle, newLength);

    }

    public String toString() {
        String res = "content: [ ";
        for (int i=0; i<elems.length; i++)
            res+=elems[i]+" ";
        return res+"]";
    }

    public static void main(String[] args){
        ArrayListInt numbers =
                new ArrayListInt(new int[]{12, 11, 11, 15, 13, 11, 12, 14});
        numbers.removeRepeat();
        System.out.println(numbers);
    }
}





