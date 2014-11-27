package cn.cartman.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lcf on 2014/11/19.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String value() default "";

    boolean required() default false;

    String defaultValue() default 	"\n\t\t\n\t\t\n\uE000\uE001\uE002\n\t\t\t\t\n";

    String descriptoin() default "";
}
