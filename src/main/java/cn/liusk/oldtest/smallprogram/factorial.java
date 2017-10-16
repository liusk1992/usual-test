package cn.liusk.oldtest.smallprogram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//阶乘算法
public class factorial {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        line = br.readLine();
        int num = 0;
        try {
            num = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("输入数字" + line + "不规范");
            System.exit(0);
        }
        /*int sum = 1;
        System.out.print("4!=");
        for(int i=num;i>0;i--){
        	sum = sum*i;
        	System.out.print(i+"*");
        }
        System.out.println("="+sum);
        */

        System.out.println(numFactorial(num));
    }

    public static int numFactorial(int num) {
        if (num == 1)
            return 1;
        else
            return num * numFactorial(num - 1);
    }

}
