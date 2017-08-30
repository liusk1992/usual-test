/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.designpattern.createtype.factorymethod;

/**
 * 亚当夏娃
 * @author liusk
 * @version $Id: YaDangXiaWa.java, v 0.1 2017年8月30日 下午3:12:56 liusk Exp $
 */
public class YaDangXiaWa implements God {

    /** 
     * 亚当夏娃造白人
     * @see cn.liusk.designpattern.factorymethod.God#createPeople()
     */
    @Override
    public People createPeople() {
        return new WhitePeople();
    }

}
