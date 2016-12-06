package socketDemo;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer2 {
	 public static void main(String args[]){
		 System.out.println("�����������!");
		 try{
			 
			 ServerSocket s=new ServerSocket(5700);//��������൱��һ��������,����5700�˿�	
			 Socket s1=s.accept();
			 String strline="";
			 /*BufferedReader �൱��Socket�����ȡ���ݵĹ�����
			  * Socket���Ƚ��յ�������Ϣ,Ȼ��getInputStream()�õ��ֽ���Ϣ,InputStream�����Socket�ж�ȡ�ֽ�������
			  * InputStreamReader�������ֽ���Ϣ��װΪ�ַ�������,�����ݸ�BufferedReader������,
			  * BufferedReader���������𽫵����ַ���Ϣ��װ���ַ�����Ϣ
			  *  �乹�캯����Ĳ����Ƕ�ȡ���ݵĵ�ַ
			  * */
			 BufferedReader breader=new BufferedReader(new InputStreamReader(s1.getInputStream()));//��ȡ���ݶ���
			 /*
			  * PrintWriter�൱��Socket���������Ϣ�Ĺ�����,ͨ��PrintWriter�����ַ�������ת��Ϊ�ֽ�����,Ȼ��ͨ��
			  * Socket�����OutputStream�������Socket��,��һ��ͨ��InputStream��ȡ
			  * �乹�캯����Ĳ�����Ŀ������Դ��Ҳ���ǽ�Ҫд��ȥ��Ŀ�ĵ�
			  * */
			 PrintWriter pwriter=new PrintWriter(s1.getOutputStream());//�õ������(д),Socket����緢������
			 BufferedReader breaderIn=new BufferedReader(new InputStreamReader(System.in));//��׼��������ö�ȡ����
			 System.out.println("����˳���,�ͻ��˵���ϢΪ:"+breader.readLine());
			 strline=breaderIn.readLine();//�Ӽ��̶�ȡ����
			 
			 while(!strline.equals("bye")){
				 pwriter.println(strline);// ��ӡ�ַ���Ȼ����ֹ���С� 
				 pwriter.flush();//ˢ�¸����Ļ���,���������е��κλ������ݶ������Ŀ������ԴOutputStream
				 System.out.println("����˳���,����˵�����Ϊ:"+strline);
				 System.out.println("����˳���,�ͻ��˵���ϢΪ:"+breader.readLine());
				 strline=breaderIn.readLine();//������ȡ
			 }
			 breader.close();//�ر�������(��ȡ��)
			 pwriter.close();//�ر������(д)
			 s1.close();//�ر�Socket
			 s.close();//�رռ�����
			 
			 	
			 
		 }catch(Exception e){
			 System.out.println(e);
		 }
		 
	 }
 
}
