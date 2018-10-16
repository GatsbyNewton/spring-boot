package edu.wzm.tool;

import java.lang.annotation.*;

/**
 * @author Jimmy Wong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamAlias {

    String name();
}
