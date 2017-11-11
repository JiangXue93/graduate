package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class resultConter {
	
	/*
	 * @param path ����·��
	 * @param result ���ڴ�ȡ��
	 */
	public static void getTopicContent(String path, Map map){
		File file = new File(path);
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
            		
            		if(tempString.length()>0 && line<10){
    					System.out.println(tempString);
    					String [] arr = tempString.split("\\s+");
    					String key=arr[1];
    					double[] temp = new double[arr.length-2];
    					
    					for(int i = 2; i<arr.length;i++){
    						temp[i-2] = Double.valueOf(arr[i]);
    					}
    					
    					map.put(key,temp);
    				}else if(line>=10){
    					break;
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
	
	/*
	 * @function ��ȡpath�µ�����txt�ĵ����� 
	 * @param path ����·��
	 */
	public static File[] getFileName(String path){
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return null;
		}
		
		File fa[] = f.listFiles();
		System.out.println(fa.length);
//		for (int i = 0; i < 10; i++) {
//			File fs = fa[i];
//			if (fs.isDirectory()) {
//				System.out.println(fs.getName() + " [Ŀ¼]");
//			} else {
//				System.out.println(fs.getName());
//			}
//		}
		return fa;
		
	}
	
	public static void getPermission(String path, Map<String, Map<Integer,Integer>> map){
		
		File fa[] = getFileName(path);
		for (int i = 0; i < 10000; i++) {
			if (fa[i].isDirectory()) {
				System.out.println(fa[i].getName() + " [Ŀ¼]");
			} else {
				String keyApp = fa[i].getName();
				Map<Integer,Integer> valApp = new HashMap<Integer,Integer>();
				BufferedReader reader = null;
				System.out.println(fa[i].getName());
				try {
					reader = new BufferedReader(new FileReader(fa[i]));
					String tempString = null;
					while((tempString = reader.readLine()) != null){
						String [] arr = tempString.split("\\s+");
						
						System.out.println(" file:"+arr[0]+" "+arr[1]);
						valApp.put( Integer.parseInt(arr[0]),  Integer.parseInt(arr[1]));
						
					}//һ��Ӧ���ļ���ȡ���
					
					map.put(keyApp,valApp);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1��ȡ27wӦ�õķ����������
		String appDir = "D:/jx/output/35/output-doc-topics.txt";
		Map<String, double[]> topicMap = new HashMap<String, double[]>();
		getTopicContent(appDir,topicMap);
		//2��ȡ27wӦ�õ�Ȩ��
		Map<String, Map<Integer,Integer>> permissionMap = new HashMap<String, Map<Integer,Integer>>();
		getPermission("D:/jx/api", permissionMap);
		
		//3����ÿһ���Ȩ���ܺ�
		
//		countVector()
//		for(String key : permission.keySet()){
//			System.out.println(" key:"+key);
//			System.out.println(" val:"+permission.get(key));
//			for(int p : permission.get(key).keySet()){
//				System.out.println("  val-key:"+p);
//				System.out.println("  val-val:"+permission.get(key).get(p));
//			}
//		}
	}
	

}
