package ioDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferReaderAndWriterDemo {


	public static void main(String[] args) {
		String content[]={"�����һ��Ԫ��","����ڶ���Ԫ��","���������Ԫ��"};
		File myfile=new File("D:/FileOpDemo/buffer.txt");
		try {
			
			FileWriter fWriter=new FileWriter(myfile);
			BufferedWriter bfw=new BufferedWriter(fWriter);
			for(int k=0;k<content.length;k++){
				bfw.write(content[k]);
				bfw.newLine();//д��һ���зָ�����
			}
			bfw.close();
			fWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileReader fReader=new FileReader(myfile);
			BufferedReader bReader=new BufferedReader(fReader);
			String str=null;
			int i=0;
			//һ���ж�ȡ��Ϣ
			while ((str=bReader.readLine())!=null) {
				i++;
				System.out.println("��"+i+"�е���ϢΪ:"+str);
				
			}
			fReader.close();
			bReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
