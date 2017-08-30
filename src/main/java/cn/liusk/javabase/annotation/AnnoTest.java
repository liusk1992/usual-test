/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.javabase.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.liusk.javabase.annotation.Liusk.PermissionType;

/**
 * 
 * @author liusk
 * @version $Id: AnnoTest.java, v 0.1 2017年8月17日 下午5:42:19 liusk Exp $
 */
public class AnnoTest {

    @Liusk(value = { PermissionType.ADD, PermissionType.UPDATE })
    public void testAnno() {

    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        Class<AnnoTest> clazz = AnnoTest.class;
        Method method = clazz.getMethod("testAnno", null);

        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println(clazz.isAnnotation());

        //Method和Class都有isAnnotationPresent方法，这个方法可以判断这个Method和Class上是否有对应的注解
        System.out.println(method.isAnnotationPresent(Liusk.class));

        Liusk l = method.getAnnotation(Liusk.class);
        PermissionType[] pts = l.value();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(pts[0].toString(), 1);
        System.out.println(pts[0].toString());
    }

}
