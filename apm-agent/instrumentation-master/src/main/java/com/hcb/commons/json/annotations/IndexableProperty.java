package com.hcb.commons.json.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IndexableProperty {
    public enum IndexMode {
        index,
        facet
    }
    public IndexMode mode() default IndexMode.index;
}
