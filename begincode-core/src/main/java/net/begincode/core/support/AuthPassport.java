package net.begincode.core.support;

import java.lang.annotation.*;

/**
 * Created by saber on 2016/8/25.
 * 方法注释，不适用于类注释
 * @AuthPassport
 * （validate = false）关闭注解
 */

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
    boolean validate() default true;
}
