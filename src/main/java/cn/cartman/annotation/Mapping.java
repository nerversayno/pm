package cn.cartman.annotation;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lcf on 2014/11/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {
    String value() default "";
    Method[] method() default {Method.GET};

    ResponseCode[] status() default {};

    String descriptoin() default "";

//    AnnotationConfigWebApplicationContext
}
