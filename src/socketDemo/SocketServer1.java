package socketDemo;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
/**
 * 
 * @author NingKangMing
 * @content
 *   ServerSocket服务端，同样是用Socket来进行读写操作的.ServerSocket负责监听
 *
 */
public class SocketServer1 {
	 public static void main(String args[]){
		 System.out.println("开启服务!");
		 ServerSocket s=null;
			try{
			s=new ServerSocket(666);//服务对象，相当于一个监听器,监听666端口	
			}catch(IOException e){}
		while(true){//通过一死循环来不停接收外界对端口666的请求
		  try{
			/*accept是一个同步函数，等待外界的连接请求，如果有请求就生成一个Socket对象和该连接通信，程序继续执行，否则程序阻塞。
			 * 所以这里的循环不会连续执行
			 */
			  Socket s1=s.accept();
			  OutputStream os=s1.getOutputStream();//从Socket对象上获得数据输出流
			  DataOutputStream dos=new DataOutputStream(os);//输出(写)处理流,把各种数据转换为字节数据
			  dos.writeUTF("我是服务端Socket，我向客户端你输出(写)数据!你读取了吗?");
			  dos.close();
			  s1.close();//关闭连接
			  
			  
		  }catch(IOException e){
			  System.out.println(e);
		  }	
			
			
			
			
			
			
			
			
			
		}	
			
	 }
	
}
