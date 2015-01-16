package hackerrank.algorithm;

import java.util.Scanner;

/**
 * Created by lcf on 2015/1/15.
 */
public class UtopianTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        byte count = in.nextByte();
        if (count < 1 || count > 10) {
            return;
        }
        byte[] input = new byte[count];
        for (byte i = 0; i < count; i++) {
            byte temp = in.nextByte();
            if (temp < 0 || temp > 60) {
                System.out.println("out of range (1~60),input again");
                i--;
            } else {
                input[i] = temp;
            }
        }
        for(byte i=0; i<count; i++){
            byte k = 1;
            int height = 1;
            while (k<=input[i]){
                height = grow(height,k);
                k++;
            }
            System.out.println(height);
        }
    }

    public static int grow(int init, byte cycle) {
        if (cycle > 0) {
            return cycle % 2 == 0 ? init + 1 : 2*init;
        } else {
            return init;
        }
    }
}
