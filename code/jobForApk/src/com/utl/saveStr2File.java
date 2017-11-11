package com.utl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class saveStr2File {
	
	public static void saveStr2File(String dir,String filename, String words){
		FileWriter fileWriter;
		if(!filename.endsWith(".txt")){
			filename = filename+".txt";
		}
		
		File file = new File(dir,filename);
		
		if (!file.exists()) {  
	        try {  
	            file.createNewFile();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }
		
		//д����
		try {  
	        fileWriter = new FileWriter(file);  
	        //ʹ�û������Ȳ�ʹ�û�����Ч�����ã���Ϊÿ�˴��̲��������ڴ����Ҫ���Ѹ���ʱ�䡣  
	        //ͨ��BufferedWriter��FileWriter�����ӣ�BufferedWriter�����ݴ�һ�����ݣ�Ȼ����ʱ��ʵ��д�����  
	        //�����Ϳ��Լ��ٶԴ��̲����Ĵ����������Ҫǿ�ưѻ���������д��,ֻҪ����writer.flush();��������Ϳ���Ҫ�󻺳������ϰ�����д��ȥ  
	        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);  
	        bufferedWriter.write(words);  
	        bufferedWriter.close();  
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
		String dir = "C:/Users/jiangxue/Desktop/apktool";
		String filename = "123";
		String words = "this is a test";
		saveStr2File(dir,filename,words);
	}

}
