package socketDemo;
import java.net.*;
import java.io.*;
public class SockClient2 {
	public static void main(String args[]){
		 System.out.println("��ʼ����!");
	    	try{
	    		/*Socket��java�ж�Ӧһ���������ӵ�����ͨѶ���ơ�����TCPЭ��
	    		 * Socket��һ������ͬ���ӵ�����ͨѶ���࣬�䴦����̴���ǣ��ͻ���ͨ��д(OutputStream��Writer)�����˷�������
	    		 * �����ͨ����InputStream��Reader��ȡ���ݣ��������ͨ��д��ͻ��˷�������.
	    		 * 
	    		 * */
	   		 Socket s1=new Socket("127.0.0.1",5700);//�������ض������ض��˿ڵ�����
	   		BufferedReader breader=new BufferedReader(new InputStreamReader(System.in));//�Ӽ��̶�ȡ����
	   	    PrintWriter pwriter=new PrintWriter(s1.getOutputStream());
	   	   BufferedReader breaderIn=new BufferedReader(new InputStreamReader(s1.getInputStream()));//��socket��ȡ
	     	 String readline="";
	     	 readline=breader.readLine();//��ȡ���̵�ֵ
	     	 while(!readline.equals("bye")){
	     		 pwriter.println(readline);// ��ӡ�ַ���Ȼ����ֹ���С� 
				 pwriter.flush();//ˢ�¸����Ļ���,���������е��κλ������ݶ������Ŀ������ԴOutputStream
				 System.out.println("�ͻ��˳���,�ͻ��˵�����Ϊ:"+readline);
				 System.out.println("�ͻ��˳���,����˵���ϢΪ:"+breader.readLine());
				 readline=breaderIn.readLine();//������ȡ
	     	 }
	   		 
	     	breader.close();//�ر�������(��ȡ��)
			 pwriter.close();//�ر������(д)
			 s1.close();//�ر�Socket
	   		 
	   		 
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
