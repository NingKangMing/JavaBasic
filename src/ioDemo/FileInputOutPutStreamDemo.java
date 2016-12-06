package ioDemo;
import java.io.*;
public class FileInputOutPutStreamDemo {
	
	
   public static void main(String[] args) {
	 File myfile=new File("D:\\FileOpDemo\\word.txt");
	
	 try {
		 FileWriter mywriter=new FileWriter(myfile,true);//从末尾处写入字符流
		 String strW="FileWriter,我是由FileWriter写进去的字符。";
		 mywriter.write(strW);
		 mywriter.close();
		 //long fileLen=myfile.length();
		FileOutputStream out=new FileOutputStream(myfile,true);//字节流
		byte wb[]="呵呵呵呵!FileOutputStream,我要用byte字节写信息进文本!".getBytes();
		out.write(wb);
		out.close();
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	 try {
		 
		 FileInputStream in=new FileInputStream(myfile);
		 byte b[]=new byte[1024];
		 int len=in.read(b);//把文本信息流的1024个byte(字节)读取到b里面,,一个汉字是两个字节,最多可读500多个汉字,所以实际上是用缓冲区要写入和读取
		System.out.println("FileInputStream,文本的信息为:"+new String(b,0,len));//输出指定信息
		in.close();
		
		FileReader myreade=new FileReader(myfile);
		char c[]=new char[200];//只能读取200个字符信息
		int lenChar=myreade.read(c);
		System.out.println("用FileReader读取:"+new String(c,0,lenChar));
		//System.out.println("返回字节数:"+myreade.read());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
   
   
}
