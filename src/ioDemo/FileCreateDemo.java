package ioDemo;
import java.io.*;

public class FileCreateDemo {
   
    
    public static  void main(String[] args) {
    	 File myfile=new File("D:\\FileOpDemo","myTest.txt");
    	  if(myfile.exists()){
    		  myfile.delete();
    		  System.out.print("�ļ��Ѿ�ɾ����");
    	  }else {
			try {
				
				myfile.createNewFile();
				System.out.print("�ļ��Ѿ�������!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
}
