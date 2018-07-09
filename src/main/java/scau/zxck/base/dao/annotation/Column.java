package scau.zxck.base.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	
    /**
     * 数据表字段注解，默认值为空串。需要持久化的属性，需要使用此注解标识
     * @return
     */
    String name() default "";
}