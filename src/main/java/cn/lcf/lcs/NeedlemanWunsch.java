package cn.lcf.lcs;

import java.util.Collections;

/**
 * Created by lcf on 2015/1/8.
 */
public class NeedlemanWunsch {
    static String a = "GGATCG";
    static String b = "GAATTCAGTTAA";

    //A=GGATCGA，B=GAATTCAGTTA
    public static void main(String[] args) {
//        System.out.println(LD(a.length()-1,b.length()-1));
        int[][] arr = initLCS(a, b);
        LCS(arr);
        for (int[] ar : arr) {
            for (int a : ar) {
                System.out.print(a + "\t");
            }
            System.out.println();
        }
        LCSResult(arr);
    }

    public static String LCSResult(int[][] arr) {
        String resultA = "";
        String resultB = "";

        int i = arr.length - 1;
        int j = arr[0].length - 1;
        int k = Math.max(i, j) - 1;
        while (k > -1) {
            int intA = arr[i - 1][j - 1];
            int intB = arr[i - 1][j];
            int intC = arr[i][j - 1];
            int intD = max(intA, intB, intC);
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

    public static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void LCS(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = max(arr[i - 1][j - 1], arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
    }

    public static int[][] initLCS(String a, String b) {
        int[][] arr = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
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


Needleman/Wunsch算法矩阵
 	 	G	A	A	T	T	C	A	G	T	T	A
 	0	0	0	0	0	0	0	0	0	0	0	0
G	0	1	1	1	1	1	1	1	1	1	1	1
G	0	1	1	1	1	1	1	1	2	2	2	2
A	0	1	2	2	2	2	2	2	2	2	2	2
T	0	1	2	2	3	3	3	3	3	3	3	3
C	0	1	2	2	3	3	4	4	4	4	4	4
G	0	1	2	2	3	3	3	4	5	5	5	5
A	0	1	2	3	3	3	3	4	5	5	5	6


    0	0	0	0	0	0	0	0	0	0	0	0
    0	1	1	1	1	1	1	1	1	1	1	1
    0	1	1	1	1	1	1	1	2	2	2	2
    0	1	2	2	2	2	2	2	2	2	2	3
    0	1	2	2	3	3	3	3	3	3	3	3
    0	1	2	2	3	3	4	4	4	4	4	4
    0	1	2	2	3	3	4	4	5	5	5	5
    0	1	2	3	3	3	4	5	5	5	5	6
 */