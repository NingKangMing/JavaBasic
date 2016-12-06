package socketAndThread;
/**
 * 
 * @author Administrator
 *  负责服务器的引导
 *
 */
public class ServerBooter {
   public static void main(String args[]){
	   System.out.println("服务开启");
	   TalkServer server=new TalkServer();
	   server.start();//调用监听方法
   }
}
