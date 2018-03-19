package apm.web.util.apmutil.jsonutils;

import java.lang.annotation.*;

/**
 * @author ChengBing Han
 * @date 23:09  2018/1/29
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONS.class)   // 让方法支持多重@JSON 注解
public @interface JSON {
    Class<?> type();
    String include() default "";
    String filter() default "";
}