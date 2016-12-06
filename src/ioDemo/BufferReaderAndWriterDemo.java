package ioDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferReaderAndWriterDemo {


	public static void main(String[] args) {
		String content[]={"数组第一个元素","数组第二个元素","数组第三个元素"};
		File myfile=new File("D:/FileOpDemo/buffer.txt");
		try {
			
			FileWriter fWriter=new FileWriter(myfile);
			BufferedWriter bfw=new BufferedWriter(fWriter);
			for(int k=0;k<content.length;k++){
				bfw.write(content[k]);
				bfw.newLine();//写入一个行分隔符。
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
			//一行行读取信息
			while ((str=bReader.readLine())!=null) {
				i++;
				System.out.println("第"+i+"行的信息为:"+str);
				
			}
			fReader.close();
			bReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
