/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author liusk
 * @version $Id: Test.java, v 0.1 2017年6月13日 下午2:35:47 liusk Exp $
 */
public class JDBCDemo {

    /**
     * 
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        //timer();
        insertData();
    }

    public static void insertData() throws SQLException {
        DBHelper db = new DBHelper("jdbc:mysql://192.168.95.70:3306/chigoo", "root", "beyondsoft");
        Connection conn = db.getConn();
        PreparedStatement ps = null;

        String sql = "insert into PVG_COUNT_SOURCEDATA "
                     + " (AirPortCode, TimePeriod, CMD, MAC, FlightNo, POST, PASSENGER, PARAMS,PROCESS,CountDate) "
                     + "values ('PVG','2016-06-14 10:00:00','PlayAdv','cc:d2:9b:5d:db:52','CZ3523','登机口2#位置1#区域1#1_2_3','p121025#1#2','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','cc:d2:9b:5d:db:52','CZ9869','登机口2#位置1#区域1#1_2_3','p121034#0#1','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','dd:d2:9b:5d:db:52','CZ9224','登机口2#位置1#区域1#1_2_3','p121015#1#1','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','ee:d2:9b:5d:db:52','G54295','登机口2#位置1#区域1#1_2_3','p121098#0#2','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','mm:d2:9b:5d:db:52','CZ9315','登机口2#位置1#区域1#1_2_3','p121067#1#3','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','nn:d2:9b:5d:db:52','CZ3523','登机口2#位置1#区域1#1_2_3','p121095#1#5','61000001_71000002@5@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','ff:d2:9b:5d:db:52','MF4603','登机口2#位置1#区域1#1_2_3','p121033#1#4','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','aa:d2:9b:5d:db:52','MU3041','登机口2#位置1#区域1#1_2_3','p121055#0#4','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','bb:d2:9b:5d:db:52','MF4567','登机口2#位置1#区域1#1_2_3','p121077#1#3','61000001_71000002@9@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','cc:d2:9b:5d:db:52','MF1975','登机口2#位置1#区域1#1_2_3','p121071#1#2','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','cc:d2:9b:5d:db:52','HU7848','登机口2#位置1#区域1#1_2_3','p121068#1#2','61000001_71000002@10@@false@false','F','2017-06-14'),"
                     + " ('PVG','2016-06-14 10:00:00','PlayAdv','cc:d2:9b:5d:db:52','G55334','登机口2#位置1#区域1#1_2_3','p121099#0#4','61000001_71000002@10@@false@false','F','2017-06-14')";

        ExecutorService pool = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            pool.submit(new InsertThread(conn, ps, sql));
        }

        pool.shutdown();
    }

    public static void timer() {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                try {
                    insertData();
                } catch (SQLException e) {

                }
            }
        };
        long delay = 0;
        long intevalPeriod = 60 * 1000 * 10;
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }

}

class InsertThread implements Runnable {

    private Connection        conn = null;
    private PreparedStatement ps   = null;
    private String            sql  = "";

    public InsertThread(Connection conn, PreparedStatement ps, String sql) {
        this.conn = conn;
        this.ps = ps;
        this.sql = sql;
    }

    /** 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
            try {
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            } catch (SQLException e) {
            }
            System.out.println("--" + i + "--");
            /*sql = sql.replace("pid", "33" + i);
            try {
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("--" + i + "--");*/
        }
    }

}
