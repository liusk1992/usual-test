/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author liusk
 * @version $Id: ListTest.java, v 0.1 2017年7月7日 上午11:35:32 liusk Exp $
 */
public class ListTest {

    public static void main(String[] args) {
        List<Double> list = new ArrayList<Double>();
        list.add(3.89987);
        list.add(3.89787);
        list.add(5.77);
        list.add(8.89787);
        list.add(4.8);
        list.add(3.23);

        Collections.sort(list);

        System.out.println(list.get(0));
        System.out.println(list.get(5));
    }

}
