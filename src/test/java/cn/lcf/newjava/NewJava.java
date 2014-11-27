package cn.lcf.newjava;

import org.codehaus.jackson.map.util.Annotations;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by yunguijingsan on 2014/10/30.
 */
public class NewJava {
    @Test
    public void testMethoRef() {
        Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);
    }

    @Test
    public void testRepeatableAnnotation() {
        for (RepeatableAnnotation repeatableAnnotation : Dog.class.getAnnotationsByType(RepeatableAnnotation.class)) {
            System.out.println(repeatableAnnotation.value() + " : " + repeatableAnnotation.time());
        }
    }

    @Test
    public void testOptional() {
        String nullString = null;
        Optional<String> optional = Optional.ofNullable(nullString);
        System.out.println(optional.toString());
    }

    @Test
    public void testStream() {
        List<Integer> list = Arrays.asList(1, null, 3, null, 6, 2, null, 9, 8);
        long notNullCount = list.stream().filter(num -> num != null).count();
//        list.stream().distinct().forEach(System.out::print);
        list.stream().mapToInt(e -> e).forEach(System.out::print);
    }
    @Test
    public void testStreamMap(){
        List<Integer> list = Arrays.asList(1, null, 3, null, 6, 2, null, 9, 8);
        list.stream().distinct().filter(e->e!=null).mapToInt(e -> (int) Math.floor(e)).forEach(System.out::print);
        System.out.println();
//        list.forEach(System.out::print);

        Stream s = list.stream();

        Stream stream =   list.stream().peek(e->{
            System.out.println("consume"+e);
        });
        System.out.println();
//        stream.forEach(System.out::println);

        System.out.println(list.stream().filter(num -> num !=null).mapToInt(num -> num*2).sum());
    }
    @Test
    public void testReduce(){
        List<Integer> list = Arrays.asList(1, 9, 3, 2, 6, 2, 12, 9, 8);
        Integer result = list.stream().reduce((sum,item)->sum+item).get();
        System.out.println(result);

        System.out.println(list.stream().allMatch(i -> i < 10));
        System.out.println(list.stream().anyMatch(i -> i < 10));

    }

}

