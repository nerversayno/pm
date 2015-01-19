package hackerrank.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lcf on 2015/1/16.
 */
public class ACMTeam {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strs = str.split(" ");
        int numPersons = Integer.valueOf(strs[0]);
        if(numPersons < 2 || numPersons > 500){
            return;
        }
        int numTopics = Integer.valueOf(strs[1]);
        if(numTopics <1 || numTopics > 500){
            return;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numPersons; i++) {
            String s = in.next();
            if(s.length() > numTopics){
                return;
            }
            list.add(s);
        }
        int[][] arr = countTeam(numPersons,list);
        int max = arr[0][0];
        int count = 0;
        for(int i=0;i<numPersons;i++){
            for(int j=i+1;j<numPersons;j++){
                if(arr[i][j] == max){
                    count ++;
                }
            }
        }
        System.out.println(max);
        System.out.println(count);
    }

    public static int[][] countTeam(int numPersons, List<String> list) {
        int[][] arr = new int[numPersons][numPersons];
        int max = 0;
        for(int i=0;i<numPersons;i++){
            for(int j=i+1;j<numPersons;j++){
                arr[i][j] = countTopics(list.get(i),list.get(j));
                max = Math.max(max,arr[i][j]);
            }
        }
        arr[0][0] = max;
        return arr;
    }

    private static int countTopics(String str1, String str2) {
        int i=0;
        int count = 0;
        while (i<str1.length()){
            if(str1.charAt(i) =='1' ||  str2.charAt(i)=='1'){
                count++;
            }
            i++;
        }
        return count;
    }
}
