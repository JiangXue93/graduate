package com.utl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class uiString {
	static String words="";
	static Set stopList = new HashSet();
	/**
	 * @param dirPath The fullpath of the unzip directory
	 */
	public static String uiString(String dirPath){
		String dirFooter = "res/values/strings.xml";
		String filePath = "";
		words="";
		
		if(dirPath.endsWith("/")){
			filePath = dirPath + dirFooter;
		}else{
			filePath = dirPath +"/"+ dirFooter;
		}
		
		System.out.println("OUT: ��ʼ����xml-"+filePath);
		File f=new File(filePath);
		if(f.exists()){
			//	System.out.println(f.getAbsolutePath());
				 try {
					test1(f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("strings.xml������" );
				return "";
			}
//			System.out.println(words);
			return words.replaceAll("\\s+", " ");
		
	}
	  public static void test1(File file) throws Exception{
	    	 
	    	 //System.out.println("test1");
			
	    	 Element element = null;
			  // ����ʹ�þ���·��
			 
			  // documentBuilderΪ������ֱ��ʵ����(��XML�ļ�ת��ΪDOM�ļ�)
			  DocumentBuilder db = null;
			  DocumentBuilderFactory dbf = null;
			   // ����documentBuilderFactory����
			   dbf = DocumentBuilderFactory.newInstance();
			   // ����db������documentBuilderFatory�����÷���documentBuildr����
			   db = dbf.newDocumentBuilder();
			 
			   // �õ�һ��DOM�����ظ�document����
			   Document dt = db.parse(file);
			   // �õ�һ��elment��Ԫ��
			   element = dt.getDocumentElement();
			   // ��ø��ڵ�
			   // ��ø�Ԫ���µ��ӽڵ�
			   NodeList childNodes = element.getChildNodes();
			
			   //System.out.println("childNodes="+childNodes.getLength());
			   listNodes(childNodes);
			  	   	  
	   }
	  public static void listNodes(NodeList childNodes){
	    	//System.out.println("listNodes"+childNodes.getLength());
	    	   
	    	for(int i=0;i<childNodes.getLength();i++){
	    		Node node1=childNodes.item(i);  
	    		if(node1.hasChildNodes()){
	    			//  System.out.println("hi ����ѭ��");
	    			NodeList nodeDetail=node1.getChildNodes();
	    			listNodes(nodeDetail);
	    		}else{
	    			String s=node1.getTextContent();
	    			s=s.trim();
//					System.out.println("s="+s);
	    			if(!(s.isEmpty())){
//	    				System.out.println("s=if="+s);
//	    				for(int k=0;k<s.length();k++){
//	    					if((s.charAt(k)+"").getBytes().length>1){
//	    						words=words+s.charAt(k);
//	    					}
//	    					else words=words+" ";
//	    				}	
	    				//Сд
	    				s=s.toLowerCase();
	    				//ȥ��URL
	    				s=s.replaceAll("((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", "");
	    				//ȥ�����ֱ�����
	    				s=s.replaceAll("[^a-z-\\s]+","");
	    				//ȥ��ͣ�ô�
	    				s=removeStopWords(s);
	    				s=s.trim();
//	    				System.out.println("s-length:"+s.length());
//	    				System.out.println("s:"+s);
	    				if(s.length()>0){
	    					if(words.length()>0){
//	    						System.out.println("++++++++++s:"+s);	
		    					words = words+" "+s;
//		    					System.out.println("++++++++++words:"+words);
		    				}else{
		    					words = s;
		    				}
	    				}
	    			}
					  
	    		}
	    	}	   		
	  }
	  //��ȡͨ�ôʱ����ھ�̬set������
	  public static void getStopWord(){
		  String path = "D:/jx/output/stopwords.txt";
		  BufferedReader reader = null;
//		  Set stopList = new HashSet();
		  try {
			reader = new BufferedReader(new FileReader(path));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
//				System.out.println(tempString);
				stopList.add(tempString);
			}
		  }catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		return stopList;
	  }
	  //ȥ��ͣ�ôʣ�����Ϊһ���ÿո�ָ��ĵ����ַ���
	  //����ȥ�ʺ���ַ���
	  public static String removeStopWords(String line){
		  String newWords = "";
		  if(stopList.size()==0){
			  System.out.println("stopWordsList is empty,start to get stopwords...");
			  getStopWord();
		  }
		  String arr[]=line.split("\\s+");
		  System.out.println(arr.length);
		  for(int i = 0; i < arr.length; i++){
			  if(!stopList.contains(arr[i])){
				  newWords = newWords + " " + arr[i];
			  }
		  }
		  System.out.println(newWords.trim());
		  return newWords.trim();
		  
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String desPath = "C:/Users/jiangxue/Desktop/apktool/wifi/";
		String desPath = "C:/Users/jiangxue/Desktop/apktool/kumapaw.oneesan";
		System.out.println(uiString(desPath));
//		Set stopList = new HashSet();
//		getStopWord();
		System.out.println(stopList);
		removeStopWords("asd a a comes his its words");
	}

}
