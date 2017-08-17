/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.javabase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Liusk {
    public enum PermissionType {
        ADD, UPDATE, DELETE, QUERY
    };

    PermissionType[]value() default PermissionType.ADD;
}
