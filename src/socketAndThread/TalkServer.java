package socketAndThread;
import java.io.*;
import java.net.*;
/**
 * 
 * @author Administrator
 *  ����˼�������һ�������󵽴�ʹ���һ��TalkWorkerʵ��������ͨѶ�ܵ�ί�и�TalkWorker
 */
public class TalkServer {
	ServerSocket b;
	public void start(){
		try{
			b=new ServerSocket(9011);//���ü����˿�
			while(true){
				Socket c=b.accept();//��������,�ӵ����������ִ��
				TalkWorker t1=new TalkWorker(c);
				Thread thd=new Thread(t1);//Ϊÿ���ͻ�������һ���������̡߳����������ȷʵ��Runnable�ӿ�
				thd.start();//��Ϊ������һ���̺߳�,�����ֿ�ʼ���������ͻ��˵�����ÿ���ͻ���ͨ��һ�������߳�Ϊ�����
				 
				t1.run();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
