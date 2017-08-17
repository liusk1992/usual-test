/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author liusk
 * @version $Id: DBHelper.java, v 0.1 2017年6月14日 下午2:54:21 liusk Exp $
 */
public class DBHelper {
    public String       url      = "jdbc:mysql://192.168.95.70:3306/chigoo";
    public final String name     = "com.mysql.jdbc.Driver";
    public String       user     = "root";
    public String       password = "beyondsoft";

    public Connection conn = null;

    public DBHelper(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConn() {
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
