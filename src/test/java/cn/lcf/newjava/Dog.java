package cn.lcf.newjava;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by yunguijingsan on 2014/10/29.
 */
@RepeatableAnnotation(value = "Dog",time = 4)
@RepeatableAnnotation(value = "Animal",time = 5)
public class Dog implements Animal {

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void eat(Animal animal) {

    }

    @Override
    public void move(List<Integer> list) {

    }
    public static class Car {
        public static Car create( final Supplier< Car > supplier ) {
            return supplier.get();
        }
        public static void collide( final Car car ) {
            System.out.println( "Collided " + car.toString() );
        }
        public void follow( final Car another ) {
            System.out.println( "Following the " + another.toString() );
        }
        public void repair() {
            System.out.println( "Repaired " + this.toString() );
        }
    }
}
