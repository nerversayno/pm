package cn.lcf.newjava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yunguijingsan on 2014/11/3.
 */
@Target(ElementType.TYPE)
@Retention( RetentionPolicy.RUNTIME )
public @interface RepeatableAnnotations {
    RepeatableAnnotation[] value();
}
