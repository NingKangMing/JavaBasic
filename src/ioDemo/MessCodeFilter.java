package ioDemo;
import java.io.*;
public class MessCodeFilter {
	public static final String[] CHARSET_NAMES=new String[]{"ISO8859-1","GBK","UTF-8"};

	public static void main (String[] args){
		String newStr="";
		File myfile=new File("E:\\南方科学Java集训\\java课程录像\\MedicineSystem\\myEclipse设置快捷键.txt");
		try {

			//乱码字符串
			// String str="寰蒋鐧惧害鍏辨帹Windows XP鑱斿悎闃叉姢瑙ｅ喅鏂规";
			FileReader myre=new FileReader(myfile);
			char[] chr=new char[20000];
			int len=myre.read(chr);
			String str=new String(chr,0,len);
			newStr=new String(str.getBytes("GBK"),"UTF-8");
			System.out.println("开始读取:"+newStr);
			myre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			//将还原的乱码写回文本
			FileWriter mywr=new FileWriter(myfile);
			mywr.write(newStr);
			mywr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


}
