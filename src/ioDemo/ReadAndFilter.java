package ioDemo;
import java.io.*;

public class ReadAndFilter {
  public static final String[] CHARSET_NAMES=new String[]{"ISO8859-1","GBK","UTF-8"};
	
	public static void main (String[] args){
		 
		// File myfile=new File("D:\\FileOpDemo\\word.txt");
		try {
			
			 //乱码字符串
			  String str="寰蒋鐧惧害鍏辨帹Windows XP鑱斿悎闃叉姢瑙ｅ喅鏂规";
			  int strLength=Integer.MAX_VALUE; //字符长度
			  String newStr="";     //从乱码字符串分析出的字符串
			  String srcCharSet="";    //当前乱码字符串编码
			  String targetCharSet="";   //乱码字符串正确的编码
			  //遍历可能的编码组合，从中造成编码长度最小的编码格式
			  for(int i=0;i<CHARSET_NAMES.length;i++){
			   for(int j=0;j<CHARSET_NAMES.length;j++){
			    String temp=new String(str.getBytes(CHARSET_NAMES[i]),CHARSET_NAMES[j]);
			    //System.out.println(temp);
			    if(temp.length()<=strLength){
			     strLength=temp.length();
			     newStr=temp;
			     srcCharSet=CHARSET_NAMES[i];
			     targetCharSet=CHARSET_NAMES[j];
			     
			    }
			    
			   }
			}
			  //输出查询到的编码及正确文本格式
			  System.out.println(srcCharSet+"-->"+targetCharSet+":"+newStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
