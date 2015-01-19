package hackerrank.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lcf on 2015/1/16.
 */
public class GameThrones1 {
    public static void main(String[] args) {
        System.out.println(isPosible("aaabbbb"));
    }
    public static boolean isPosible(String str){
        Set set = new HashSet<Character>();
        for(int i=0; i<str.length();i++){
            if( set.contains(str.charAt(i))    ){
                set.remove(str.charAt(i));
            }else {
                set.add(str.charAt(i));
            }
        }
        return set.size() <= 1;
    }
}
