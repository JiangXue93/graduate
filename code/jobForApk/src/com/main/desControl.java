package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.utl.saveStr2File;

public class desControl {

	/**
	 * @function ��sourcedir�ж�ȡ�ļ���Ϊdes���ļ����������е�����������������Ľ����outdir����Ϊoutname_outfoot.txt
	 */
	public static void main(String[] args) {
		String sourcedir = "D:/jx/preinput/";
		String sourcename = "des";
		String outdir = "D:/jx/input/";
		String outname = "xxxx";  //Ӧ�����֣���Ӧapkname��
		String outfoot = "_des";
		String words = "";
		// TODO Auto-generated method stub
		//1.���ļ�
		File file = new File(sourcedir + sourcename + ".txt");
        BufferedReader reader = null;
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
            	if(tempString.length()>0){
            		
            		tempString=tempString.toLowerCase();
            		
            		//ȥ��URL
            		tempString = tempString.replaceAll("((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", "");
    				//ȥ�����ֱ�����
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
        System.out.println("OUT: ��ȡ"+ sourcename +"���ɹ������ַ�");
		//2.�洢
		saveStr2File ss2f = new saveStr2File();
		ss2f.saveStr2File(outdir, outname+outfoot, words);
		System.out.println("OUT: �洢"+ outname+outfoot +"��"+ outdir + "�ɹ�");
	}

}
