package cn.lcf.lcs;

import org.omg.CORBA.INTERNAL;
import scala.tools.nsc.backend.icode.Primitives;

import java.util.Map;

/**
 　④L(k,i)=Min｛Min｛j｝ Where ｛ai=bj  And j＞L(k-1,i-1)｝，L(k,i-1)｝ 　　此时，1≤i≤M，1≤k≤M

 　　且当i＜k时，则L(k,i)=MaxValue

 　　仔细观察④，公式还可以写成如下
 　　⑤　　L(k,i)=Min｛j｝ Where ｛ai=bj  And L(k-1,i-1)<j<L(k,i-1)｝　1≤i≤M，1≤k≤M，且j存在　 　　
 　　或　　L(k,i)=L(k,i-1)　　1≤i≤M，1≤k≤M，当j不存在时
 */
public class Nakatsu {
    static int startJ = 0;
    public static void main(String[] args) {
        String a = "GGATCGA";
        String b = "GAATTCAGGGATCGATTA";
//        String a = "481234781";
//        String b = "4411327431";
        int[] l = new int[a.length()];
        int[] p = new int[a.length()];
        int[][] arr = new int[a.length()][a.length()];
        l[0] = -1;
        for (int i = 1; i < a.length(); i++) {
            l[i] = Integer.MAX_VALUE;
            p[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < a.length(); i++) {
            for (int j = 1; j < a.length(); j++ ){
               int t = getP(i+j-1,l[j-1],l[j],a,b);
               if(t != Integer.MAX_VALUE)
                   l[j] = t;
               if(p[j] == Integer.MAX_VALUE && l[j] !=Integer.MAX_VALUE)
                    p[j] = l[j];
               if(l[j] == Integer.MAX_VALUE)
                    break;
            }
            if(l[a.length()-i-1] != Integer.MAX_VALUE){
                StringBuffer sb = new StringBuffer();
                for( int k=1; k<a.length() - i +1;k++){
                    sb.append(b.charAt(p[k]));
                }
                System.out.println(sb.toString());
                return;
            }
        }
    }

    private static int getP(int index, int startP, int endP,String a,String b) {
        if(endP == Integer.MAX_VALUE)
            endP = b.length();
        for(int i=startP +1; i<endP; i++){
            if(a.charAt(index) == b.charAt(i))
                return i;
        }
        return Integer.MAX_VALUE;
    }

    public static void show(int[][] arr) {
        System.out.println();
        for (int k = 0; k < arr.length; k++) {
            for (int i = 0; i < arr[0].length; i++) {
                System.out.print(arr[k][i] == Integer.MAX_VALUE ? "_" + "\t" : arr[k][i] + "\t");
            }
            System.out.println();
        }
    }
}
/*
　For i = 0 To _A.Length - 1
　　　　　　　　For j = 1 To _A.Length - i
　　　　　　　　　　T = GetP(i + j - 1, L(j - 1), L(j))
　　　　　　　　　　If T <> Integer.MaxValue Then L(j) = T

　　　　　　　　　　If P(j) = Integer.MaxValue AndAlso L(j) <> Integer.MaxValue Then P(j) = L(j)

　　　　　　　　　　If L(j) = Integer.MaxValue Then Exit For
　　　　　　　　Next

　　　　　　　　If L(_A.Length - i) <> Integer.MaxValue Then
　　　　　　　　　　Dim tS As New System.Text.StringBuilder

　　　　　　　　　　For j = 1 To _A.Length - i
　　　　　　　　　　　　tS.Append(_B(P(j)))
　　　　　　　　　　Next

　　　　　　　　　　Return tS.ToString
　　　　　　　　End If

　　　　　　Next
 */