package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *�ͻ��������࣬������Socket��ί�и��ͻ���ClientWorker����
 */
public class ClientBooter {
	
	
  public static void main(String[] args){
	  System.out.println("����ʼ!");
	  try{
		  Socket c=new Socket("127.0.0.1",9011);
		  ClientWorker myproxy=new ClientWorker(c);
		  myproxy.run();
	  }catch(Exception e){
		
	  }
  }
}
