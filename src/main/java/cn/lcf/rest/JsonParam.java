package cn.lcf.rest;

import java.lang.annotation.*;

/**
 * User: lcf
 * Date: 14-3-31
 * Time: ����10:27
 * Description:
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonParam {
    String value() default "";
    boolean required() default  true;
}
