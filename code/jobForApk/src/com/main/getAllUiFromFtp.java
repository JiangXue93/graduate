package com.main;

import com.utl.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;


public class getAllUiFromFtp {
	
	public static void getAppPathAndName(ArrayList list){
		File file = new File("D:/jx/appname.txt");
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

            		tempString=tempString.trim();	
            		if(tempString.length()>0 ){//设置前几个&& line<10
    					System.out.println(tempString);
    					String [] arr = tempString.split("\\s+");
    					String[] result = {arr[0],arr[1],arr[2]};
    					
    					list.add(result);
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
	}
	//删除文件夹
	//param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	        System.out.print("OUT: 删除成功-"+folderPath);
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	//删除指定文件夹下所有文件
	//param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ftpHost = "192.168.1.184";
        String ftpUserName = "taseCommon";
        String ftpPassword = "password";
        int ftpPort = 21;
        String ftpPath = "0d";
        String ftpfilename = "a.a.hikidashi-9.apk";
        String filename = "a.a.hikidashi9.apk";
        String localPath = "D:/jx/apk/";
        String localUiPath = "D:/jx/ui/";
        String words = "";
        FTPClient ftpClient = new FTPClient();
        downFromFTP dff = new downFromFTP();
        ftpClient = dff.getFTPClient(ftpHost,ftpUserName,ftpPassword,ftpPort);
		//1.读取27w应用的名称
		File file = new File("D:/jx/appname.txt");
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
            		
            		tempString=tempString.trim();
            		
            		if(tempString.length()>0){//&& line<10设置前几个
    					System.out.println(tempString);
    					String [] arr = tempString.split("\\s+");//0d	a.a.hikidashi-9.apk	a.a.hikidashi.apk
    					ftpPath = arr[0];
    					ftpfilename = arr[1];
    					filename = arr[2];//xxxx.apk
    					
    					//2.根据名称下载apk
    					System.out.println("OUT: 开始下载");
    					dff.downFromFTP(ftpClient,ftpPath,localPath,ftpfilename,filename);
    					
    					
    					//3.解压apk获得UI文本word
    					String desPath = localPath+filename.substring(0,filename.length() - 4); //"C:/Users/jiangxue/Desktop/apktool/wifi";
    					String apkPath = localPath+filename;
//    					System.out.println(desPath);
//    					System.out.println(apkPath);
    					unzip uz = new unzip();
    					try {
    						uz.unzip(apkPath,desPath);
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					
    		    		//4.存储word
    					uiString us = new uiString();
    					words = "";
    					words = us.uiString(desPath);
    					System.out.println("words:"+words);
    					saveStr2File ss2f = new saveStr2File();
    					ss2f.saveStr2File(localUiPath, filename.substring(0,filename.length() - 4), words);
    					System.out.println("OUT: 存储"+ filename +"UI文本到"+ localUiPath + "成功");
    		    		//5.删除apk及反编译文件夹
    					delFolder(apkPath);
    					delFolder(desPath);

    				}
            	}
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            	System.out.println("\n#########已经解析 "+line+" 个应用#########"+df.format(new Date()));
                line++;
            }
            reader.close();
            
            System.out.println("【结束】");
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
	}

}
