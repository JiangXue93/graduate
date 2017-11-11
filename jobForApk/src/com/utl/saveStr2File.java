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
		
		//写内容
		try {  
	        fileWriter = new FileWriter(file);  
	        //使用缓冲区比不使用缓冲区效果更好，因为每趟磁盘操作都比内存操作要花费更多时间。  
	        //通过BufferedWriter和FileWriter的连接，BufferedWriter可以暂存一堆数据，然后到满时再实际写入磁盘  
	        //这样就可以减少对磁盘操作的次数。如果想要强制把缓冲区立即写入,只要调用writer.flush();这个方法就可以要求缓冲区马上把内容写下去  
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
