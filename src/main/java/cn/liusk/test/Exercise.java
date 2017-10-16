/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author liusk
 * @version $Id: Exercise.java, v 0.1 2017年5月22日 上午10:08:02 liusk Exp $
 */
public class Exercise {
    public static void main(String[] args) {
        /*String str = "@";
        String[] strArray = str.split("@");
        System.out.println(strArray.length);
        String regex = "^[a-zA-Z0-9]+$";
        
        System.out.println("liuskq`".matches(regex));
        
        String s = "null@null,null@null";
        String[] sArr = s.split("null@null");
        System.out.println(sArr);
        
        StringBuilder sb = new StringBuilder();
        //sb.append(" ");
        String error = new String(sb);
        System.out.println(error == null);
        
        Random rand = new Random();
        int x = rand.nextInt(5);
        System.out.println(x);*/
        SortedMap<Object, Object> sortMap = new TreeMap<Object, Object>();
        sortMap.put("a", 111);
        sortMap.put("c", 333);
        sortMap.put("b", 222);

        for (Object key : sortMap.keySet()) {
            System.out.println(key.toString());
        }
        test3();
    }

    public static void test2() {
        String rclt = "";
        int pNum = 219, cNum = 1;
        double d = (double) pNum / (double) cNum;

        if (d == 0.0) {
            rclt = "0/1";
        } else {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
            rclt = df.format(d);
            if (rclt.endsWith(".0")) {
                rclt = rclt.replace(".0", "");
            }
            rclt = rclt + "/1";
        }
        System.out.println(rclt);
    }

    public static void test3() {
        StringBuilder beforeEncrypt = new StringBuilder("cab4e64f4ea2a0a993bce44febf1d8f5");
        String now = new Date().getTime() + "";
        beforeEncrypt.append("&" + now);
        String afterEncrypt = MD5.GetMD5Code(beforeEncrypt.toString());
        System.out.println(now);
        System.out.println(afterEncrypt);
    }
}
