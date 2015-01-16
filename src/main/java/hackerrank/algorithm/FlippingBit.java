package hackerrank.algorithm;

/**
 * Created by lcf on 2015/1/14.
 * 4294967296
 */
public class FlippingBit {

    private final static int LENGTH = 32;
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        int count;
        count = in.nextInt();
        if(count < 1 && count > 100){
            System.out.println("1~100");
            count = in.nextInt();
        }

        long[] input = new long[count];
        for (int i = 0; i < count; i++) {
            input[i] = in.nextLong();
        }
        for (int i = 0; i < count; i++) {
            if(input[i] < 0 || input[i] > Math.pow(2,LENGTH)){
                System.out.println("");
            }else {
                System.out.println(flippingBit(input[i]));
            }
        }
    }

    public static long flippingBit(long i) {
        String str = Long.toBinaryString(i);
        while (str.length() < LENGTH) {
            str = '0' + str;
        }
        String result = "";
        for (int j = 0; j < LENGTH; j++) {
            result += str.charAt(j) == '0' ? '1' : '0';
        }
        return parse(result,2);
    }

    public static long parse(String str, int radix) {
        int i = 1;
        int max = str.length();
        long result = 0;
        while (i <= max) {
            result += (str.charAt(i-1) == '0' ? 0 : 1) * Math.pow(radix, max - i);
            i++;
        }
        return result;
    }
}
