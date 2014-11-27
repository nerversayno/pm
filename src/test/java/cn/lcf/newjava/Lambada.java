package cn.lcf.newjava;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yunguijingsan on 2014/10/29.
 */
public class Lambada {
    private static void test(List<Integer> list, Movable movable) {
        movable.move(list);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//
//        Arrays.asList().forEach(e -> System.out.println(e));
//        Arrays.asList().sort();

        for (int i = 0; i < 10; i++) {
            final int k = i;
            Movable movable = (e) -> {
                e.add(k);
            };
        }
        test(new ArrayList<>(), new Movable() {
            @Override
            public void move(List<Integer> e) {
                System.out.println(e.size());
            }
        });

        List<List<LambadaEntity>> lists = new ArrayList<List<LambadaEntity>>();
        for(int j = 0; j < 3; j ++){
            List<LambadaEntity> lambadaEntityList = new ArrayList<LambadaEntity>();
            for(int i = 0; i < 4; i++){
                lambadaEntityList.add(new LambadaEntity(j,""+i));
            }
            lists.add(lambadaEntityList);
        }
        lists.forEach(list->{
           list.forEach(lam ->{
               System.out.println(lam.getI() +"-------"+ lam.getName());
           });
        });
//
//        String str1 = "\u6237";
//        String str2 = new String(str1.getBytes("UTF-8"));
//        System.out.println(str2);
       new Thread(new Runnable() {
           @Override
           public void run() {

           }
       });

       new Thread(()->{

       });
    }
}
