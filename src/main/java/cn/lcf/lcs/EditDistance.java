package cn.lcf.lcs;

import java.util.List;

/**
 * Created by lcf on 2015/1/8.
 */
public class EditDistance {
    static String a = "GGATCGA";
    static String b = "GAATTCAGTTAA";

//    static String a = "481234781";
//    static String b = "4411327431";
    public static void main(String[] args) {
        int[][] arr = new int[2][b.length()];
        for(int j =0; j<b.length();j++){
            arr[0][j] = j;
        }
//        LD(arr);
         LD(a, b, arr);
        System.out.println();
        for (int[] ar : arr) {
            for (int a : ar) {
                System.out.print(a + "\t");
            }
            System.out.println();
        }
//        LDResult(arr);
    }

    public static String LDResult(int[][] arr) {
        String resultA = "";
        String resultB = "";

        int i = arr.length - 1;
        int j = arr[0].length - 1;
        if (j == 0 || i == 0) {
            while (i != 0) {
                resultA += String.valueOf(a.charAt(i - 1));
                resultB += "_";
                i--;
            }
            while (j != 0) {
                resultA += "_";
                resultA += String.valueOf(b.charAt(j - 1));
                j--;
            }
            System.out.println(new StringBuffer(resultA).reverse());
            System.out.println(new StringBuffer(resultB).reverse());
            return "";
        }
        int k = Math.max(i, j) - 1;
        while (k > -1) {
            int intA = arr[i - 1][j - 1];
            int intB = arr[i - 1][j];
            int intC = arr[i][j - 1];
            int intD = min(intA, intB, intC);
            if (intD == intA || a.charAt(i - 1) == b.charAt(j - 1)) {
                resultA += a.charAt(i - 1);
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    resultB += String.valueOf(b.charAt(j - 1)).toLowerCase();
                } else {
                    resultB += b.charAt(j - 1);
                }
                i--;
                j--;
            } else if (intD == intB) {
                resultA += String.valueOf(a.charAt(i - 1)).toLowerCase();
                resultB += "_";
                i--;
            } else if (intD == intC) {
                resultA += "_";
                resultB += String.valueOf(b.charAt(j - 1)).toLowerCase();
                j--;
            }
            if (j == 0 || i == 0) {
                while (i != 0) {
                    resultA += String.valueOf(a.charAt(i - 1));
                    resultB += "_";
                    i--;
                    k--;
                }
                while (j != 0) {
                    resultA += "_";
                    resultA += String.valueOf(b.charAt(j - 1));
                    j--;
                    k--;
                }
            }
            k--;
        }
        System.out.println(new StringBuffer(resultA).reverse());
        System.out.println(new StringBuffer(resultB).reverse());
        return "";
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
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

    public static int LD(String a, String b,int[][] arr) {
        int k = 1;
        for (int i = 1; i < a.length()+1 ; i++) {
            for (int j = 1; j <b.length(); j++) {
                int prev = k == 1 ? 0 :1;
                arr[k][0] = i;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    arr[k][j] = arr[prev][j - 1];
                } else {
                    arr[k][j] = Math.min(Math.min(arr[prev][j - 1], arr[prev][j]), arr[k][j - 1]) + 1;
                }
                System.out.print(arr[k][j] + "\t");
            }
            k =  k == 1 ? 0 :1;
            System.out.println();
        }
        if(k == 1)
            return  arr[0][b.length()-1];
        else
            return arr[1][b.length()-1];
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