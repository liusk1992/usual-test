package cn.liusk.oldtest.smallprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("192.168.11.237",10000);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("�����ʽ��0 1,2 0");
		while(true){
			while(true){
				String line11 = br1.readLine();
				if("0000".equals(line11)){
					break;
				}
				if("1111".equals(line11))
					System.exit(0);
				System.out.println(line11);
			}
			String line = br.readLine();
			bw.write(line);
			bw.newLine();
			bw.flush();
			
			if("88".equals(line))
				break;
		}
		bw.close();
		br.close();
	}

}
