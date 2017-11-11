package com.utl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
 

public class downFromFTP {
	static String ftpbase = "/analysis2/playdrone/apk/";
	public static void downFromFTP(FTPClient ftpClient,String ftpPath, String localPath,String ftpfilename,String fileName){
		String ftpDir = ftpbase + ftpPath;
		//System.out.println(ftpDir+ "/" +ftpfilename);
		
		try{
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpDir);
            
            File localFile = new File(localPath + "/" + fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(ftpDir+ "/" +ftpfilename, os);
            os.flush(); 
            os.close();

		} catch (FileNotFoundException e) {
            System.out.println("没有找到" + ftpDir + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }
	}
	/**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ftpHost = "192.168.1.184";
        String ftpUserName = "taseCommon";
        String ftpPassword = "password";
        String ftpPath = "0d";
        String filename = "12322222.apk";
        String ftpfilename = "a.a.hikidashi-9.apk";
        String localPath = "C:/Users/jiangxue/Desktop/apktool/";
        int ftpPort = 21;
        FTPClient ftpClient = new FTPClient();
        ftpClient = getFTPClient(ftpHost,ftpUserName,ftpPassword,ftpPort);
        System.out.println(ftpClient.isAvailable());
        downFromFTP(ftpClient,ftpPath,localPath,ftpfilename,filename);
        
	}

}
