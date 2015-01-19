package hackerrank.algorithm;

/**
 * Created by lcf on 2015/1/16.
 */
public class LoveLetterMystery {
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        int count;
        count = in.nextInt();
        if(count < 1 && count > 10){
            System.out.println("1~100");
            count = in.nextInt();
        }

        String[] input = new String[count];
        for (int i = 0; i < count; i++) {
            input[i] = in.next();
        }
        for (int i = 0; i < count; i++) {
            if(input[i].length() < 0 || input[i].length() > Math.pow(10,4)){
                System.out.println("");
            }else {
                System.out.println(countHuiWeng(input[i]));
            }
        }
    }

    public static int countHuiWeng(String str) {
        int count = 0;
        int i = 0;
        while (i < str.length()/2) {
            char left = str.charAt(i);
            char right = str.charAt(str.length()-1 - i);
            count += Math.abs((int)left - (int)right);
            i++;
        }
        return  count;
    }
}
