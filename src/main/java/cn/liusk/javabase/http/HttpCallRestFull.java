/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.javabase.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http调用rest接口
 * @author liusk
 * @version $Id: HttpCallRestFull.java, v 0.1 2017年8月14日 下午8:12:53 liusk Exp $
 */
public class HttpCallRestFull {

    public static void main(String[] args) throws IOException {
        URL url = new URL(
            "http://221.178.187.38:18080/ucenter/api/user/list?token=63R3Abcg&uid=76");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

}
