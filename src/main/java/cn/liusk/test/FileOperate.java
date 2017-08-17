/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author liusk
 * @version $Id: FileOperate.java, v 0.1 2017年7月5日 下午5:37:08 liusk Exp $
 */
public class FileOperate {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        File file = null;
        FileInputStream fis = null;

        try {
            file = new File("G:/can_sourcedata_0704.sql");
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];

            int len = 0;
            int count = 0;
            while ((len = fis.read(buf)) != -1) {

                if (count == 100) {
                    break;
                }

                String str = new String(buf, 0, len);
                System.out.println(str);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test1() throws IOException {
        File file = new File("G:/error.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(123 + ",");
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
