package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *  客户端工作者，负责和服务端进行通讯
 */
public class ClientWorker {
   private Socket st=null;//接收ClientBooter发送过来的通讯实例，从而获得具体的通讯地点
   private BufferedReader breader=null;//读取流，负责读取服务端发来的信息
   private PrintWriter pwriter=null;//输出流，负责将字符串信息组装为字节信息后输出到OutputStream然后输出到Socket对象上
   private BufferedReader myreader=null;//读取从从键盘输入的值
   /**
    * 
    * @param s
    * 接收来客户端Socket对象，该对象和特定端口建立连接
    * 本构造函数接收服务端发过来的信息，并向服务端发送信息
    */
  public ClientWorker(Socket s){
	   this.st=s;
	   try{
     InputStream instream=this.st.getInputStream();//获取读取数据的渠道
     this.breader=new BufferedReader(new InputStreamReader(instream));//获取服务端的信息
     OutputStream outStream=this.st.getOutputStream();//获取输出流，也就是获取向Socket写数据的渠道
	 this.pwriter=new PrintWriter(outStream);//把字符串信息组装成字节信息输出到目的地outStream，outStream把数据写到Socket上
	 
	 InputStreamReader inreader=new InputStreamReader(System.in);//从数据源System.in上获取字节信息后组装为单个字符信息
	 this.myreader=new BufferedReader(inreader);//从数据源inreader获取单个字符信息组装成字节串信息。用于读取键盘输入的信息
		    
	   }catch(Exception e){
		   System.out.println("服务器连接失败！");
		   e.printStackTrace();
	   }
   }
  /**
   * 启动客户端
   */
  public void run(){
	  try{
		  System.out.println("请发送聊天指令！01或02或03或04.");
		  String strUserOrder=this.myreader.readLine();//读取用户输入
		  while(!strUserOrder.equals("exit")){
			  this.pwriter.println(strUserOrder);
			  this.pwriter.flush();//发送到服务端
			  String strServerContent=this.breader.readLine();//获取服务端发送来的信息
			  String[] strarr=strServerContent.split("/");
			  for(int i=0;i<strarr.length;i++){
				  System.out.println(strarr[i]);
			  }
			  strUserOrder=this.myreader.readLine();//读取用户输入
		  }
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}
