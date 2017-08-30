/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.designpattern.createtype.factorymethod;

/**
 * 
 * @author liusk
 * @version $Id: BlackPeople.java, v 0.1 2017年8月29日 下午4:05:47 liusk Exp $
 */
public class YellowPeople extends People {

    public void say() {
        System.out.println("自我介绍:" + super.toString());
    }

}
