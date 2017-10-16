package cn.liusk.oldtest.chessboard;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StartSocket {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ServerSocket ss = new ServerSocket(10001);

        testThread tt1 = new testThread(ss);
        testThread tt2 = new testThread(ss);
        Thread t1 = new Thread(tt1);
        Thread t2 = new Thread(tt2);
        t1.start();
        t2.start();
    }

}

class testThread implements Runnable {
    private ServerSocket ss;
    private static int   num = 0;

    testThread() {

    }

    public testThread(ServerSocket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        try {
            Socket s = ss.accept();
            System.out.println(s.getInetAddress().getHostName());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            num++;

            if (num > 2) {
                System.out.println("连接数达到上限，无法释放连接");
                bw.write("连接数已达到上限，无法获取连接");
                bw.newLine();
                bw.flush();
                bw.write("0000");
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(111);
    }

}
