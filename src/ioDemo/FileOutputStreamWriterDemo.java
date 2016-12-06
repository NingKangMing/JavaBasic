package ioDemo;
import java.io.*;
public class FileOutputStreamWriterDemo {
	public static void main(String[] args) {
		File myfile=new File("D:\\FileOpDemo\\writer.txt");

		try {
			
			FileOutputStream fOutputStream=new FileOutputStream(myfile);
			Writer outW= new BufferedWriter(new OutputStreamWriter(fOutputStream));	
			outW.write("����������д��ȥ");
			outW.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			FileInputStream in=new FileInputStream(myfile);
			BufferedReader myre=new BufferedReader(new InputStreamReader(in));
			System.out.println("��Reader��ȡ:"+myre.readLine());
			in.close();
			myre.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
