package cn.liusk.oldtest.smallprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxySimple {
	public ProxySimple(){
		
	}
	
	public void proPrint(){
		System.out.println("this is a proxy class");
	}
	public static void main(String[] args){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("aaa", 111);
		map1.put("bbb", 222);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("aaa", 1111);
		map2.put("bbb", 2222);
		list.add(map1);
		list.add(map2);
		for(Map<String,Object> m : list){
			System.out.println(m.keySet().toArray()[1]);
		}
		
	}
}
