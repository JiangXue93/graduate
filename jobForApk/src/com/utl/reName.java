package com.utl;

import java.io.File;
/*
 * 将目录path下所有以.apk.txt结尾的文件名改为仅以.txt结尾
 */
public class reName {
	static String path = "D:/jx/ui";
	public static void reName(){
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return;
		}
		
		File fa[] = f.listFiles();
		System.out.println(fa.length);
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			
			String oldName = fs.getName();
			
			if (fs.isDirectory()) {
				System.out.println(fs.getName() + " [目录]");
			} else if(oldName.endsWith(".apk.txt")){
				String newName = oldName.substring(0, oldName.length()-8)+".txt";
				File newfile=new File(path+"/"+newName);
				if(newfile.exists()){
					fs.delete();
				}else{
					System.out.println(oldName);
					System.out.println(oldName.substring(0, oldName.length()-8)+".txt");
					fs.renameTo(newfile);
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reName();
	}

}
