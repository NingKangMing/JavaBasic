package ioDemo;
import java.io.*;

public class FileCreateDemo {
   
    
    public static  void main(String[] args) {
    	 File myfile=new File("D:\\FileOpDemo","myTest.txt");
    	  if(myfile.exists()){
    		  myfile.delete();
    		  System.out.print("文件已经删除！");
    	  }else {
			try {
				
				myfile.createNewFile();
				System.out.print("文件已经创建！!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
}
