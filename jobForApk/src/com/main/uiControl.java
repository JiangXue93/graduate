package com.main;
import java.io.IOException;

import com.utl.*;

public class uiControl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String basedir = "C:/Users/jiangxue/Desktop/apktool/";
		String filename = "kumapaw.oneesan"; //应用名字（对应apkname）
		String outdir = "D:/jx/input/";
		
		String desPath = basedir+filename; //"C:/Users/jiangxue/Desktop/apktool/wifi";
		String apkPath = basedir+filename+".apk";
		String words = "";
		// TODO Auto-generated method stub
		//1.解压
		unzip uz = new unzip();
		try {
			uz.unzip(apkPath,desPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.读取内容并预处理
		uiString us = new uiString();
		words = us.uiString(desPath);
//		System.out.println(words);
		//3.存储
		saveStr2File ss2f = new saveStr2File();
		ss2f.saveStr2File(outdir, filename, words);
		System.out.println("OUT: 存储"+ filename +"到"+ outdir + "成功");
		//4.删除文件
	}

}
