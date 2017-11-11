package com.data.count;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class appInTopic {
	/*
	 * 计算每个主题应用个数
	 */
	public static void appInTopic(String path, Map<Integer, Integer> topic, float seperate){
		BufferedReader reader = null;
		Map<Integer,Integer> appToTopicCount = new HashMap<Integer,Integer>();//一个应用分到主题个数的统计分布
		try {
			reader = new BufferedReader(new FileReader(path));
			String tempString = null;
			int line = 1;
	        // 一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null) {
	        	String [] arr = tempString.split("\\s+");
//	        	System.out.println("#########"+arr[1]);
	        	int topicCount = 0;//应用大于阈值seperate的主题个数
	        	for(int i = 0; i < arr.length-2; i++){
	        		if(Double.parseDouble(arr[i+2])>seperate){
	        			topicCount++;
//	        			System.out.println( Double.parseDouble(arr[i+2]));
//		        		System.out.println(Double.parseDouble(arr[i+2])>seperate);
	        			if(topic.containsKey(i)){
//	        				System.out.println("oldcount: "+topic.get(i));
	        				int oldcount = ((Integer) topic.get(i)) + 1;
//	        				System.out.println("update: "+oldcount);
	        				topic.put(i, oldcount);
	        			}else{
	        				topic.put(i,1);
	        			}
	        		}
	        	}
//	        	System.out.println("topicCount: "+topicCount);
	        	if(appToTopicCount.containsKey(topicCount)){
	        		int oldcount = ((Integer)appToTopicCount.get(topicCount)) + 1;
	        		appToTopicCount.put(topicCount, oldcount);
	        	}else{
	        		appToTopicCount.put(topicCount,1);
	        	}
//	        	System.out.println("map:"+appToTopicCount);
	        	line++;
	        }
	        int total = 0;
	        String fenbu = "";
	        int checkTotal = 0;
        	String fenbu2 = "";
	        for(Integer key : topic.keySet()){
	        	total += topic.get(key);
	        	fenbu = fenbu+" "+topic.get(key);
//	        	System.out.println( "topic "+key+" have "+topic.get(key));
	        }
	        for(Integer key : appToTopicCount.keySet()){
	        	
	        	checkTotal += appToTopicCount.get(key);
	        	fenbu2 = fenbu2+" "+appToTopicCount.get(key);
	        	System.out.println( " "+key+":"+appToTopicCount.get(key));
	        }
	        System.out.println("主题分布: "+fenbu);
	        System.out.println("应用分到主题个数: "+total);
	        System.out.println("应用依概率分到主题个数的分布0-35: "+fenbu2);
	        System.out.println("应用分到主题个数: "+checkTotal);
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path= "D:/jx/output/35/output-doc-topics.txt";
		Map<Integer,Integer> topic = new HashMap<Integer,Integer>();
		float seperate = (float) 0.6;
		appInTopic(path,topic,seperate);
	}

}
