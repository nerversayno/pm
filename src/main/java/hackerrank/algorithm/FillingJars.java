package hackerrank.algorithm;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lcf on 2015/1/16.
 */
public class FillingJars {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 3 || n > Math.pow(10, 7)) {
            return;
        }
        int m = in.nextInt();
        if (m < 1 || m > Math.pow(10, 5)) {
            return;
        }
        int[][] arr = new int[m][4];
        for (int i = 0; i < m; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
            arr[i][2] = in.nextInt();
            if (arr[i][0] < 1 || arr[i][1] < arr[i][0] || arr[i][1] > n) {
                return;
            }
            if (arr[i][2] < 0 || arr[i][2] > Math.pow(10, 6)) {
                return;
            }
        }
        System.out.println(countTotal(arr)/n);
    }

    public static long countTotal(int[][] arr) {
        long total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += (long)(arr[i][1] - arr[i][0] +1) * (long)arr[i][2];
        }
        return total;
    }

    @Test
    public void test(){
        System.out.println(Long.MAX_VALUE);
        System.out.println(Math.pow(10,6)*Math.pow(10,7));
        System.out.println(Long.MAX_VALUE/10);
        System.out.println(4991588628l * 30000);
    }
}
