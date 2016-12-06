package ioDemo;
import java.io.*;

public class ReadAndFilter {
  public static final String[] CHARSET_NAMES=new String[]{"ISO8859-1","GBK","UTF-8"};
	
	public static void main (String[] args){
		 
		// File myfile=new File("D:\\FileOpDemo\\word.txt");
		try {
			
			 //�����ַ���
			  String str="徽�百度共推Windows XP联合防护解决方案";
			  int strLength=Integer.MAX_VALUE; //�ַ�����
			  String newStr="";     //�������ַ������������ַ���
			  String srcCharSet="";    //��ǰ�����ַ�������
			  String targetCharSet="";   //�����ַ�����ȷ�ı���
			  //�������ܵı�����ϣ�������ɱ��볤����С�ı����ʽ
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
			  //�����ѯ���ı��뼰��ȷ�ı���ʽ
			  System.out.println(srcCharSet+"-->"+targetCharSet+":"+newStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
