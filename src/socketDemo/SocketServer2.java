package socketDemo;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer2 {
	 public static void main(String args[]){
		 System.out.println("开启聊天服务!");
		 try{
			 
			 ServerSocket s=new ServerSocket(5700);//服务对象，相当于一个监听器,监听5700端口	
			 Socket s1=s.accept();
			 String strline="";
			 /*BufferedReader 相当于Socket对象读取数据的过滤器
			  * Socket首先接收到请求信息,然后getInputStream()得到字节信息,InputStream负责从Socket中读取字节型数据
			  * InputStreamReader流负责将字节信息组装为字符型数据,并传递给BufferedReader流对象,
			  * BufferedReader流对象则负责将单个字符信息组装成字符串信息
			  *  其构造函数里的参数是读取数据的地址
			  * */
			 BufferedReader breader=new BufferedReader(new InputStreamReader(s1.getInputStream()));//读取数据对象
			 /*
			  * PrintWriter相当于Socket对象输出信息的过滤器,通过PrintWriter对象将字符串数据转变为字节数据,然后通过
			  * Socket对象的OutputStream流输出到Socket上,令一端通过InputStream获取
			  * 其构造函数里的参数是目的数据源，也就是将要写进去的目的地
			  * */
			 PrintWriter pwriter=new PrintWriter(s1.getOutputStream());//得到输出流(写),Socket向外界发送数据
			 BufferedReader breaderIn=new BufferedReader(new InputStreamReader(System.in));//标准输入流获得读取数据
			 System.out.println("服务端程序,客户端的信息为:"+breader.readLine());
			 strline=breaderIn.readLine();//从键盘读取数据
			 
			 while(!strline.equals("bye")){
				 pwriter.println(strline);// 打印字符，然后终止该行。 
				 pwriter.flush();//刷新该流的缓冲,把数据流中的任何缓冲数据都输出到目的数据源OutputStream
				 System.out.println("服务端程序,服务端的数据为:"+strline);
				 System.out.println("服务端程序,客户端的信息为:"+breader.readLine());
				 strline=breaderIn.readLine();//继续读取
			 }
			 breader.close();//关闭输入流(读取流)
			 pwriter.close();//关闭输出流(写)
			 s1.close();//关闭Socket
			 s.close();//关闭监听器
			 
			 	
			 
		 }catch(Exception e){
			 System.out.println(e);
		 }
		 
	 }
 
}
