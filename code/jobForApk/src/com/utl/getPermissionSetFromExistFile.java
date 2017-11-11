package com.utl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

public class getPermissionSetFromExistFile {
	static String path = "D:/jx/api";
	public static void getPermissionSetFromExistFile(){
		Set set = new HashSet();
		File f = new File(path);
		
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return;
		}
		File fa[] = f.listFiles();
		System.out.println(fa.length);
		for (int i = 0; i < fa.length; i++) {//fa.length
			File fs = fa[i];
			BufferedReader reader = null;
			
			if (fs.isDirectory()) {
				System.out.println(fs.getName() + " [目录]");
			} else{
				try {
					reader = new BufferedReader(new FileReader(fa[i]));
					String tempString = null;
					while((tempString = reader.readLine()) != null){
						String [] arr = tempString.split("\\s+");
						System.out.println(" file:"+fa[i].getName());
						System.out.println(" file:"+arr[0]+" "+arr[1]);
						set.add(arr[0]);
					}//end of a file content
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}//end of each file
		System.out.println(set.size());
		System.out.println(set);
	}
	/*
	 * 应用权限-id对应表存储到指定位置
	 */
	public static void getJsonPermission(Set set){
		String dir = "D:/jx/apidict.json";
		BufferedReader reader = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("D:/jx/midOutput/miniPermit.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			reader = new BufferedReader(new FileReader(dir));
			String tempString = null;
	        int line = 1;
	        // 一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null) {
	        	JSONObject jsonObj = JSONObject.fromObject(tempString);//(JSONObject)jParser.parse("楼主的字符串");
//	    		Object obj = jsonObj.get ("weatherinfo");
//	    		JSONObject subObj = (JSONObject)obj ;
	        	Integer id= (Integer)jsonObj.get("value");
	        	String permit = (String)jsonObj.get("key");
	    		System.out.println(id+" "+permit);
	    		
	    		//写入到miniPermit
	    		if(set.contains(id)){
	    			System.out.println(id+" "+permit);
	    			fw.write(id+" "+permit+"\r\n");
	    			System.out.println(line);
	    		}
	    		//fw.write(id+" "+permit+"\r\n");
	    		line++;
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		getPermissionSetFromExistFile();
		//Map<Integer,String> allPermit= new HashMap<Integer,String>();
		Set set = new HashSet();
		//读取文本筛选后的api的id
		int[] miniPermit = {3089, 3088, 3087, 15628, 3081, 15625, 3866, 154, 152, 15429, 150, 47014, 15427, 151, 15424, 2953, 15425, 15423, 2954, 2854, 46996, 2956, 2850, 46994, 3099, 14676, 14678, 15636, 3091, 3090, 15632, 937, 15634, 15630, 2849, 2944, 2848, 2847, 2942, 19619, 2846, 2844, 2843, 19481, 2949, 3598, 12456, 3597, 12459, 47131, 12455, 19620, 19621, 12703, 15401, 12974, 15408, 12978, 12977, 12468, 15410, 12981, 15411, 12980, 15414, 2961, 12989, 12462, 9600, 116, 843, 117, 9602, 9601, 115, 112, 113, 15394, 110, 15395, 13000, 13001, 10389, 19507, 12891, 12890, 10386, 19501, 13007, 9599, 9597, 2893, 9598, 2244, 12897, 9596, 15662, 15660, 19300, 3103, 12250, 12252, 15387, 12268, 125, 3101, 15388, 3618, 15385, 15386, 3619, 9610, 15383, 122, 15384, 124, 15382, 2892, 13010, 837, 2890, 13011, 3611, 129, 10436, 19510, 2888, 19511, 2889, 10664, 13014, 2884, 2885, 13013, 10662, 10660, 9609, 9608, 12263, 12264, 9606, 15389, 11624, 9604, 3706, 134, 138, 12277, 139, 3605, 12278, 137, 3602, 3600, 27142, 12496, 12499, 46735, 27140, 12492, 2871, 2872, 12494, 12495, 2875, 11612, 12490, 11611, 2879, 12274, 130, 15653, 143, 9328, 15654, 27150, 15656, 15658, 12287, 17237, 856, 9734, 855, 27152, 27151, 859, 27154, 27153, 1203, 17231, 2866, 17236, 1202, 17235, 12285, 9332, 12286, 10455, 12283, 12284, 12281, 1207, 142, 22323, 26087, 26088, 3875, 3873, 10917, 9748, 27135, 54913, 12908, 12909, 9651, 9653, 12921, 19294, 19296, 3205, 46925, 12928, 12927, 19542, 12926, 12925, 12923, 47186, 12511, 12919, 12517, 3141, 3140, 10678, 9626, 9625, 9627, 9769, 2266, 10671, 2269, 11653, 11654, 2270, 9773, 9774, 9775, 2273, 24341, 13022, 3134, 12508, 3133, 11647, 15692, 12807, 9619, 12806, 15695, 15694, 10682, 12902, 12901, 10419, 11641, 10418, 12907, 10417, 12905, 12904, 9623, 9621, 9622, 12959, 1571, 12955, 12957, 12958, 12812, 19457, 3782, 19458, 12813, 2910, 7122, 12950, 3779, 9790, 3777, 9792, 12421, 10390, 1567, 3630, 1568, 3637, 3634, 3635, 2901, 2903, 12968, 2907, 12967, 12960, 15479, 12961, 1940, 46822, 9812, 9781, 15476, 15471, 12415, 19449, 12412, 12418, 47143, 47142, 3587, 12830, 12831, 12832, 12833, 24584, 12938, 14453, 19473, 14450, 12834, 12930, 11596, 15448, 12931, 11592, 12445, 12442, 12443, 3076, 12946, 26094, 12945, 12942, 12941, 3771, 15453, 15451, 12430, 47163};
		for(int i=0; i< miniPermit.length;i++){
			set.add(miniPermit[i]);
		}
//		System.out.println(set);
//		System.out.println(set.contains(1));
		getJsonPermission(set);
		
	}

}
