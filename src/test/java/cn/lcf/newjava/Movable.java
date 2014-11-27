package cn.lcf.newjava;

import java.util.List;

/**
 * Created by yunguijingsan on 2014/10/29.
 */
@FunctionalInterface
public interface Movable{
    void move(List<Integer> list);
    // may not implement (override) them.
    default String notRequired() {
        return "Default implementation";
    }

    boolean equals(Object obj);

}
