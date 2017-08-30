/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.designpattern.createtype.factorymethod;

/**
 * 
 * @author liusk
 * @version $Id: FactoryTest.java, v 0.1 2017年8月30日 下午3:17:39 liusk Exp $
 */
public class FactoryTest {

    public static void main(String[] args) {
        God nv = new NvWa();
        God yx = new YaDangXiaWa();
        People p1 = nv.createPeople();
        People p2 = yx.createPeople();
        p1.setAge("23");
        p1.setName("郭嘉");
        p1.setSex("男");
        p1.say();

        p2.setAge("32");
        p2.setName("曹操");
        p2.setSex("男");
        p2.say();
    }

}
