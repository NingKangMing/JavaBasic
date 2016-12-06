package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 * 服务端处理请求类
 */
public class TalkWorker implements Runnable{
  public String name;
  public BufferedReader breader;//读取流
  public PrintWriter pwriter;//输出流
  public static String content;
   public TalkWorker(Socket s){
	   try{
		   InputStream istream=s.getInputStream();
		   OutputStream os=s.getOutputStream();
		   this.breader=new BufferedReader(new InputStreamReader(istream));//字节到单个字符到字符串的组装过程,参数里面为获取数据的依据
		   this.pwriter=new PrintWriter(os,true);
		   
	   }catch(Exception e){
	 	  
	   }
   }
   
   public void run(){
	   while(true){
		   try{
			   String temp=this.breader.readLine();
			   if(temp.equals("01")){
				   this.pwriter.println("收到01指令,请输入用户名!");
				   this.name=this.breader.readLine();
				   this.pwriter.println("接收到用户名OK01!");
			   }else if(temp.equals("02")){
				   this.pwriter.println("收到02指令,请输入你的内容!");
				   content=this.breader.readLine()+"/"+content;
				   this.pwriter.println("内容已经读取!ok02!");
			   }else if(temp.equals("03")){
				   this.pwriter.println("收到03指令,下面为你输入的内容!");
				   this.pwriter.println(content);
			   }else if(temp.equals("04")){
				   this.pwriter.println("收到04指令,表示用户已经离开!");
				   content=this.name+"离开了"+"/"+content;
			   }else{
				   this.pwriter.println("FALSE!");
			   }
			   
		   }catch(Exception e){
			   break;
		   }
	   }
   }
  
}
