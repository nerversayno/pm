package hackerrank.algorithm;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by lcf on 2015/1/15.
 */
public class AlternatingCharacters {
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        int count = in.nextInt();
        if(count<1 || count > 10){
            return;
        }
        int[] output = new int[count];
        for(int i=0;i<count;i++){
            String str = in.next();
            if(str.length() <1 || str.length() > Math.pow(10,6)){
                return;
            }
            output[i] = alterCharacters(str);
        }
        for(int i=0; i<count;i++){
            System.out.println(output[i]);
        }
    }

    private static int alterCharacters(String str) {
        int count = 0;
        char a = str.charAt(0);
        if(str.length() == 1) {
            return count;
        }
        for (int i=1;i<str.length();i++){
            if(a == str.charAt(i)){
                count ++;
            }else {
                a = str.charAt(i);
            }
        }
        return count;
    }
}
