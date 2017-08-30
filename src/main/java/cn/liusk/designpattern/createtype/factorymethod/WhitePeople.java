/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.designpattern.createtype.factorymethod;

/**
 * 
 * @author liusk
 * @version $Id: WhitePeople.java, v 0.1 2017年8月29日 下午4:07:40 liusk Exp $
 */
public class WhitePeople extends People {

    public void say() {
        System.out.println("自我介绍:" + super.toString());
    }

}
