package cn.lcf.lcs;

import java.util.List;

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
        for (int[] ar : arr) {
            for (int a : ar) {
                System.out.print(a + "\t");
            }
            System.out.println();
        }
        LDResult(arr);
    }

    public static String LDResult(int[][] arr) {
       String resultA ="";
       String resultB ="";

        int i =  arr.length-1;
        int j =  arr[0].length-1;

        while (i != 0 || j != 0) {
            int intA = arr[i-1][j-1];
            int intB = arr[i-1][j];
            int intC = arr[i][j-1];
            int intD  = min(intA,intB,intC);
            if(intD==intA || a.charAt(i-1) == b.charAt(j-1) ){
               resultA += a.charAt(i-1);

                if( a.charAt(i-1) != b.charAt(j-1) ){
                    resultB += String.valueOf(b.charAt(j-1)).toLowerCase();
                }else {
                    resultB += b.charAt(j-1);
                }
               i = i-1;
               j = j-1;
            }else if(intD==intB) {
                resultA += String.valueOf(a.charAt(j-1)).toLowerCase();
                resultB += "_";
                i= i-1;
            }else if(intD==intC){
                resultA += "_";
                resultB += String.valueOf(b.charAt(j-1)).toLowerCase();
                j=j-1;
            }
        }
        System.out.println(new StringBuffer(resultA).reverse());
        System.out.println(new StringBuffer(resultB).reverse());
        return "";
    }

    public static int min(int a,int b,int c){
         return Math.min(Math.min(a,b),c);
    }

    public static void LD(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.min(Math.min(arr[i - 1][j - 1], arr[i - 1][j]), arr[i][j - 1]) + 1;
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
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}
/*
A：GGA_TC_G__A
B：GAATTCAGTTA
 */
/*

LD算法矩阵
       G	A	A	T	T	C	A	G	T	T	A
 	0	1	2	3	4	5	6	7	8	9	10	11
G	1	0	1	2	3	4	5	6	7	8	9	10
G	2	1	1	2	3	4	5	6	6	7	8	9
A	3	2	1	1	2	3	4	5	6	7	8	8
T	4	3	2	2	1	2	3	4	5	6	7	8
C	5	4	3	3	2	2	2	3	4	5	6	7
G	6	5	4	4	3	3	3	3	3	4	5	6
A	7	6	5	4	4	4	4	3	4	4	5	5


    0	1	2	3	4	5	6	7	8	9	10	11
    1	0	1	2	3	4	5	6	7	8	9	10
    2	1	1	2	3	4	5	6	6	7	8	9
    3	2	1	1	2	3	4	5	6	7	8	8
    4	3	2	2	1	2	3	4	5	6	7	8
    5	4	3	3	2	2	2	3	4	5	6	7
    6	5	4	4	3	3	3	3	3	4	5	6
    7	6	5	4	4	4	4	3	4	4	5	5
 */