/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.frame.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 
 * @author liusk
 * @version $Id: JedisTest.java, v 0.1 2017年6月19日 下午3:10:20 liusk Exp $
 */
public class JedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        //jedis.auth("");
        //jedis.set("name", "liushaokang");
        //jedis.lpush("list", new String[] { "111", "222", "333" });
        Transaction tran = jedis.multi();
        jedis.lpop("list");
        jedis.lpop("list");
        int i = 0;
        System.out.println(2 / i);
        tran.exec();
        System.out.println(jedis.get("name"));
    }

}
