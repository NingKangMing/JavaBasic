package socketDemo;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
/**
 * 
 * @author NingKangMing
 * @content
 *   ServerSocket����ˣ�ͬ������Socket�����ж�д������.ServerSocket�������
 *
 */
public class SocketServer1 {
	 public static void main(String args[]){
		 System.out.println("��������!");
		 ServerSocket s=null;
			try{
			s=new ServerSocket(666);//��������൱��һ��������,����666�˿�	
			}catch(IOException e){}
		while(true){//ͨ��һ��ѭ������ͣ�������Զ˿�666������
		  try{
			/*accept��һ��ͬ���������ȴ���������������������������һ��Socket����͸�����ͨ�ţ��������ִ�У��������������
			 * ���������ѭ����������ִ��
			 */
			  Socket s1=s.accept();
			  OutputStream os=s1.getOutputStream();//��Socket�����ϻ�����������
			  DataOutputStream dos=new DataOutputStream(os);//���(д)������,�Ѹ�������ת��Ϊ�ֽ�����
			  dos.writeUTF("���Ƿ����Socket������ͻ��������(д)����!���ȡ����?");
			  dos.close();
			  s1.close();//�ر�����
			  
			  
		  }catch(IOException e){
			  System.out.println(e);
		  }	
			
			
			
			
			
			
			
			
			
		}	
			
	 }
	
}
