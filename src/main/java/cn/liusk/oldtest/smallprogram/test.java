package cn.liusk.oldtest.smallprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MyThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for(int i=0;i<10;i++){
				System.out.println(i+"--"+"1");
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}

class MyThread2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for(int i=0;i<10;i++){
				System.out.println(i+"--"+"2");
				Thread.sleep(4000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//stringCut("��aaa",6);
		//listTest();
		//stringTest();
		threadTest();
	}
	
	public static void threadTest(){
		
		MyThread myThread = new MyThread();
		MyThread2 myThread2 = new MyThread2();
		Thread thread1 = new Thread(myThread);
		Thread thread2 = new Thread(myThread2);
		thread1.start();
		thread2.start();
	}
	
	public static void stringTest(){
		String a = new String("abc");
		String b = new String("abc");
		System.out.println(a.equals(b));
	}
	
	public static void listTest(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("liusk", "liusk");
		map.put("admin", "admin");
		Map.Entry e = map.entrySet().iterator().next();
		System.out.println(e.getKey()+ e.getValue().toString());
		
	}
	
	public static void stringCut(String str,int num){
		byte[] bytes = str.getBytes();
		String str2 = "";
		if(bytes[num-1]<=127&&bytes[num-1]>=0)
			str2 = new String(bytes,0,num);
		else
			str2 = new String(bytes,0,num+1);
		
		System.out.println(str2);
	}

}
