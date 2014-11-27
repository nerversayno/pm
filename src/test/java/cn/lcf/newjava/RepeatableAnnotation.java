package cn.lcf.newjava;

import java.lang.annotation.*;

/**
 * Created by yunguijingsan on 2014/11/3.
 */
@Target(ElementType.TYPE)
@Retention( RetentionPolicy.RUNTIME )
@Repeatable(RepeatableAnnotations.class)
public @interface RepeatableAnnotation {
     String value() default  "name";
       long time() default 1;
}
