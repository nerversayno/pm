package hackerrank.algorithm;


import java.util.Scanner;

/**
 * Created by lcf on 2015/1/15.
 */
public class MaximizingXOR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short l = scanner.nextShort();
        short r = scanner.nextShort();
        if (l > 1000 || r > 1000 || l > r || l < 1 || r < 1) {
            return;
        }
        System.out.println(maxXor(5,10));
    }
    public static int maxXor(int _l, int _r){
        int max = 0;
        for (int i = _l; i <= _r; i++) {
            for (int j = _l; j <= _r; j++) {
                max = Math.max(max, i ^ j);
            }
        }
        return max;
    }
}
