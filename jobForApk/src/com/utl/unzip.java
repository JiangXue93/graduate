package com.utl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class unzip {
	/**
	 * @param apkPath The fullpath of the apk
	 * @param desPath The unzip out put file directory
	 */
	public static void unzip(String apkPath, String desPath) throws IOException{
		String path ="C:/Users/jiangxue/Desktop/apktool/"; //这是apktool所存放的路径
		Runtime rt = Runtime.getRuntime();
		String order = path + "apktool.bat" + " d " + apkPath + " -o " + desPath;
		System.out.println("OUT: 开始执行命令-" + order);
		Process pro = Runtime.getRuntime().exec(order);
		try {
			BufferedReader output = new BufferedReader(new InputStreamReader(pro.getInputStream())); 
			String line;  
			while ((line = output.readLine()) != null) { 
		        System.out.println("unzip:"+line);  
		      }
			//pro.waitFor();//等待结束
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pro.destroy();
		}        
		 System.out.println("OUT: 反编译结束-" + apkPath);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String apkPath = "C:/Users/jiangxue/Desktop/apktool/wifi.apk";
		String desPath = "C:/Users/jiangxue/Desktop/apktool/wifi";
		// TODO Auto-generated method stub
		
		try {
			unzip(apkPath,desPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
