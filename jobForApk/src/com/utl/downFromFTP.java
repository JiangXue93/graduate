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
            System.out.println("û���ҵ�" + ftpDir + "�ļ�");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("����FTPʧ��.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�ļ���ȡ����");
            e.printStackTrace();
        }
	}
	/**
     * ��ȡFTPClient����
     *
     * @param ftpHost     FTP����������
     * @param ftpPassword FTP ��¼����
     * @param ftpUserName FTP��¼�û���
     * @param ftpPort     FTP�˿� Ĭ��Ϊ21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// ����FTP������
            ftpClient.login(ftpUserName, ftpPassword);// ��½FTP������
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("δ���ӵ�FTP���û������������");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP���ӳɹ���");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP��IP��ַ���ܴ�������ȷ���á�");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP�Ķ˿ڴ���,����ȷ���á�");
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
