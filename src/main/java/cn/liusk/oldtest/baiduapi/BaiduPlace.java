package cn.liusk.oldtest.baiduapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaiduPlace {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println(getResult());
    }

    public static String resolveJson(String jsonStr) {
        String resolveStr = "";
        return resolveStr;
    }

    public static String getResult() throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("query", "��Ӱ");
        map.put("scope", "1");
        map.put("region", "֣��");
        map.put("output", "json");
        map.put("ak", Constant.baiduAK);
        String urlStr = installUrl(Constant.baiduPlaceSearch, map);
        System.out.println(urlStr);

        URL url = new URL(urlStr);
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.connect();
        InputStream is = httpUrlConnection.getInputStream();
        Reader reader = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static String installUrl(String url, Map<String, String> params) {
        StringBuilder paramsString = new StringBuilder();
        Iterator<Map.Entry<String, String>> paramsIterator = params.entrySet().iterator();
        while (paramsIterator.hasNext()) {

            Map.Entry<String, String> entry = paramsIterator.next();
            paramsString.append(entry.getKey());
            paramsString.append("=");
            paramsString.append(entry.getValue());
            paramsString.append("&");
        }

        return url + "?" + paramsString.toString();
    }
}
