/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.designpattern.createtype.factorymethod;

/**
 * 
 * @author liusk
 * @version $Id: NvWa.java, v 0.1 2017年8月30日 下午3:12:36 liusk Exp $
 */
public class NvWa implements God {

    /** 
     * @see cn.liusk.designpattern.factorymethod.God#createPeople()
     */
    @Override
    public People createPeople() {
        return new YellowPeople();
    }

}
