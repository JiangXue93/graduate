package com.main;
import java.io.IOException;

import com.utl.*;

public class uiControl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String basedir = "C:/Users/jiangxue/Desktop/apktool/";
		String filename = "kumapaw.oneesan"; //Ӧ�����֣���Ӧapkname��
		String outdir = "D:/jx/input/";
		
		String desPath = basedir+filename; //"C:/Users/jiangxue/Desktop/apktool/wifi";
		String apkPath = basedir+filename+".apk";
		String words = "";
		// TODO Auto-generated method stub
		//1.��ѹ
		unzip uz = new unzip();
		try {
			uz.unzip(apkPath,desPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.��ȡ���ݲ�Ԥ����
		uiString us = new uiString();
		words = us.uiString(desPath);
//		System.out.println(words);
		//3.�洢
		saveStr2File ss2f = new saveStr2File();
		ss2f.saveStr2File(outdir, filename, words);
		System.out.println("OUT: �洢"+ filename +"��"+ outdir + "�ɹ�");
		//4.ɾ���ļ�
	}

}
