package socketDemo;
import java.net.*;
import java.io.*;
/**
 *@author NingKangMing
 *@Content socket�ͻ���,Socket�������ǽ��պͷ�������
 *
 */
public class SocketClient1 {
	 public static void main(String args[]){
		 System.out.println("��ʼ����!");
	    	try{
	    		/*Socket��java�ж�Ӧһ���������ӵ�����ͨѶ���ơ�����TCPЭ��
	    		 * Socket��һ������ͬ���ӵ�����ͨѶ���࣬�䴦����̴���ǣ��ͻ���ͨ��д(OutputStream��Writer)�����˷�������
	    		 * �����ͨ����InputStream��Reader��ȡ���ݣ��������ͨ��д��ͻ��˷�������.
	    		 * 
	    		 * */
	   		 Socket s1=new Socket("127.0.0.1",666);//�������ض������ض��˿ڵ�����
	   		 InputStream ins=s1.getInputStream();//��Socket�����ϻ�ȡ(��ȡ)����Socket�����͹���������
	   		 DataInputStream dis=new DataInputStream(ins);
	   	    //��InputStream���󴮽�һ��DataInputStream���󣬸ö����𽫷��͹������ֽ���Ϣ��װ�ɸ��ӵ����ݶ�����Ϣ
	   		 String strMsg=dis.readUTF();//��ȡһ���ַ���
	   		 System.out.println(strMsg);
	   		 System.out.println("����!�ҽ��յ���");
	   		 
	   		 dis.close();//�رն���
	   		 s1.close();
	   		
	   		 
	   	}catch(ConnectException conex){
	   		System.err.println("����������ʧ��!");
	   		conex.printStackTrace();
	   	}
	    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
}
