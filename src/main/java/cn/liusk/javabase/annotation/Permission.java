/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.javabase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author liusk
 * @version $Id: Permission.java, v 0.1 2017年8月18日 下午3:42:05 liusk Exp $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Permission {

    /**
     * 权限类型枚举
     * @author liusk
     * @version $Id: Permission.java, v 0.1 2017年8月18日 下午3:48:07 liusk Exp $
     */
    public enum PermissionType {
        ADD, UPDATE, DELETE, QUERY
    };

    PermissionType[]value();
}
