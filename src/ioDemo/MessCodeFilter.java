package ioDemo;
import java.io.*;
public class MessCodeFilter {
	public static final String[] CHARSET_NAMES=new String[]{"ISO8859-1","GBK","UTF-8"};

	public static void main (String[] args){
		String newStr="";
		File myfile=new File("E:\\�Ϸ���ѧJava��ѵ\\java�γ�¼��\\MedicineSystem\\myEclipse���ÿ�ݼ�.txt");
		try {

			//�����ַ���
			// String str="徽�百度共推Windows XP联合防护解决方案";
			FileReader myre=new FileReader(myfile);
			char[] chr=new char[20000];
			int len=myre.read(chr);
			String str=new String(chr,0,len);
			newStr=new String(str.getBytes("GBK"),"UTF-8");
			System.out.println("��ʼ��ȡ:"+newStr);
			myre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			//����ԭ������д���ı�
			FileWriter mywr=new FileWriter(myfile);
			mywr.write(newStr);
			mywr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


}
