package ioDemo;
import java.io.*;
public class FileInputOutPutStreamDemo {
	
	
   public static void main(String[] args) {
	 File myfile=new File("D:\\FileOpDemo\\word.txt");
	
	 try {
		 FileWriter mywriter=new FileWriter(myfile,true);//��ĩβ��д���ַ���
		 String strW="FileWriter,������FileWriterд��ȥ���ַ���";
		 mywriter.write(strW);
		 mywriter.close();
		 //long fileLen=myfile.length();
		FileOutputStream out=new FileOutputStream(myfile,true);//�ֽ���
		byte wb[]="�ǺǺǺ�!FileOutputStream,��Ҫ��byte�ֽ�д��Ϣ���ı�!".getBytes();
		out.write(wb);
		out.close();
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	 try {
		 
		 FileInputStream in=new FileInputStream(myfile);
		 byte b[]=new byte[1024];
		 int len=in.read(b);//���ı���Ϣ����1024��byte(�ֽ�)��ȡ��b����,,һ�������������ֽ�,���ɶ�500�������,����ʵ�������û�����Ҫд��Ͷ�ȡ
		System.out.println("FileInputStream,�ı�����ϢΪ:"+new String(b,0,len));//���ָ����Ϣ
		in.close();
		
		FileReader myreade=new FileReader(myfile);
		char c[]=new char[200];//ֻ�ܶ�ȡ200���ַ���Ϣ
		int lenChar=myreade.read(c);
		System.out.println("��FileReader��ȡ:"+new String(c,0,lenChar));
		//System.out.println("�����ֽ���:"+myreade.read());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
   
   
}
