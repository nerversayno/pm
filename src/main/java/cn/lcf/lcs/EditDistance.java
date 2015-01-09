package cn.lcf.lcs;

/**
 * Created by lcf on 2015/1/8.
 */
public class EditDistance {
    static String a = "GGATCGA";
    static String b = "GAATTCAGTTA";

    public static void main(String[] args) {
//        System.out.println(LD(a.length()-1,b.length()-1));
        int[][] arr = initLD(a, b);
        LD(arr);
        for(int[] ar :arr){
            for(int a : ar){
                System.out.print(a+"\t");
            }
            System.out.println();
        }
    }
    public static void LD(int[][] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)){
                    arr[i][j] = arr[i-1][j-1];
                }else {
                    arr[i][j] =  Math.min(Math.min(arr[i - 1][j - 1], arr[i - 1][j]),arr[i][ j - 1]) + 1;
                }
            }
        }
    }
    public static int[][] initLD(String a, String b) {
        int[][] arr = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j] = 0;
                } else if (i == 0 && j != 0) {
                    arr[i][j] = j;
                } else if (i != 0 && j == 0) {
                    arr[i][j] = i;
                }
            }
        }
        return arr;
    }
}
