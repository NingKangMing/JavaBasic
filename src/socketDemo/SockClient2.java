package socketDemo;
import java.net.*;
import java.io.*;
public class SockClient2 {
	public static void main(String args[]){
		 System.out.println("开始请求!");
	    	try{
	    		/*Socket在java中对应一种面向连接的数据通讯机制。基于TCP协议
	    		 * Socket是一个处理不同机子的数据通讯的类，其处理过程大概是，客户端通过写(OutputStream或Writer)向服务端发送请求。
	    		 * 服务端通过读InputStream或Reader读取数据，处理完后通过写向客户端发送数据.
	    		 * 
	    		 * */
	   		 Socket s1=new Socket("127.0.0.1",5700);//建立与特定机子特定端口的联接
	   		BufferedReader breader=new BufferedReader(new InputStreamReader(System.in));//从键盘读取数据
	   	    PrintWriter pwriter=new PrintWriter(s1.getOutputStream());
	   	   BufferedReader breaderIn=new BufferedReader(new InputStreamReader(s1.getInputStream()));//从socket读取
	     	 String readline="";
	     	 readline=breader.readLine();//获取键盘的值
	     	 while(!readline.equals("bye")){
	     		 pwriter.println(readline);// 打印字符，然后终止该行。 
				 pwriter.flush();//刷新该流的缓冲,把数据流中的任何缓冲数据都输出到目的数据源OutputStream
				 System.out.println("客户端程序,客户端的数据为:"+readline);
				 System.out.println("客户端程序,服务端的信息为:"+breader.readLine());
				 readline=breaderIn.readLine();//继续读取
	     	 }
	   		 
	     	breader.close();//关闭输入流(读取流)
			 pwriter.close();//关闭输出流(写)
			 s1.close();//关闭Socket
	   		 
	   		 
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
