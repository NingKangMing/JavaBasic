package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *  �ͻ��˹����ߣ�����ͷ���˽���ͨѶ
 */
public class ClientWorker {
   private Socket st=null;//����ClientBooter���͹�����ͨѶʵ�����Ӷ���þ����ͨѶ�ص�
   private BufferedReader breader=null;//��ȡ���������ȡ����˷�������Ϣ
   private PrintWriter pwriter=null;//������������ַ�����Ϣ��װΪ�ֽ���Ϣ�������OutputStreamȻ�������Socket������
   private BufferedReader myreader=null;//��ȡ�ӴӼ��������ֵ
   /**
    * 
    * @param s
    * �������ͻ���Socket���󣬸ö�����ض��˿ڽ�������
    * �����캯�����շ���˷���������Ϣ���������˷�����Ϣ
    */
  public ClientWorker(Socket s){
	   this.st=s;
	   try{
     InputStream instream=this.st.getInputStream();//��ȡ��ȡ���ݵ�����
     this.breader=new BufferedReader(new InputStreamReader(instream));//��ȡ����˵���Ϣ
     OutputStream outStream=this.st.getOutputStream();//��ȡ�������Ҳ���ǻ�ȡ��Socketд���ݵ�����
	 this.pwriter=new PrintWriter(outStream);//���ַ�����Ϣ��װ���ֽ���Ϣ�����Ŀ�ĵ�outStream��outStream������д��Socket��
	 
	 InputStreamReader inreader=new InputStreamReader(System.in);//������ԴSystem.in�ϻ�ȡ�ֽ���Ϣ����װΪ�����ַ���Ϣ
	 this.myreader=new BufferedReader(inreader);//������Դinreader��ȡ�����ַ���Ϣ��װ���ֽڴ���Ϣ�����ڶ�ȡ�����������Ϣ
		    
	   }catch(Exception e){
		   System.out.println("����������ʧ�ܣ�");
		   e.printStackTrace();
	   }
   }
  /**
   * �����ͻ���
   */
  public void run(){
	  try{
		  System.out.println("�뷢������ָ�01��02��03��04.");
		  String strUserOrder=this.myreader.readLine();//��ȡ�û�����
		  while(!strUserOrder.equals("exit")){
			  this.pwriter.println(strUserOrder);
			  this.pwriter.flush();//���͵������
			  String strServerContent=this.breader.readLine();//��ȡ����˷���������Ϣ
			  String[] strarr=strServerContent.split("/");
			  for(int i=0;i<strarr.length;i++){
				  System.out.println(strarr[i]);
			  }
			  strUserOrder=this.myreader.readLine();//��ȡ�û�����
		  }
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}
