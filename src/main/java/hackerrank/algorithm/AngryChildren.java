package hackerrank.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by lcf on 2015/1/16.
 */
public class AngryChildren {
    static BufferedReader in = new BufferedReader(new InputStreamReader(
            System.in));
    static StringBuilder out = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int numPackets = Integer.parseInt(in.readLine());
        if(numPackets <1 || numPackets > Math.pow(10,5)){
            return;
        }
        int numKids = Integer.parseInt(in.readLine());
        if(numKids < 1 || numKids > numPackets){
            return;
        }

        int[] packets = new int[numPackets];

        for(int i = 0; i < numPackets; i ++)
        {
            packets[i] = Integer.parseInt(in.readLine());
            if(packets[i] < 0 || packets[i] > Math.pow(10,9)){
                return;
            }
        }
        int unfairness = Integer.MAX_VALUE;

        unfairness = unfairness(packets,numKids);

        System.out.println(out.append(unfairness));
    }

    public static int unfairness(int[] arr, int n) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length-n;i++){
            min = Math.min(arr[i+n-1] - arr[i],min);
        }
        return min;
    }
}
