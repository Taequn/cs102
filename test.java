import java.util.*;
public class test {
    public static double averageVowels(ArrayList<String> arr){
        double count=0;
        double totalCount=0;
        if (arr.size()==0){
            return 0.0;
        }

        String vowel = "aeiou";
        for (int i=0; i<arr.size(); i++){
            String mystery = arr.get(i);
            for(int j=0; j<mystery.length(); j++){
                totalCount++;
                if (vowel.indexOf(mystery.charAt(j))!=-1){
                    count++;
                }

            }
        }
        return count/totalCount;
    }

    public static boolean removeEvenLength(ArrayList<String> myarr){
        if (myarr.size()>0) {
            for (int i = 0; i < myarr.size(); i++) {
                String mystr = myarr.get(i);
                if (mystr.length() % 2 == 0) {
                    myarr.remove(i);
                }
            }
            return true;
        }
        return false;
    }

    public static void swapPairs(ArrayList<String> myarr) {
        if (myarr.size() % 2 == 0) {
            for (int i = 0; i < myarr.size(); i += 2) {
                String temp1 = myarr.get(i), temp2 = myarr.get(i + 1);
                myarr.set(i, temp2);
                myarr.set(i + 1, temp1);
            }
        } else {
            for (int i = 0; i < myarr.size() - 1; i += 2) {
                String temp1 = myarr.get(i), temp2 = myarr.get(i + 1);
                myarr.set(i, temp2);
                myarr.set(i + 1, temp1);
            }
        }
    }


    public static void main(String[] arggs){
        ArrayList<String> arrayL = new ArrayList();
        arrayL.add("Emily");
        arrayL.add("Anna");
        arrayL.add("Parfait");
        arrayL.add("Chris");
        arrayL.add("Elodie");
        System.out.println(arrayL);
        swapPairs(arrayL);
        System.out.println(arrayL);

    }
}





