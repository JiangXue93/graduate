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
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
            	if(tempString.length()>0){

            		tempString=tempString.trim();	
            		if(tempString.length()>0 ){//����ǰ����&& line<10
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
	//ɾ���ļ���
	//param folderPath �ļ�����������·��
	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //ɾ����������������
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //ɾ�����ļ���
	        System.out.print("OUT: ɾ���ɹ�-"+folderPath);
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	//ɾ��ָ���ļ����������ļ�
	//param path �ļ�����������·��
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
	             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	             delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
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
		//1.��ȡ27wӦ�õ�����
		File file = new File("D:/jx/appname.txt");
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
            		
            		tempString=tempString.trim();
            		
            		if(tempString.length()>0){//&& line<10����ǰ����
    					System.out.println(tempString);
    					String [] arr = tempString.split("\\s+");//0d	a.a.hikidashi-9.apk	a.a.hikidashi.apk
    					ftpPath = arr[0];
    					ftpfilename = arr[1];
    					filename = arr[2];//xxxx.apk
    					
    					//2.������������apk
    					System.out.println("OUT: ��ʼ����");
    					dff.downFromFTP(ftpClient,ftpPath,localPath,ftpfilename,filename);
    					
    					
    					//3.��ѹapk���UI�ı�word
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
    					
    		    		//4.�洢word
    					uiString us = new uiString();
    					words = "";
    					words = us.uiString(desPath);
    					System.out.println("words:"+words);
    					saveStr2File ss2f = new saveStr2File();
    					ss2f.saveStr2File(localUiPath, filename.substring(0,filename.length() - 4), words);
    					System.out.println("OUT: �洢"+ filename +"UI�ı���"+ localUiPath + "�ɹ�");
    		    		//5.ɾ��apk���������ļ���
    					delFolder(apkPath);
    					delFolder(desPath);

    				}
            	}
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
            	System.out.println("\n#########�Ѿ����� "+line+" ��Ӧ��#########"+df.format(new Date()));
                line++;
            }
            reader.close();
            
            System.out.println("��������");
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
