package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 * ����˴���������
 */
public class TalkWorker implements Runnable{
  public String name;
  public BufferedReader breader;//��ȡ��
  public PrintWriter pwriter;//�����
  public static String content;
   public TalkWorker(Socket s){
	   try{
		   InputStream istream=s.getInputStream();
		   OutputStream os=s.getOutputStream();
		   this.breader=new BufferedReader(new InputStreamReader(istream));//�ֽڵ������ַ����ַ�������װ����,��������Ϊ��ȡ���ݵ�����
		   this.pwriter=new PrintWriter(os,true);
		   
	   }catch(Exception e){
	 	  
	   }
   }
   
   public void run(){
	   while(true){
		   try{
			   String temp=this.breader.readLine();
			   if(temp.equals("01")){
				   this.pwriter.println("�յ�01ָ��,�������û���!");
				   this.name=this.breader.readLine();
				   this.pwriter.println("���յ��û���OK01!");
			   }else if(temp.equals("02")){
				   this.pwriter.println("�յ�02ָ��,�������������!");
				   content=this.breader.readLine()+"/"+content;
				   this.pwriter.println("�����Ѿ���ȡ!ok02!");
			   }else if(temp.equals("03")){
				   this.pwriter.println("�յ�03ָ��,����Ϊ�����������!");
				   this.pwriter.println(content);
			   }else if(temp.equals("04")){
				   this.pwriter.println("�յ�04ָ��,��ʾ�û��Ѿ��뿪!");
				   content=this.name+"�뿪��"+"/"+content;
			   }else{
				   this.pwriter.println("FALSE!");
			   }
			   
		   }catch(Exception e){
			   break;
		   }
	   }
   }
  
}
