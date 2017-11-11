package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.utl.saveStr2File;

public class desControl {

	/**
	 * @function 从sourcedir中读取文件名为des的文件，并对其中的文字做处理，处理完的结果放outdir中名为outname_outfoot.txt
	 */
	public static void main(String[] args) {
		String sourcedir = "D:/jx/preinput/";
		String sourcename = "des";
		String outdir = "D:/jx/input/";
		String outname = "xxxx";  //应用名字（对应apkname）
		String outfoot = "_des";
		String words = "";
		// TODO Auto-generated method stub
		//1.读文件
		File file = new File(sourcedir + sourcename + ".txt");
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	if(tempString.length()>0){
            		
            		tempString=tempString.toLowerCase();
            		
            		//去除URL
            		tempString = tempString.replaceAll("((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", "");
    				//去除数字标点符号
            		tempString = tempString.replaceAll("[^a-z_\\s]+","");
            		tempString=tempString.trim();
            		
            		if(tempString.length()>0){
    					if(words.length()>0){
	    					words = words+" "+tempString;
	    				}else{
	    					words = tempString;
	    				}
    				}
            	}
                
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println("OUT: 读取"+ sourcename +"并成功处理字符");
		//2.存储
		saveStr2File ss2f = new saveStr2File();
		ss2f.saveStr2File(outdir, outname+outfoot, words);
		System.out.println("OUT: 存储"+ outname+outfoot +"到"+ outdir + "成功");
	}

}
