package socketDemo;
import java.net.*;
import java.io.*;
/**
 *@author NingKangMing
 *@Content socket客户端,Socket的任务是接收和发送数据
 *
 */
public class SocketClient1 {
	 public static void main(String args[]){
		 System.out.println("开始请求!");
	    	try{
	    		/*Socket在java中对应一种面向连接的数据通讯机制。基于TCP协议
	    		 * Socket是一个处理不同机子的数据通讯的类，其处理过程大概是，客户端通过写(OutputStream或Writer)向服务端发送请求。
	    		 * 服务端通过读InputStream或Reader读取数据，处理完后通过写向客户端发送数据.
	    		 * 
	    		 * */
	   		 Socket s1=new Socket("127.0.0.1",666);//建立与特定机子特定端口的联接
	   		 InputStream ins=s1.getInputStream();//从Socket对象上获取(读取)其它Socket对象发送过来的数据
	   		 DataInputStream dis=new DataInputStream(ins);
	   	    //给InputStream对象串接一个DataInputStream对象，该对象负责将发送过来的字节信息组装成复杂的数据对象信息
	   		 String strMsg=dis.readUTF();//读取一个字符串
	   		 System.out.println(strMsg);
	   		 System.out.println("嗯嗯!我接收到了");
	   		 
	   		 dis.close();//关闭对象
	   		 s1.close();
	   		
	   		 
	   	}catch(ConnectException conex){
	   		System.err.println("服务器连接失败!");
	   		conex.printStackTrace();
	   	}
	    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
}
