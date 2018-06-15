package com.hcb.commons.json.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JsonProperty {

    public String title() default "";
    public String description() default "";
    public boolean required() default false;
    public String defaultJsonExp() default "";
    public String values() default "";
}
