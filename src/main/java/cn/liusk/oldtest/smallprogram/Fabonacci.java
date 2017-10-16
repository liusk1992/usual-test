package cn.liusk.oldtest.smallprogram;

public class Fabonacci {

    public static void main(String[] args) {
        /*简单直接输出斐波那契数列
        int no1 = 1;
        int no2 = 1;
        int no3;
        System.out.print(1+" "+1+" ");
        for(int i=0;i<10;i++){
            no3 = no1 + no2;
            System.out.print(no3+" ");
            no1 = no2;
            no2 = no3;
        }*/

        //递归输出斐波那契数列
        System.out.println(fn(6));
    }

    public static int fn(int len) {
        /*int m = 0;
        if(len>2){
        	m = fn(len-2)+fn(len-1);
        }
        else{
        	m = 1;
        }
        return m;*/

        if (len > 2)
            return fn(len - 2) + fn(len - 1);
        else
            return 1;
    }

}
