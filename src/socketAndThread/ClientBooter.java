package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *客户端引导类，负责建立Socket并委托给客户端ClientWorker对象
 */
public class ClientBooter {
	
	
  public static void main(String[] args){
	  System.out.println("请求开始!");
	  try{
		  Socket c=new Socket("127.0.0.1",9011);
		  ClientWorker myproxy=new ClientWorker(c);
		  myproxy.run();
	  }catch(Exception e){
		
	  }
  }
}
