/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liusk
 * @version $Id: MapTest.java, v 0.1 2017年7月6日 下午2:06:04 liusk Exp $
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "123");
        map.put("b", "345");

        Map<String, Object> newMap = new HashMap<String, Object>();
        newMap.put("c", "678");

        newMap.put("d", "9876");

        map.putAll(newMap);

        System.out.println(map.get("d"));
    }

}
