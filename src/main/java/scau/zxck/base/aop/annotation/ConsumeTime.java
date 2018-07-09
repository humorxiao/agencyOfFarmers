package scau.zxck.base.aop.annotation;

import java.lang.annotation.*;

/**
 * 注解到Service方法上，计算该Service方法的总运行时间
 * Created by lishunpeng on 2015/11/13.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ConsumeTime {
}
