package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *  服务端监听器，一旦有请求到达，就创建一个TalkWorker实例，并将通讯管道委托给TalkWorker
 */
public class TalkServer {
	ServerSocket b;
	public void start(){
		try{
			b=new ServerSocket(9011);//设置监听端口
			while(true){
				Socket c=b.accept();//监听请求,接到请求后向下执行
				TalkWorker t1=new TalkWorker(c);
				Thread thd=new Thread(t1);//为每个客户端启动一个单独的线程。对象必须明确实现Runnable接口
				thd.start();//当为启动了一个线程后,程序又开始监听其它客户端的请求。每个客户端通过一个独立线程为其服务
				 
				t1.run();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
